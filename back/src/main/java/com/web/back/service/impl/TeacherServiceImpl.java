package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.back.config.property.SystemConfig;
import com.web.back.domain.*;
import com.web.back.domain.result.TeacherGroupRes;
import com.web.back.mapper.*;
import com.web.back.service.AuthenticationService;
import com.web.back.service.TeacherService;
import com.web.back.utils.RsaUtil;
import com.web.back.viewmodel.TeacherGroupResult;
import com.web.back.viewmodel.admin.user.UserPageRequestVM;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.web.back.state.ResposeResult;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Dell
* @description 针对表【teacher】的数据库操作Service实现
* @createDate 2023-05-07 13:10:23
*/
@Service
@Slf4j
@Lazy
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService {
    @Resource
    BaseMapper baseMapper;

    @Resource
    TeacherMapper teacherMapper;
    @Resource
    TeacherClassMapper teacherClassMapper;

    @Resource
    TeacherSignInMapper teacherSignInMapper;

    @Resource
    StudentPointsMapper studentPointsMapper;

    @Resource
    GroupMapper groupMapper;

    @Resource
    TeacherGroupMapper teacherGroupMapper;

    @Resource
    StudentGroupMapper studentGroupMapper;

    @Resource
    SystemConfig systemConfig;


    @Override
    public ResposeResult add_teacher(Teacher teacher) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("account", teacher.getAccount());
            if(teacherMapper.exists(queryWrapper))
            {
                throw new Exception();
            }
            teacherMapper.insert(teacher);
            log.info("老师-注册id为" + teacher.getId());

        }catch (Exception e)
        {
            log.info("该用户已经存在,请重新尝试");
            return new ResposeResult(0, "注册失败");
        }
        return new ResposeResult(1, "注册成功");
    }

    @Override
    public ResposeResult teacher_login(String account, String password) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("account", account);
            Teacher teacher = teacherMapper.selectOne(queryWrapper);
            if(teacher == null)
            {
                throw new Exception();
            }


            if(password.equals(teacher.getPassword()))
            {
                log.info(teacher.getName() + "登录成功");
                return new ResposeResult(1 , "登录成功");
            }
            else {
                throw new Exception();
            }
        }catch (Exception e)
        {
            log.info("登录失败");
            return new ResposeResult(0 , "登录失败");
        }
    }

    public String pwdDecode(String encodePwd) {
        return RsaUtil.rsaDecode(systemConfig.getPwdKey().getPrivateKey(), encodePwd);
    }
    @Override
    public ResposeResult create_course(TeacherClass teacherClass) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("course_name", teacherClass.getCourseName());
            if (teacherClassMapper.exists(queryWrapper)) {
                throw new Exception();
            }
            teacherClassMapper.insert(teacherClass);
            log.info("老师-创建班级id为" + teacherClass.getId());

        } catch (Exception e) {
            log.info("该班级已经存在,请重新尝试");
            return new ResposeResult(0, "创建失败");
        }
        return new ResposeResult(1, "创建成功");
    }

    @Override
    public ResposeResult get_classId_by_name(String course_name) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_name", course_name);
            TeacherClass teacherClass = teacherClassMapper.selectOne(queryWrapper);
            if(teacherClass == null)
            {
                throw new Exception();
            }
            return new ResposeResult(1, "" + teacherClass.getId());
        }catch (Exception e)
        {
            return new ResposeResult(0, "查询失败");
        }
    }

    @Override
    public ResposeResult close_qiandao(String sign_in_title) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sign_in_title", sign_in_title);
            TeacherSignIn teacherSignIn = teacherSignInMapper.selectOne(queryWrapper);
            if(teacherSignIn == null)
            {
                throw new Exception();
            }
            teacherSignIn.setIsValid(0);
            teacherSignInMapper.update(teacherSignIn, queryWrapper);
            return new ResposeResult(1, sign_in_title + "-签到关闭成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "关闭签到失败");
        }
    }

    @Override
    public Teacher get_detail_by_account(String account) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account", account);
            Teacher teacher = teacherMapper.selectOne(queryWrapper);
            if(teacher == null)
            {
                throw new Exception();
            }
