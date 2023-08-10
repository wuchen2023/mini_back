package com.web.back.service;

import com.web.back.domain.SubjectClass;
import com.web.back.domain.tree.TreeSelect;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/8/10.
 * @DESC:
 */
public interface SubjectClassService {
    /**
     * 查询专业班级管理数据
     *
     * @param subjectClass 专业班级信息
     * @return 专业班级信息集合
     */
    public List<SubjectClass> selectSubjectClassList(SubjectClass subjectClass);

    /**
     * 查询部门树结构信息
     *
     * @param subjectClass 部门信息
     * @return 部门树信息集合
     */
    public List<TreeSelect> selectSubjectClassTreeList(SubjectClass subjectClass);

    /**
     * 构建前端所需要树结构
     *
     * @param subjectClasses 部门列表
     * @return 树结构列表
     */
    public List<SubjectClass> buildSubjectClassTree(List<SubjectClass> subjectClasses);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param subjectClasses 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildSubjectClassTreeSelect(List<SubjectClass> subjectClasses);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
//    public List<Long> selectSubjectClassListByRoleId(Long roleId);

    /**
     * 根据部门ID查询信息
     *
     * @param subjectId 部门ID
     * @return 部门信息
     */
    public SubjectClass selectSubjectClassById(Long subjectId);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param subjectId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenSubjectClassById(Long subjectId);

    /**
     * 是否存在部门子节点
     *
     * @param subjectId 部门ID
     * @return 结果
     */
    public boolean hasChildBySubjectId(Long subjectId);

    /**
     * 查询部门是否存在用户
     *
     * @param subjectId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkSubjectClassExistUser(Long subjectId);

    /**
     * 校验部门名称是否唯一
     *
     * @param subjectClass 部门信息
     * @return 结果
     */
    public boolean checkSubjectClassNameUnique(SubjectClass subjectClass);

    /**
     * 校验部门是否有数据权限
     *
     * @param subjectId 部门id
     */
    public void checkSubjectClassDataScope(Long subjectId);

    /**
     * 新增保存部门信息
     *
     * @param subjectClass 部门信息
     * @return 结果
     */
    public int insertSubjectClass(SubjectClass subjectClass);

    /**
     * 修改保存部门信息
     *
     * @param subjectClass 部门信息
     * @return 结果
     */
    public int updateSubjectClass(SubjectClass subjectClass);

    /**
     * 删除部门管理信息
     *
     * @param subjectId 部门ID
     * @return 结果
     */
    public int deleteSubjectClassById(Long subjectId);
}
