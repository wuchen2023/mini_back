package com.web.back.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.web.back.domain.*;
import com.web.back.service.AuthenticationService;
import com.web.back.service.TeacherService;
import com.web.back.service.TeacherSignInService;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import com.web.back.utils.GetOnlyCode;
import com.web.back.utils.ModelMapperSingle;
import com.web.back.utils.PageInfoHelper;
import com.web.back.viewmodel.TeacherGroupResult;
import com.web.back.viewmodel.admin.stu.StuCreateVM;
import com.web.back.viewmodel.admin.user.UserCreateVM;
import com.web.back.viewmodel.admin.user.UserPageRequestVM;
import com.web.back.viewmodel.admin.user.UserResponseVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Api("老师的Api")
public class TeacherController {
//    @Resource
//    TeacherService teacherService;
//    @Resource
//    TeacherSignInService teacherSignInService;
//
    private final TeacherService teacherService;

    private final TeacherSignInService teacherSignInService;

    private final AuthenticationService authenticationService;

    @Autowired
    public TeacherController(TeacherService teacherService, TeacherSignInService teacherSignInService, AuthenticationService authenticationService){
        this.teacherService = teacherService;
        this.teacherSignInService = teacherSignInService;
        this.authenticationService = authenticationService;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ResponseBody
    @ApiOperation("老师注册，添加")
    @PostMapping("add_teacher")
    public ResposeResult add_teacher(@RequestParam String name, @RequestParam String account, @RequestParam String password, @RequestParam String gender,@RequestParam Integer role)
    {
        String encodePwd = authenticationService.pwdEncode(password);
        Teacher teacher = new Teacher(name, "0", account, encodePwd, gender, role);
        return teacherService.add_teacher(teacher);
    }


    @ResponseBody
    @GetMapping("login_teacher")
    @ApiOperation("老师登录，获取唯一的标识码")
    public ResposeResult teacher_login(@RequestParam String account, @RequestParam String password)
    {
        ResposeResult re = teacherService.teacher_login(account, password);
        if (re.getCode() == 0)
        {
            return new ResposeResult(0, "登录失败");
        }
        else
        {
            GetOnlyCode getOnlyCode = new GetOnlyCode();
            String only_code = redis_get(account);
            if(only_code == null)
            {
                only_code = getOnlyCode.get_code();
                redis_save(account, only_code);
                return new ResposeResult(1, only_code);
            }
            else
            {
                return new ResposeResult(1, only_code);
            }
        }
    }

    @ResponseBody
    @ApiOperation("老师创建班级")
    @PostMapping("create_course")
    public ResposeResult create_course(@RequestParam Integer teacher_id, @RequestParam String course_name ,@RequestParam String code, @RequestParam String teacher_account)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            GetOnlyCode getOnlyCode = new GetOnlyCode();
            String invite_code = getOnlyCode.get_invite_code();
            TeacherClass teacherClass = new TeacherClass(teacher_id, course_name, invite_code, 0);
            ResposeResult resposeResult = teacherService.create_course(teacherClass);
            resposeResult.setMessage(invite_code);
            return resposeResult;
        }
        else
        {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("老师发放签到")
    @PostMapping("create_qiandao")
    public ResposeResult create_qiandao(@RequestParam Integer teacher_class_id, @RequestParam String sign_in_title, @RequestParam String code, @RequestParam String teacher_account)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            TeacherSignIn teacherSignIn = new TeacherSignIn(teacher_class_id, 0, sign_in_title, 1);
            return teacherSignInService.create_qiandao(teacherSignIn);
        }
        else
        {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("老师根据课程名字查询teacher_class_id")
    @GetMapping("get_class_id_by_name")
    public ResposeResult get_class_id_by_name(@RequestParam String course_name)
    {
        return teacherService.get_classId_by_name(course_name);
    }

    @ResponseBody
    @ApiOperation("根据签到名称关闭签到")
    @GetMapping("close_qiandao_by_title")
    public ResposeResult close_qiandao(@RequestParam String sign_in_title, @RequestParam String code, @RequestParam String teacher_account)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            return teacherService.close_qiandao(sign_in_title);
        }
        else {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("根据老师的账户获取老师信息")
    @GetMapping("get_teacher_detail")
    public Teacher get_detail(@RequestParam  String account, @RequestParam String code)
    {
        if(code.equals(redis_get(account)))
        {
            return teacherService.get_detail_by_account(account);
        }
        else
        {
            return null;
        }
    }

    @ResponseBody
    @ApiOperation("根据课程名称查询邀请码")
    @GetMapping("get_invite_code")
    public String get_invite_code_by_course_name(@RequestParam String course_name)
    {
        return teacherService.get_invite_code_by_course_name(course_name);
    }

    @ResponseBody
    @ApiOperation("给学生加分")
    @PostMapping("add_student_pounts")
    public ResposeResult add_student_points(@RequestParam Integer points, @RequestParam String student_id, @RequestParam String course_name, @RequestParam String teacher_account, @RequestParam String code)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            return teacherService.add_student_points(points, student_id, course_name);
        }
        else
        {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("获取学生积分排名，降序")
    @GetMapping("get_points_sort")
    public List<StudentPoints> get_points_sort(@RequestParam String course_name)
    {
        return teacherService.get_points_sort(course_name);
    }

    @ResponseBody
    @ApiOperation("获取全部老师的信息")
    @GetMapping("get_all_teachers")
    public List<Teacher> get_all_teachers(){
        return teacherService.get_all_teacher();
    }

    @ResponseBody
    @ApiOperation("老师进行分组，同时得到每组的分组人数,和分组表的id,group_type为分组的目的")
    @PostMapping("create_group_task")
    public ResposeResult<TeacherGroupResult> create_group_task(@RequestParam String teacher_account, @RequestParam Integer teacher_id, @RequestParam String code, @RequestParam Integer group_number, @RequestParam String group_type)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            Group group = new Group(group_number, group_type);
            return teacherService.create_group_task(group, teacher_id);
        }else
        {
            return new ResposeResult<>(0, "未登录，请登录！", null);
        }

    }

    @ResponseBody
    @ApiOperation("老师根据teacher_group_id添加分组")
    @PostMapping("add_group_number")
    public ResposeResult add_group_number(@RequestParam String teacher_account, @RequestParam String code, @RequestParam String group_name, @RequestParam Integer teacher_group_id)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            StudentGroup studentGroup = new StudentGroup(group_name, teacher_group_id);
            return teacherService.add_group_of_number(studentGroup);
        }
        else
        {
            return new ResposeResult(0, "未登录，请登录!");
        }
    }

    @ResponseBody
    @ApiOperation("根据tacher_id获取班级列表")
    @GetMapping("get_all_class_by_teacher_id")
    public List<TeacherClass> get_all_class_by_tacher_id(@RequestParam Integer teacher_id)
    {
        return teacherService.get_all_class_by_teacher_id(teacher_id);
    }

    @ResponseBody
    @ApiOperation("获取一个分组类型老师添加的所有组")
    @GetMapping("get_all_student_group")
    public List<StudentGroup> get_all_student_group(@RequestParam Integer teacher_group_id)
    {
        return teacherService.get_all_student_group_by_teacher_group_id(teacher_group_id);
    }

    @ResponseBody
    @ApiOperation("判断老师的code是否过期")
    @PostMapping("teacher_code_is_f")
    public ResposeResult get_code_is_f(@RequestParam String teacher_account, @RequestParam String code)
    {
        if(code.equals(redis_get(teacher_account)))
        {
            return new ResposeResult(1, "未过期");
        }else{
            return new ResposeResult(0, "过期了");
        }
    }

    @ResponseBody
    @ApiOperation("获取老师所有的分组任务")
    @PostMapping("get_all_group_task")
    public List<TeacherGroup> get_all_teacher_group_task(@RequestParam Integer teacher_id)
    {
        return teacherService.get_all_teacher_group_task(teacher_id);
    }

    @ResponseBody
    @ApiOperation("根据group_id获取group")
    @PostMapping("get_group_task_by_group_id")
    public Group get_group_task(@RequestParam Integer group_id)
    {
        return teacherService.get_group_by_group_id(group_id);
    }





    public void redis_save(String key, String value) {
        redisTemplate.opsForValue().set(key + "-teacher", value, 7, TimeUnit.DAYS);
    }
    public String redis_get(String key) {
        return (String) redisTemplate.opsForValue().get(key + "-teacher");
    }

    /**
     * 下面是管理端相关的
     */
    @RequestMapping(value = "api/webadmin/teacher/page/list", method = RequestMethod.POST)
    public RestResponse<PageInfo<UserResponseVM>> pageList(@RequestBody UserPageRequestVM model) {
        PageInfo<Teacher> pageInfo = teacherService.teacherPage(model);
        PageInfo<UserResponseVM> page = PageInfoHelper.copyMap(pageInfo, d -> UserResponseVM.from(d));
        return RestResponse.ok(page);
    }


    @RequestMapping(value = "/api/webadmin/teacher/select/{id}", method = RequestMethod.POST)
    public RestResponse<UserResponseVM> select(@PathVariable Integer id) {
        Teacher teacher = teacherService.getById(id);
        UserResponseVM userVm = UserResponseVM.from(teacher);
        return RestResponse.ok(userVm);
    }
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

    @RequestMapping(value = "/api/webadmin/teacher/edit", method = RequestMethod.POST)
    public RestResponse<Teacher> edit(@RequestBody @Valid UserCreateVM model){
        if (model.getId() == null) {  //create
            System.out.println("老师后台老师edit，传入的id为空");
            Teacher existStu = teacherService.getTeacherByName(model.getName());
            if (null != existStu) {
                return new RestResponse<>(2, "老师已存在");
            }

            if (StringUtils.isBlank(model.getPassword())) {
                return new RestResponse<>(3, "密码不能为空");
            }
        }
//        if (StringUtils.isBlank(model.getBirthDay())) {
//            model.setBirthDay(null);
//        }
        Teacher teacher = modelMapper.map(model, Teacher.class);

        if (model.getId() == null) {
            System.out.println("进入到了这一步");
            String encodePwd = authenticationService.pwdEncode(model.getPassword());
            teacher.setPassword(encodePwd);
//            student.setUserUuid(UUID.randomUUID().toString());
            teacher.setCreateTime(new Date());
//            student.setLastActiveTime(new Date());
//            student.setDeleted(false);
//            下面要进行插入的操作，不要忘记写数据库了
            teacherService.insertByFilter(teacher);
        } else {
            //如果点击的存在数据进行编辑，传入的id不为空
            System.out.println("传入的老师id不为空");
            if (!StringUtils.isBlank(model.getPassword())) {
                String encodePwd = authenticationService.pwdEncode(model.getPassword());
                teacher.setPassword(encodePwd);
            }
//            student.setModifyTime(new Date());
            //否则就进行更新
            teacherService.updateByIdFilter(teacher);
        }
        return RestResponse.ok(teacher);
    }


}