//            teacher.setPassword("不可见");
            return teacher;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public String get_invite_code_by_course_name(String course_name) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_name", course_name);
            TeacherClass teacherClass = teacherClassMapper.selectOne(queryWrapper);
            if(teacherClass == null)
            {
                throw new Exception();
            }
            return teacherClass.getClassInviteCode();

        }catch (Exception e)
        {
            return "未查询到";
        }
    }

    @Override
    public String get_courseName_by_id(Integer id) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            TeacherClass teacherClass = teacherClassMapper.selectOne(queryWrapper);
            if(teacherClass == null)
            {
                throw new Exception();
            }
            return teacherClass.getCourseName(); //返回查询结果中的课程名

        }catch (Exception e)
        {
            return "未查询到";
        }
    }


    @Override
    public ResposeResult add_student_points(Integer points, String student_id, String course_name) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_id", student_id);
            queryWrapper.eq("course_name", course_name);
            StudentPoints studentPoints = studentPointsMapper.selectOne(queryWrapper);
            if(studentPoints == null)
            {
                throw new Exception();
            }
            studentPoints.setPoints(studentPoints.getPoints() + points);
            studentPointsMapper.update(studentPoints, queryWrapper);
            return new ResposeResult(1, "加" + points + "分成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "加分失败");
        }
    }

    @Override
    public List<StudentPoints> get_points_sort(String course_name) {
        return studentPointsMapper.get_points_sort(course_name);
    }

    @Override
    public List<Teacher> get_all_teacher() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        List<Teacher> teacherList = teacherMapper.selectList(queryWrapper);
        return teacherList.stream().map(teacher -> {return get_safe_teacher(teacher);}).collect(Collectors.toList());
    }

    @Override
    public ResposeResult<TeacherGroupResult> create_group_task(Group group, Integer teacher_id, String course_name) {
        try {
            Group group1 = groupMapper.get_one(group.getGroupType());
            if(group1 != null)
            {
                throw new Exception();
            }
            groupMapper.insert_one(group.getGroupSize(), group.getGroupType());
            //由于自己完善的方法，所没有自动组装
            group1 = groupMapper.get_one(group.getGroupType());
            TeacherGroup teacherGroup = new TeacherGroup(teacher_id, group1.getId(), course_name);
            QueryWrapper queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("teacher_id", teacher_id);
            queryWrapper1.eq("group_id", group1.getId());
            queryWrapper1.eq("course_name", course_name);
            TeacherGroup teacherGroup1 = teacherGroupMapper.selectOne(queryWrapper1);
            if(teacherGroup1 != null)
            {
                throw new Exception();
            }
            teacherGroupMapper.insert(teacherGroup);
            TeacherGroupResult teacherGroupResult = new TeacherGroupResult(teacherGroup.getGroupId(), group.getGroupSize());
            return new ResposeResult<>(1, "创建分组成功", teacherGroupResult);


        }catch (Exception e)
        {
            return new ResposeResult<>(0, "创建分组失败", null);
        }
    }

    @Override
    public ResposeResult add_group_of_number(StudentGroup studentGroup) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("group_name", studentGroup.getGroupName());
            queryWrapper.eq("teacher_group_id", studentGroup.getTeacherGroupId());
            StudentGroup studentGroup1 = studentGroupMapper.selectOne(queryWrapper);
            if(studentGroup1 != null)
            {
                throw new Exception();
            }
            studentGroupMapper.insert(studentGroup);
            return new ResposeResult(1, "" + studentGroup.getId());

        }catch (Exception e)
        {
            return new ResposeResult(0, "创建分组失败");
        }
    }

    @Override
    public List<TeacherSignIn> get_all_qiandao(Integer teacher_class_id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_class_id", teacher_class_id);
        return teacherSignInMapper.selectList(queryWrapper);
    }

    @Override
    public List<TeacherClass> get_all_class_by_teacher_id(Integer teacher_id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacher_id);
        return teacherClassMapper.selectList(queryWrapper);
    }

    @Override
    public List<StudentGroup> get_all_student_group_by_teacher_group_id(Integer teacher_group_id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_group_id", teacher_group_id);
        return studentGroupMapper.selectList(queryWrapper);
    }

    @Override
    public List<TeacherGroupRes> get_all_teacher_group_task(Integer teacher_id, String course_name) {
        return teacherGroupMapper.getListWithGroupType(teacher_id, course_name);
    }

    @Override
    public Group get_group_by_group_id(Integer group_id) {
        return groupMapper.get_one_by_group_id(group_id);
    }

    public Teacher get_safe_teacher(Teacher teacher)
    {
        teacher.setPassword("不可见");
        return teacher;
    }

    /**
     * 下面是管理端相关的
     */

    @Override
    public PageInfo<Teacher> teacherPage(UserPageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                teacherMapper.teacherPage(requestVM)
        );
    }

    //根据id查询
    @Override
    public Teacher selectById(Integer id){
        return teacherMapper.selectByPrimaryKey(id);
    }

    @Override
    public Teacher getTeacherByName(String name){
        return teacherMapper.getTeacherByName(name);
    }

    @Override
    public int updateByIdFilter(Teacher record){
        return teacherMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public int insertByFilter(Teacher record) {
        return teacherMapper.insertSelective(record);
    }


    @Override
    public Teacher getTeacherById(Integer id){
        return teacherMapper.getTeacherById(id);
    }

    //实现删除的方法
    @Override
    public boolean deleteById(Integer id){
        return this.removeById(id); //直接使用内置的
    }


    @Override
    public ResposeResult delete_teacher_class(Integer teacher_class_id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", teacher_class_id);
        try {
            TeacherClass teacherClass = teacherClassMapper.selectOne(queryWrapper);
            if(teacherClass == null)
            {
                throw new Exception();
            }
            teacherClassMapper.delete(queryWrapper);
            return new ResposeResult(1, "删除成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "删除失败");
        }
    }

    @Override
    public ResposeResult update_teacher_class(Integer teacher_class_id, String course_name)
    {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", teacher_class_id);
            TeacherClass teacherClass = teacherClassMapper.selectOne(queryWrapper);
            if(teacherClass == null){
                throw new Exception();
            }
            QueryWrapper queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("course_name", course_name);
            TeacherClass teacherClass1 = teacherClassMapper.selectOne(queryWrapper1);
            if(teacherClass1 != null)
            {
                throw new Exception();
            }
            teacherClass.setCourseName(course_name);
            teacherClassMapper.update(teacherClass, queryWrapper);
            return new ResposeResult(1, "修改成功");

        }catch (Exception e)
        {
            return new ResposeResult(0, "修改失败");
        }
    }
}




