package com.web.back.controller.webadmin;

import com.github.pagehelper.PageInfo;
import com.web.back.base.BaseApiController;
import com.web.back.domain.Subject;
import com.web.back.service.SubjectService;
import com.web.back.state.RestResponse;
import com.web.back.utils.PageInfoHelper;
import com.web.back.viewmodel.admin.education.SubjectEditRequestVM;
import com.web.back.viewmodel.admin.education.SubjectPageRequestVM;
import com.web.back.viewmodel.admin.education.SubjectResponseVM;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/29.
 * @DESC:
 */
@Api("管理后台-学科的Api")
@RestController("WebAdminSubjectController")
@RequestMapping(value = "/api/webadmin/subject")
public class SubjectController extends BaseApiController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @PostMapping("/list")
    public RestResponse<List<Subject>> list(){
        List<Subject> subjects = subjectService.allSubject();
        return RestResponse.ok(subjects);
    }


    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<SubjectResponseVM>> pageList(@RequestBody SubjectPageRequestVM model) {
        PageInfo<Subject> pageInfo = subjectService.page(model);
        PageInfo<SubjectResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> modelMapper.map(e, SubjectResponseVM.class));
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid SubjectEditRequestVM model) {
        Subject subject = modelMapper.map(model, Subject.class);
        if (model.getId() == null) {
            subject.setDeleted(false);
            subjectService.insertByFilter(subject);
        } else {
            subjectService.updateByIdFilter(subject);
        }
        return RestResponse.ok();
    }

    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<SubjectEditRequestVM> select(@PathVariable Integer id) {
        Subject subject = subjectService.selectById(id);
        SubjectEditRequestVM vm = modelMapper.map(subject, SubjectEditRequestVM.class);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        Subject subject = subjectService.selectById(id);
        subject.setDeleted(true);
        subjectService.updateByIdFilter(subject);
        return RestResponse.ok();
    }
}
