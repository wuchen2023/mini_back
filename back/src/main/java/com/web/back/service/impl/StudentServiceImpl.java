package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.back.domain.*;
import com.web.back.domain.result.StudentClassRes;
import com.web.back.mapper.*;
import com.web.back.service.StudentService;
import com.web.back.state.ResposeResult;
import com.web.back.utils.StringUtils;
import com.web.back.utils.bean.BeanValidators;
import com.web.back.viewmodel.admin.stu.StuPageRequestVM;
import com.web.back.viewmodel.admin.user.UserPageRequestVM;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Validator;

/**
 * @author Dell
 * @description 针对表【student】的数据库操作Service实现
 * @createDate 2023-05-07 13:10:05
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherClassMapper teacherClassMapper;

    @Resource
    StudentClassMapper studentClassMapper;

    @Resource
    TeacherSignInMapper teacherSignInMapper;

    @Resource
    StudentPointsMapper studentPointsMapper;

    @Resource
    GroupingMapper groupingMapper;

    @Resource
    StudentSignInMapper studentSignInMapper;
    @Resource
    protected Validator validator;

    @Override
    public ResposeResult add_student(Student student) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("account", student.getAccount());
            if (studentMapper.exists(queryWrapper)) {
                throw new Exception();
            }
            studentMapper.insert(student);
            log.info("学生-注册id为" + student.getId());

        } catch (Exception e) {
            log.info("该用户已经存在,请重新尝试");
            return new ResposeResult(0, "注册失败");
        }
        return new ResposeResult(1, "注册成功");
    }

    @Override
    public ResposeResult student_login(String account, String password) {
        try {
            Student student = studentMapper.getStuByAccount(account);
            if (student == null) {
                throw new Exception();
            }
            if (password.equals(student.getPassword())) {
                log.info(student.getName() + "登录成功");
                return new ResposeResult(1, "登录成功");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.info("登录失败");
            return new ResposeResult(0, "登录失败");
        }
    }

    @Override
    public ResposeResult add_course(Integer student_id, String invite_code) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("class_invite_code", invite_code);
            TeacherClass teacherClass = teacherClassMapper.selectOne(queryWrapper);
            QueryWrapper queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("student_id", student_id);
            queryWrapper1.eq("class_invite_code", invite_code);
            StudentClass studentClass = studentClassMapper.selectOne(queryWrapper1);
            //第一步验证，这个班级是否存在
            if (teacherClass == null) {
                throw new Exception();
            }
            //第二步，验证这个学生是否已经加入这个班级
            if (studentClass != null) {
                throw new Exception();
            }
            StudentClass studentClass1 = new StudentClass(student_id, teacherClass.getCourseName(), invite_code);
            studentClassMapper.insert(studentClass1);
            teacherClass.setCourseStudentCount(teacherClass.getCourseStudentCount() + 1);
            teacherClassMapper.update(teacherClass, queryWrapper);
            log.info("id为" + student_id + "学生加入" + teacherClass.getCourseName());
            //更新积分表
            QueryWrapper queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("student_id", student_id);
            queryWrapper2.eq("course_name", teacherClass.getCourseName());
            StudentPoints studentPoints = studentPointsMapper.selectOne(queryWrapper2);
            if (studentPoints != null) {
                throw new Exception();
            }
            StudentPoints studentPoints1 = new StudentPoints(student_id, teacherClass.getCourseName(), 0);
            studentPointsMapper.insert(studentPoints1);
        } catch (Exception e) {
            log.info("加入班级失败");
            return new ResposeResult(0, "加入班级失败");
        }
        return new ResposeResult(1, "加入班级成功");
    }

    @Override
    public Integer get_teacher_sign_in_id(String sign_in_title) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sign_in_title", sign_in_title);
            TeacherSignIn teacherSignIn = teacherSignInMapper.selectOne(queryWrapper);
            if (teacherSignIn == null) {
                throw new Exception();
            }
            return teacherSignIn.getId();
        } catch (Exception e) {
            //表示未查询到签到
            return -1;
        }
    }

    @Override
    public Student get_detail_by_account(String account) {
        try {
            Student student = studentMapper.getStuByAccount(account);
            if (student == null) {
                throw new Exception();
            }
            student.setPassword("不可见");
            return student;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Student> get_all_students() {
        List<Student> studentList = studentMapper.getall();
        return studentList.stream().map(student -> {
            return get_safe_teacher(student);
        }).collect(Collectors.toList());
    }

    public Student get_safe_teacher(Student student) {
        student.setPassword("不可见");
        return student;
    }

    @Override
    public Student student_id_get_name(Integer student_id) {
        try {
//            QueryWrapper queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("id", student_id);
//            Student student = studentMapper.selectOne(queryWrapper);
            Student student = studentMapper.getStuById(student_id);
            if (student == null) {
                throw new Exception();
            }
            log.info("依据学生id查询到的学生姓名为：" + student.getName());
            return student;
        } catch (Exception e) {
            return null;
        }
    }


    @Resource
    GroupStateMapper groupStateMapper;

    @Override
    public ResposeResult add_group(Grouping grouping, Integer group_number, Integer group_id) {
        try {
            //进行加入组关联，对于一个学生，关联一个任务
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_id", grouping.getStudentId());
            queryWrapper.eq("group_id", group_id);
            GroupState groupState = groupStateMapper.selectOne(queryWrapper);
            if (groupState != null) {
                throw new Exception();
            }
            Grouping grouping1 = groupingMapper.get_one(grouping.getStudentId(), grouping.getStudentGroupId());
            List<Grouping> groupingList = groupingMapper.get_all(grouping.getStudentGroupId());
            //判断小组人数是否已经满了
            if (groupingList.size() == group_number) {
                throw new Exception();
            }
            if (grouping1 != null) {
                throw new Exception();
            }
            //分组更新
            groupingMapper.insert_one(grouping.getStudentId(), grouping.getStudentGroupId());
            //状态更新
            groupStateMapper.insert(new GroupState(grouping.getStudentId(), group_id));
            return new ResposeResult(1, "加入成功!");
        } catch (Exception e) {
            return new ResposeResult(0, "加入失败!");
        }
    }

    @Override
    public ResposeResult exit_group(Integer student_id, Integer student_group_id, Integer group_id) {
        try {
            //查询状态
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_id", student_id);
            queryWrapper.eq("group_id", group_id);
            GroupState groupState = groupStateMapper.selectOne(queryWrapper);
            if (groupState == null) {
                throw new Exception();
            }
            Grouping grouping = groupingMapper.get_one(student_id, student_group_id);
            if (grouping == null) {
                throw new Exception();
            }
            groupingMapper.delete_one(student_id, student_group_id);
            groupStateMapper.delete(queryWrapper);
            return new ResposeResult(1, "退出成功");
        } catch (Exception e) {
            return new ResposeResult(0, "退出失败");
        }
    }


    @Override
    public ResposeResult get_qiandao_state(Integer student_id, Integer teacher_sign_in_id) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_id", student_id);
            queryWrapper.eq("teacher_sign_in_id", teacher_sign_in_id);
            StudentSignIn studentSignIn = studentSignInMapper.selectOne(queryWrapper);
            if (studentSignIn == null) {
                throw new Exception();
            }
            return new ResposeResult(1, "已经签到成功");

        } catch (Exception e) {
            return new ResposeResult(0, "未签到，请及时签到");
        }
    }

    @Override
    public List<Grouping> get_all_group_member(Integer student_group_id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_group_id", student_group_id);
        return groupingMapper.get_all(student_group_id);
    }

    @Override
    public List<StudentClassRes> get_all_class_by_student_id(Integer student_id) {
        return studentClassMapper.get_all_class(student_id);
    }

    @Override
    public List<List<Student>> get_qiandao_detail(String course_name, Integer teacher_sign_in_id) {
        //当前班级的全部学生
        List<Student> student_class_list = studentClassMapper.get_all_student_in_class(course_name);
        //获取已经签到的学生
        List<Student> sign_in_student_list = studentSignInMapper.get_all_student_has_sign_in(teacher_sign_in_id);

        List<Student> no_sign_list = new ArrayList<>(student_class_list); // 创建一个新的列表
        no_sign_list.removeAll(sign_in_student_list); // 从新的列表中移除已经签到的学生

        //密码不可见
        sign_in_student_list.stream().map(student -> {
            return get_safe_teacher(student);
        }).collect(Collectors.toList());
        no_sign_list.stream().map(student -> {
            return get_safe_teacher(student);
        }).collect(Collectors.toList());

        List<List<Student>> result = new ArrayList<>();
        result.add(sign_in_student_list);
        result.add(no_sign_list);
        return result;

    }

    /**
     * 下面是管理端相关的
     */

    @Override
    public PageInfo<Student> studentPage(StuPageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                studentMapper.studentPage(requestVM)
        );
    }


    @Override
    public Student getStuByStuName(String name) {
        return studentMapper.getStuByStuName(name);
    }

    @Override
    public String getStuNameByStuAccount(String stuAccount) {
        return studentMapper.getStuNameByStuAccount(stuAccount);

    }

    //重写
    @Override
    public int updateByIdFilter(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertByFilter(Student record) {
        return studentMapper.insertSelective(record);
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.removeById(id); //直接使用内置的
    }

    @Override
    public String importUser(List<Student> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
//        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (Student student : userList) {
            try {
                // 验证是否存在这个用户
                Student u = studentMapper.getStuByStuName(student.getName());
                if (StringUtils.isNull(u)) {
                    BeanValidators.validateWithException(validator, student);
//                    user.setPassword(SecurityUtils.encryptPassword(password));
//                    user.setCreateBy(operName);
                    student.setRole(1);
                    student.setPassword(student.getAccount().substring(student.getAccount().length() - 6));
//                    QueryWrapper queryWrapper = new QueryWrapper<>();
//                    queryWrapper.eq("name", student.getName());
                    Student student1 = studentMapper.getStuByStuName(student.getName());
                    if (student1 != null) {
                        studentMapper.updateByPrimaryKeySelective(student);
                    } else {
                        studentMapper.insertSelective(student);
                    }

                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getName() + " 导入成功");
                } else if (u.getId() != null) {
//                    BeanValidators.validateWithException(validator, student);
//                    checkUserAllowed(u);
//                    checkUserDataScope(u.getUserId());
//                    student.setId(u.getId());
//                    user.setUpdateBy(operName);
                    student.setPassword(student.getAccount().substring(student.getAccount().length() - 6));
                    studentMapper.updateByPrimaryKeySelective(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + student.getName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + student.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public List<Student> selectStudentList(Student student) {
        return studentMapper.selectStudentList(student);
    }

    @Override
    public List<Student> newselectStudentList(Student student){
        return studentMapper.newselectStudentList(student);
    }

}




