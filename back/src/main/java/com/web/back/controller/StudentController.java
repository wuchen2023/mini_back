package com.web.back.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.web.back.controller.webadmin.BaseController;
import com.web.back.domain.*;
import com.web.back.domain.result.StudentClassRes;
import com.web.back.mapper.StudentMapper;
import com.web.back.service.AuthenticationService;
import com.web.back.service.StudentService;
import com.web.back.service.StudentSignInService;
import com.web.back.service.SubjectClassService;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import com.web.back.utils.*;
import com.web.back.utils.constant.HttpStatus;
import com.web.back.utils.poi.ExcelUtil;
import com.web.back.viewmodel.admin.stu.StuCreateVM;
import com.web.back.viewmodel.admin.stu.StuPageRequestVM;
import com.web.back.viewmodel.admin.stu.StuResponseVM;
import com.web.back.viewmodel.admin.stu.stu1;
import com.web.back.viewmodel.admin.user.UserPageRequestVM;
import com.web.back.viewmodel.admin.user.UserResponseVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Api("学生的Api")
public class StudentController extends BaseController {
    @Resource
    StudentService studentService;

    @Resource
    StudentSignInService studentSignInService;

    @Resource
    AuthenticationService authenticationService;

    @Resource
    SubjectClassService subjectClassService;
    @Autowired
    public StudentController(StudentService studentService, StudentSignInService studentSignInService) {
        this.studentService = studentService;
        this.studentSignInService = studentSignInService;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @ResponseBody
    @PostMapping("add_student")
    @ApiOperation("添加学生,注册")
    public ResposeResult add_student(@RequestParam String name, @RequestParam String account, @RequestParam String password, @RequestParam String gender, @RequestParam Integer role) {
        Student student = new Student(name, "0", account, password, gender, role);
        return studentService.add_student(student);
    }


    //登录后，学生的信息讲纳入缓存，标识码讲纳入缓存
    @ResponseBody
    @GetMapping("login_student")
    @ApiOperation("学生登录，获取唯一的标识码")
    public ResposeResult student_login(@RequestParam String account, @RequestParam String password) {
        ResposeResult re = studentService.student_login(account, password);
        if (re.getCode() == 0) {
            return new ResposeResult(0, "登录失败");
        } else {
            GetOnlyCode getOnlyCode = new GetOnlyCode();
            String only_code = redis_get(account);
            if (only_code == null) {
                only_code = getOnlyCode.get_code();
                redis_save(account, only_code);
                return new ResposeResult(1, only_code);
            } else {
                return new ResposeResult(1, only_code);
            }
        }
    }

    @ResponseBody
    @PostMapping("add_course")
    @ApiOperation("学生加入班级")
    public ResposeResult add_course(@RequestParam Integer student_id, @RequestParam String invite_code, @RequestParam String code, @RequestParam String student_account) {
        if (code.equals(redis_get(student_account))) {
            return studentService.add_course(student_id, invite_code);
        } else {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("学生签到")
    @PostMapping("add_qiandao")
    public ResposeResult add_qiandao(@RequestParam Integer student_id, @RequestParam String sign_in_title, @RequestParam String code, @RequestParam String student_account) {
        if (code.equals(redis_get(student_account))) {
            Integer teacher_sign_in_id = studentService.get_teacher_sign_in_id(sign_in_title);
            if (teacher_sign_in_id == -1) {
                return new ResposeResult(0, "签到失败");
            }
            StudentSignIn studentSignIn = new StudentSignIn(student_id, teacher_sign_in_id, "签到成功");
            return studentSignInService.add_qiandao(studentSignIn);
        } else {
            return new ResposeResult(0, "未登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("根据学生的账户获取学生信息")
    @GetMapping("get_student_detail")
    public Student get_detail(@RequestParam String account, @RequestParam String code) {
        if (code.equals(redis_get(account))) {
            return studentService.get_detail_by_account(account);
        } else {
            return null;
        }
    }

    @ResponseBody
    @ApiOperation("获取全部学生的信息")
    @GetMapping("get_all_students")
    public List<Student> get_all_students() {
        return studentService.get_all_students();
    }

    @ResponseBody
    @ApiOperation("加入老师的分组")
    @PostMapping("student_add_group")
    public ResposeResult add_group(@RequestParam String student_account, @RequestParam String code, @RequestParam Integer student_id, @RequestParam Integer student_group_id, @RequestParam Integer group_number, @RequestParam Integer group_id) {
        if (code.equals(redis_get(student_account))) {
            Grouping grouping = new Grouping(student_id, student_group_id);
            return studentService.add_group(grouping, group_number, group_id);
        } else {
            return new ResposeResult(0, "为登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("退出老师的分组")
    @PostMapping("student_exit_group")
    public ResposeResult exit_group(@RequestParam String student_account, @RequestParam String code, @RequestParam Integer student_id, @RequestParam Integer student_group_id, @RequestParam Integer group_id) {
        if (code.equals(redis_get(student_account))) {
            return studentService.exit_group(student_id, student_group_id, group_id);
        } else {
            return new ResposeResult(0, "为登录，请登录！");
        }
    }

    @ResponseBody
    @ApiOperation("根据student_group_id获取这个分组的所有成员")
    @GetMapping("get_all_group_member")
    public List<Grouping> get_all_group_member(@RequestParam Integer student_group_id) {
        return studentService.get_all_group_member(student_group_id);
    }

    @ResponseBody
    @ApiOperation("判断学生的code是否过期")
    @PostMapping("student_code_is_f")
    public ResposeResult get_code_is_f(@RequestParam String student_account, @RequestParam String code) {
        if (code.equals(redis_get(student_account))) {
            return new ResposeResult(1, "未过期");
        } else {
            return new ResposeResult(0, "过期了");
        }
    }

    @ResponseBody
    @ApiOperation("获取已经签到和未签到的学生信息,list中第一个为签到的list,第二个为未签到的")
    @GetMapping("get_sign_in_detail")
    public List<List<Student>> get_no_sign_in(@RequestParam String course_name, @RequestParam Integer teacher_sign_in_id) {
        return studentService.get_qiandao_detail(course_name, teacher_sign_in_id);
    }


    //TODO
    @ResponseBody
    @ApiOperation("根据student_id查询学生加入的班级")
    @PostMapping("get_all_class_by_student_id")
    public List<StudentClassRes> get_all_class_by_s_id(@RequestParam Integer student_id) {
        return studentService.get_all_class_by_student_id(student_id);
    }


    public void redis_save(String key, String value) {
        redisTemplate.opsForValue().set(key + "-student", value, 7, TimeUnit.DAYS);
    }

    public String redis_get(String key) {
        return (String) redisTemplate.opsForValue().get(key + "-student");
    }


    /**
     * 下面是管理端相关的
     */
    @RequestMapping(value = "api/webadmin/student/page/list", method = RequestMethod.POST)
    public RestResponse<PageInfo<StuResponseVM>> pageList(@RequestBody StuPageRequestVM model) {
        System.out.println("学生测试");
        PageInfo<Student> pageInfo = studentService.studentPage(model);
        PageInfo<StuResponseVM> page = PageInfoHelper.copyMap(pageInfo, d -> StuResponseVM.from(d));
        return RestResponse.ok(page);
    }

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

    @RequestMapping(value = "/api/webadmin/student/edit", method = RequestMethod.POST)
    public RestResponse<Student> edit(@RequestBody @Valid StuCreateVM model) {
        if (model.getId() == null) {  //create
            System.out.println("老师后台学生edit，传入的id为空");
            Student existStu = studentService.getStuByStuName(model.getName());
            if (null != existStu) {
                return new RestResponse<>(2, "学生已存在");
            }

            if (StringUtils.isBlank(model.getPassword())) {
                return new RestResponse<>(3, "密码不能为空");
            }
        }
        Student student = modelMapper.map(model, Student.class);

        if (model.getId() == null) {
            System.out.println("进入到了这一步");
            student.setPassword(model.getPassword());
            student.setCreateTime(new Date());
            student.setSubjectId(model.getSubjectId());
//            下面要进行插入的操作，不要忘记写数据库了
            studentService.insertByFilter(student);
        } else {
            //如果点击的存在数据进行编辑，传入的id不为空
            System.out.println("传入的学生id不为空");
            if (!StringUtils.isBlank(model.getPassword())) {
//                String encodePwd = authenticationService.pwdEncode(model.getPassword());
                student.setPassword(model.getPassword());
            }
            //否则就进行更新
            studentService.updateByIdFilter(student);
        }
        return RestResponse.ok(student);
    }

    //查询
    @RequestMapping(value = "/api/webadmin/student/select/{id}", method = RequestMethod.POST)
    public RestResponse<StuResponseVM> select(@PathVariable Integer id) {
        System.out.println("对学生进行查询");
        Student student = studentService.getById(id);
        StuResponseVM stuResponseVM = StuResponseVM.from(student);
        return RestResponse.ok(stuResponseVM);
    }

    @RequestMapping(value = "/api/webadmin/student/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
//       Student student = studentService.getById(id);
//       student.setDeleted(true);
//       studentService.updateByIdFilter(student);
        try {
            studentService.deleteById(id);
        } catch (Exception e) {
            return RestResponse.fail(500, "删除失败");
        }
        return RestResponse.ok();
    }


    @PostMapping("/api/webadmin/student/importTemplate")
    public void importTemplate(HttpServletResponse response) {

        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        util.importTemplateExcel(response, "用户数据");
    }

//    @PostMapping("/api/webadmin/student/importData")
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
//    {
//        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
//        List<Student> userList = util.importExcel(file.getInputStream());
//        String message = studentService.importUser(userList, updateSupport);
//        return success(message);
//    }

    @PostMapping("/api/webadmin/student/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) {
        try {
            ExcelUtil<Student> util = new ExcelUtil<>(Student.class);
            List<Student> userList = util.importExcel(file.getInputStream());
            String message = studentService.importUser(userList, updateSupport);
            return success(message);
        } catch (Exception e) {
            e.printStackTrace();
            return success("Import failed: " + e.getMessage());
        }
    }

    @PostMapping("/api/webadmin/student/export")
    public void export(HttpServletResponse response,@RequestBody stu1 queryParams) throws IOException {
        Student student = new Student();
        System.out.println(queryParams.getSubjectId());
        student.setSubjectId(queryParams.getSubjectId());
        List<Student> list = studentService.newselectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        util.exportExcel(response, list, "用户数据");
//        //创建一个XSSFWorkbook对象
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        //创建一个XSSFSheet对象，命名为"用户数据"
//        XSSFSheet sheet = workbook.createSheet("用户数据");
//        //创建一个XSSFRow对象，作为表头行，索引为0
//        XSSFRow headerRow = sheet.createRow(0);
//        //创建五个XSSFCell对象，分别设置值为"id","姓名","权限","账号","密码"
//        headerRow.createCell(0).setCellValue("id");
//        headerRow.createCell(1).setCellValue("姓名");
//        headerRow.createCell(2).setCellValue("账号");
//        headerRow.createCell(3).setCellValue("密码");
//        //遍历list，从索引为1开始创建数据行
//        int rowNum = 1;
//        for (Student student1 : list) {
//            //创建一个XSSFRow对象，作为数据行
//            XSSFRow dataRow = sheet.createRow(rowNum);
//            //创建五个XSSFCell对象，分别设置值为student的属性
//            dataRow.createCell(0).setCellValue(student1.getId());
//            dataRow.createCell(1).setCellValue(student1.getName());
//            dataRow.createCell(2).setCellValue(student1.getAccount());
//            dataRow.createCell(3).setCellValue(student1.getPassword());
//            //增加行号
//            rowNum++;
//        }
//        // 设置响应的内容类型和头信息
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=student_data.xlsx");
//
//        // 将workbook写入输出流
//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//
//        // 关闭workbook和输出流
//        workbook.close();
//        outputStream.close();
    }

    @GetMapping("/api/webadmin/student/subjectclassTree")
    public AjaxResult subjectclassTree(SubjectClass subjectClass)
    {
        return success(subjectClassService.selectSubjectClassTreeList(subjectClass));
    }
    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageUtils.startPage();
    }
    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    @GetMapping("/api/webadmin/student/list")
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = studentService.newselectStudentList(student);
        return getDataTable(list);
    }
}
