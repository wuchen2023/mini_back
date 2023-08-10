package com.web.back.mapper;

import com.web.back.domain.SubjectClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/8/10.
 * @DESC:
 */
@Mapper
public interface SubjectClassMapper {
    /**
     * 查询部门管理数据
     *
     * @param subjectClass 部门信息
     * @return 部门信息集合
     */
    public List<SubjectClass> selectSubjectClassList(SubjectClass subjectClass);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
//    public List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

    /**
     * 根据部门ID查询信息
     *
     * @param subjectId 部门ID
     * @return 部门信息
     */
    public SubjectClass selectSubjectClassById(Long subjectId);

    /**
     * 根据ID查询所有子部门
     *
     * @param subjectId 部门ID
     * @return 部门列表
     */
    public List<SubjectClass> selectChildrenSubjectClassById(Long subjectId);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param subjectId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenSubjectClassById(Long subjectId);

    /**
     * 是否存在子节点
     *
     * @param subjectId 部门ID
     * @return 结果
     */
    public int hasChildBySubjectId(Long subjectId);

    /**
     * 查询部门是否存在用户
     *
     * @param subjectId 部门ID
     * @return 结果
     */
    public int checkSubjectClassExistUser(Long subjectId);

    /**
     * 校验部门名称是否唯一
     *
     * @param subjectName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    public SubjectClass checkSubjectClassNameUnique(@Param("subjectName") String subjectName, @Param("parentId") Long parentId);

    /**
     * 新增部门信息
     *
     * @param subjectClass 部门信息
     * @return 结果
     */
    public int insertSubjectClass(SubjectClass subjectClass);

    /**
     * 修改部门信息
     *
     * @param subjectClass 部门信息
     * @return 结果
     */
    public int updateSubjectClass(SubjectClass subjectClass);

    /**
     * 修改所在部门正常状态
     *
     * @param subjectIds 部门ID组
     */
    public void updateSubjectClassStatusNormal(Long[] subjectIds);

    /**
     * 修改子元素关系
     *
     * @param subjectClasses 子元素
     * @return 结果
     */
    public int updateSubjectClassChildren(@Param("subjectClasses") List<SubjectClass> subjectClasses);

    /**
     * 删除部门管理信息
     *
     * @param subjectId 部门ID
     * @return 结果
     */
    public int deleteSubjectClassById(Long subjectId);
}
