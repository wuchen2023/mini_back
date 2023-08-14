package com.web.back.controller.webadmin;

import com.web.back.annotation.Log;
import com.web.back.domain.SubjectClass;
import com.web.back.domain.enums.BusinessType;
import com.web.back.service.SubjectClassService;
import com.web.back.utils.AjaxResult;
import com.web.back.utils.StringUtils;
import com.web.back.utils.constant.UserConstants;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/8/10.
 * @DESC:
 */
@RestController("WebAdminSubjectClassController")
@Api("管理后台-专业班级的Api")
@RequestMapping(value = "/api/webadmin/subjectclass/")
public class SubjectClassController extends BaseController{
    @Autowired
    private SubjectClassService subjectClassService;

    /**
     * 获取部门列表
     */
//    @PreAuthorize("@ss.hasPermi('system:subjectClass:list')")
    @GetMapping("/list")
    public AjaxResult list(SubjectClass subjectClass)
    {
        List<SubjectClass> subjectClasses = subjectClassService.selectSubjectClassList(subjectClass);
        return success(subjectClasses);
    }

    /**
     * 查询部门列表（排除节点）
     */
//    @PreAuthorize("@ss.hasPermi('system:subjectClass:list')")
    @GetMapping("/list/exclude/{subjectId}")
    public AjaxResult excludeChild(@PathVariable(value = "subjectId", required = false) Long subjectId)
    {
        List<SubjectClass> subjectClasses = subjectClassService.selectSubjectClassList(new SubjectClass());
//        System.out.println(subjectClasses.toString());
        subjectClasses.removeIf(d -> d.getSubjectId().intValue() == subjectId || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), subjectId + ""));
        return success(subjectClasses);
    }

    /**
     * 根据部门编号获取详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:subjectClass:query')")
    @GetMapping(value = "/{subjectId}")
    public AjaxResult getInfo(@PathVariable Long subjectId)
    {
//        subjectClassService.checkSubjectClassDataScope(subjectId);
        return success(subjectClassService.selectSubjectClassById(subjectId));
    }

    /**
     * 新增部门
     */
//    @PreAuthorize("@ss.hasPermi('system:subjectClass:add')")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SubjectClass subjectClass)
    {
        if (!subjectClassService.checkSubjectClassNameUnique(subjectClass))
        {
            return error("新增专业/班级'" + subjectClass.getSubjectName() + "'失败，专业/班级名称已存在");
        }
        subjectClass.setCreateBy("管理员");
        return toAjax(subjectClassService.insertSubjectClass(subjectClass));
    }

    /**
     * 修改部门
     */
//    @PreAuthorize("@ss.hasPermi('system:subjectClass:edit')")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@Validated @RequestBody SubjectClass subjectClass)
    {
        Long subjectId = subjectClass.getSubjectId();
//        subjectClassService.checkSubjectClassDataScope(subjectId);
        if (!subjectClassService.checkSubjectClassNameUnique(subjectClass))
        {
            return error("修改专业班级'" + subjectClass.getSubjectName() + "'失败，专业班级名称已存在");
        }
        else if (subjectClass.getParentId().equals(subjectId))
        {
            return error("修改专业班级'" + subjectClass.getSubjectName() + "'失败，上级专业班级不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, subjectClass.getStatus()) && subjectClassService.selectNormalChildrenSubjectClassById(subjectId) > 0)
        {
            return error("该部门包含未停用的子专业班级！");
        }
        subjectClass.setUpdateBy("管理员");
        return toAjax(subjectClassService.updateSubjectClass(subjectClass));
    }

    /**
     * 删除部门
     */
//    @PreAuthorize("@ss.hasPermi('system:subjectClass:remove')")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @PostMapping("/{subjectId}")
    public AjaxResult remove(@PathVariable Long subjectId)
    {
        if (subjectClassService.hasChildBySubjectId(subjectId))
        {
            return warn("存在下级专业/班级,不允许删除");
        }
        if (subjectClassService.checkSubjectClassExistUser(subjectId))
        {
            return warn("班级存在用户,不允许删除");
        }
//        subjectClassService.checkSubjectClassDataScope(subjectId);
        return toAjax(subjectClassService.deleteSubjectClassById(subjectId));
    }

}
