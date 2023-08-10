package com.web.back.service.impl;

import com.web.back.annotation.DataScope;
import com.web.back.domain.SubjectClass;
import com.web.back.domain.tree.TreeSelect;
import com.web.back.mapper.SubjectClassMapper;
import com.web.back.service.SubjectClassService;
import com.web.back.utils.StringUtils;
import com.web.back.utils.constant.UserConstants;
import com.web.back.utils.spring.SpringUtils;
import com.web.back.utils.text.Convert;
import org.hibernate.boot.jaxb.hbm.spi.SubEntityInfo;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by hongdou
 * @date 2023/8/10.
 * @DESC:
 */
@Service
public class SubjectClassServiceImpl implements SubjectClassService {
    @Autowired
    private SubjectClassMapper subjectClassMapper;

    @Override
//    @DataScope(subjectClassAlias = "s")
    public List<SubjectClass> selectSubjectClassList(SubjectClass subjectClass){
        return subjectClassMapper.selectSubjectClassList(subjectClass);

    }
    @Override
    public List<TreeSelect> selectSubjectClassTreeList(SubjectClass subjectClass){
//        List<SubjectClass> subjectClasses = SpringUtils.getAopProxy(this).selectSubjectClassList(subjectClass);
        List<SubjectClass> subjectClasses = subjectClassMapper.selectSubjectClassList(subjectClass);
        return buildSubjectClassTreeSelect(subjectClasses);
    }



    @Override
    public List<SubjectClass> buildSubjectClassTree(List<SubjectClass> subjectClasses){
        List<SubjectClass> returnList = new ArrayList<SubjectClass>();
        List<Long> tempList = subjectClasses.stream().map(SubjectClass::getSubjectId).collect(Collectors.toList());
        for (SubjectClass subjectClass : subjectClasses)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(subjectClass.getParentId()))
            {
                recursionFn(subjectClasses, subjectClass);
                returnList.add(subjectClass);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = subjectClasses;
        }
        return returnList;
    }
    @Override
    public List<TreeSelect> buildSubjectClassTreeSelect(List<SubjectClass> subjectClasses){
        List<SubjectClass> subjectClassesTrees = buildSubjectClassTree(subjectClasses);
        return subjectClassesTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public SubjectClass selectSubjectClassById(Long subjectId){
        return subjectClassMapper.selectSubjectClassById(subjectId);
    }

    @Override
    public int selectNormalChildrenSubjectClassById(Long subjectId){
        return subjectClassMapper.selectNormalChildrenSubjectClassById(subjectId);

    }

    @Override
    public boolean hasChildBySubjectId(Long subjectId){
        int result = subjectClassMapper.hasChildBySubjectId(subjectId);
        return result > 0;
    }
    @Override
    public boolean checkSubjectClassExistUser(Long subjectId){
        int result = subjectClassMapper.checkSubjectClassExistUser(subjectId);
        return result > 0;
    }
    @Override
    public boolean checkSubjectClassNameUnique(SubjectClass subjectClass){
        Long subjectId = StringUtils.isNull(subjectClass.getSubjectId()) ? -1L : subjectClass.getSubjectId();
        SubjectClass info = subjectClassMapper.checkSubjectClassNameUnique(subjectClass.getSubjectName(), subjectClass.getParentId());
        if (StringUtils.isNotNull(info) && info.getSubjectId().longValue() != subjectId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    @Override
    public int insertSubjectClass(SubjectClass subjectClass){
        System.out.println(subjectClass.toString());
        SubjectClass info = subjectClassMapper.selectSubjectClassById(subjectClass.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        System.out.println(info);
        System.out.println(info.getStatus());
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new ServiceException("部门停用，不允许新增");
        }
        subjectClass.setAncestors(info.getAncestors() + "," + subjectClass.getParentId());
        return subjectClassMapper.insertSubjectClass(subjectClass);
    }
    @Override
    public int updateSubjectClass(SubjectClass subjectClass){
        SubjectClass newParentSubjectClass = subjectClassMapper.selectSubjectClassById(subjectClass.getParentId());
        SubjectClass oldSubjectClass = subjectClassMapper.selectSubjectClassById(subjectClass.getSubjectId());
        if (StringUtils.isNotNull(newParentSubjectClass) && StringUtils.isNotNull(oldSubjectClass))
        {
            String newAncestors = newParentSubjectClass.getAncestors() + "," + newParentSubjectClass.getSubjectId();
            String oldAncestors = oldSubjectClass.getAncestors();
            subjectClass.setAncestors(newAncestors);
            updateSubjectClassChildren(subjectClass.getSubjectId(), newAncestors, oldAncestors);
        }
        int result = subjectClassMapper.updateSubjectClass(subjectClass);
        if (UserConstants.DEPT_NORMAL.equals(subjectClass.getStatus()) && StringUtils.isNotEmpty(subjectClass.getAncestors())
                && !StringUtils.equals("0", subjectClass.getAncestors()))
        {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentSubjectClassStatusNormal(subjectClass);
        }
        return result;
    }

    private void updateParentSubjectClassStatusNormal(SubjectClass subjectClass)
    {
        String ancestors = subjectClass.getAncestors();
        Long[] subjectClassIds = Convert.toLongArray(ancestors);
        subjectClassMapper.updateSubjectClassStatusNormal(subjectClassIds);
    }
    public void updateSubjectClassChildren(Long subjectId, String newAncestors, String oldAncestors)
    {
        List<SubjectClass> children = subjectClassMapper.selectChildrenSubjectClassById(subjectId);
        for (SubjectClass child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            subjectClassMapper.updateSubjectClassChildren(children);
        }
    }

    @Override
    public void checkSubjectClassDataScope(Long subjectId){
        SubjectClass subjectClass = new SubjectClass();
        subjectClass.setSubjectId(subjectId);
        List<SubjectClass> subjectClasses = SpringUtils.getAopProxy(this).selectSubjectClassList(subjectClass);
        if (StringUtils.isEmpty(subjectClasses))
        {
            throw new ServiceException("没有权限访问数据！");
        }
    }
    @Override
    public int deleteSubjectClassById(Long subjectId){
        return subjectClassMapper.deleteSubjectClassById(subjectId);
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SubjectClass> list, SubjectClass t)
    {
        // 得到子节点列表
        List<SubjectClass> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SubjectClass tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SubjectClass> getChildList(List<SubjectClass> list, SubjectClass t)
    {
        List<SubjectClass> tlist = new ArrayList<SubjectClass>();
        Iterator<SubjectClass> it = list.iterator();
        while (it.hasNext())
        {
            SubjectClass n = (SubjectClass) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getSubjectId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SubjectClass> list, SubjectClass t)
    {
        return getChildList(list, t).size() > 0;
    }


}
