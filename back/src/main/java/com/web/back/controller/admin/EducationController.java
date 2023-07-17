package com.web.back.controller.admin;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.Subject;
import com.web.back.service.SubjectService;
import com.web.back.state.RestResponse;
import com.web.back.utils.ModelMapperSingle;
import com.web.back.utils.PageInfoHelper;
import com.web.back.viewmodel.admin.education.SubjectEditRequestVM;
import com.web.back.viewmodel.admin.education.SubjectPageRequestVM;
import com.web.back.viewmodel.admin.education.SubjectResponseVM;
import com.web.back.viewmodel.admin.question.QuestionPageRequestVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
@Api("学科的Api")
@RestController("SubjectController")
@RequestMapping(value = "api/admin/education")
public class EducationController {
    protected final static String DEFAULT_PAGE_SIZE = "10";
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

    private final SubjectService subjectService;

    @Autowired
    public EducationController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @ResponseBody
    @PostMapping("/subject/list")
    @ApiOperation("学科列表")
    public RestResponse<List<Subject>> list(){
        List<Subject> subjects = subjectService.allSubject();

        return RestResponse.ok(subjects);
    }
//    @ApiOperation("可以按条件查询学科")
//    @RequestMapping(value = "/subject/page", method = RequestMethod.POST)
//    public RestResponse<PageInfo<SubjectResponseVM>> pageList(@RequestBody SubjectPageRequestVM model) {
//        PageInfo<Subject> pageInfo = subjectService.page(model);
//        PageInfo<SubjectResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> modelMapper.map(e, SubjectResponseVM.class));
//        return RestResponse.ok(page);
//    }

    @ApiOperation("可以按条件查询学科")
    @RequestMapping(value = "/subject/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<SubjectResponseVM>> pageList(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer level, @RequestParam(required = false) Integer questionType, @RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        SubjectPageRequestVM model = new SubjectPageRequestVM();
        model.setId(id);
        model.setLevel(level);
        model.setPageIndex(pageIndex);
        model.setPageSize(pageSize);
        PageInfo<Subject> pageInfo = subjectService.page(model);
        PageInfo<SubjectResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> modelMapper.map(e, SubjectResponseVM.class));
        return RestResponse.ok(page);
    }

//    @ApiOperation("编辑学科，新增学科也是调用这个接口")
//    @RequestMapping(value = "/subject/edit", method = RequestMethod.POST)
//    public RestResponse edit(@RequestBody @Valid SubjectEditRequestVM model) {
//        Subject subject = modelMapper.map(model, Subject.class);
////        如果传入的id为空，则代表是新增，
//        if (model.getId() == null) {
//            subject.setDeleted(false);
//            subjectService.insertByFilter(subject);
//        } else {
////            反之id不为空的就是查询，所以说即使我传入id，但是数据库中没有数据也会返回ok
//            subjectService.updateByIdFilter(subject);
//        }
//        return RestResponse.ok();
//    }

    @ApiOperation("编辑学科，新增学科也是调用这个接口")
    @RequestMapping(value = "/subject/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) Integer level, @RequestParam(required = false) String levelName) {
        SubjectEditRequestVM model = new SubjectEditRequestVM();
        model.setId(id);
        model.setName(name);
        model.setLevel(level);
        model.setLevelName(levelName);
        Subject subject = modelMapper.map(model, Subject.class);
//        如果传入的id为空，则代表是新增，
        if (model.getId() == null) {
            subject.setDeleted(false);
            subjectService.insertByFilter(subject);
        } else {
//            反之id不为空的就是查询，所以说即使我传入id，但是数据库中没有数据也会返回ok
            subjectService.updateByIdFilter(subject);
        }
        return RestResponse.ok();
    }


    @ResponseBody
    @ApiOperation("学科查询")
    @PostMapping("/subject/select/{id}")
    public RestResponse<SubjectEditRequestVM> select(@PathVariable Integer id) {
        try{
        Subject subject = subjectService.selectById(id);
        SubjectEditRequestVM vm = modelMapper.map(subject, SubjectEditRequestVM.class);
        return RestResponse.ok(vm);}
        catch (Exception e){
            return RestResponse.fail(500,"没有此id的学科");
        }
    }

    @ApiOperation("删除学科")
    @RequestMapping(value = "/subject/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        Subject subject = subjectService.selectById(id);
        subject.setDeleted(true);
        subjectService.updateByIdFilter(subject);
        return RestResponse.ok();
    }
}
