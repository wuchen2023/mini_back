package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.*;
import com.web.back.domain.result.PkRes;
import com.web.back.mapper.ActivityItemMapper;
import com.web.back.mapper.StudentClassMapper;
import com.web.back.mapper.StudentMapper;
import com.web.back.service.ExamPaperService;
import com.web.back.service.PkService;
import com.web.back.mapper.PkMapper;
import com.web.back.service.QuestionService;
import com.web.back.service.StudentService;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import com.web.back.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.web.back.viewmodel.admin.exam.ExamPaperTitleItemVM;
import com.web.back.viewmodel.admin.question.QuestionEditRequestVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
* @author Dell
* @description 针对表【pk】的数据库操作Service实现
* @createDate 2023-05-07 13:10:00
*/
@Service
public class PkServiceImpl extends ServiceImpl<PkMapper, Pk>
    implements PkService{


    @Resource
    PkMapper pkMapper;

    @Resource
    StudentClassMapper studentClassMapper;

    @Resource
    QuestionService questionService;

    @Resource
    StudentMapper studentMapper;

    @Resource
    StudentService studentService;

    @Resource
    ExamPaperService examPaperService;

    @Resource
    ActivityItemMapper activityItemMapper;
    @Override
    public List<Integer> add_pk(Integer activity_id, String course_name) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("activity_id", activity_id);
            queryWrapper.eq("course_name", course_name);
            Pk pk = pkMapper.selectOne(queryWrapper);
            if(pk != null)
            {
                throw new Exception();
            }
            QueryWrapper queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("class_name", course_name);
            //获取随机的当前班级的两个学生
            List<StudentClass> studentClassList = studentClassMapper.selectList(queryWrapper1);

            if(studentClassList.size() < 2)
            {
                throw new Exception();
            }
            Collections.shuffle(studentClassList);
            Integer student_id_1 = studentClassList.get(0).getStudentId();
            Integer student_id_2 = studentClassList.get(1).getStudentId();
            System.out.println(student_id_1 +  " " + student_id_2);
            Pk pk1 = new Pk(activity_id, student_id_1, student_id_2, 0, course_name);
            pkMapper.insert(pk1);
            /**
             * 以上抽到两个学生的id号，下面抽取10道题目组成一套试卷，分别设置到两个学生下面
             * 所有题目类型都有
             */
            List<Integer> questionNumbers = questionService.findAllQuestionIds();
            if(questionNumbers.size()<10){
                System.out.println("题目数量少于10道，请添加更多题目");
                return null;
//                return new ResposeResult(0,"题目数量少于10道，请添加更多题目");
            }else{
                //两个学生需要创建两套试卷
                ExamPaperEditRequestVM examPaperEditRequestVM1 = new ExamPaperEditRequestVM();
                ExamPaperEditRequestVM examPaperEditRequestVM2 = new ExamPaperEditRequestVM();
                examPaperEditRequestVM1.setSubjectId(1); //subject对照课程名(课程表里面新增关联的subjectid，依据传入的课程名查询到subjectid，然后写入)
                examPaperEditRequestVM2.setSubjectId(1);
                examPaperEditRequestVM1.setPaperType(1);
                examPaperEditRequestVM2.setPaperType(1);
                examPaperEditRequestVM1.setCourseName(course_name);
                examPaperEditRequestVM2.setCourseName(course_name);
                //依据学生id查询学生account
//                QueryWrapper queryWrapper2 = new QueryWrapper<>();
//                queryWrapper2.eq("id", student_id_1);
//                String stu1_account = studentMapper.selectOne(queryWrapper2).getAccount();
//                QueryWrapper queryWrapper3 = new QueryWrapper<>();
//                queryWrapper3.eq("id", student_id_2);
//                String stu2_account = studentMapper.selectOne(queryWrapper3).getAccount();
                String stu1_account = studentMapper.getStuById(student_id_1).getAccount();
                String stu2_account = studentMapper.getStuById(student_id_2).getAccount();
                examPaperEditRequestVM1.setStuAccount(stu1_account);
                examPaperEditRequestVM2.setStuAccount(stu2_account);
                examPaperEditRequestVM1.setName(createNewName(course_name, stu1_account));
                examPaperEditRequestVM2.setName(createNewName(course_name, stu2_account));
                examPaperEditRequestVM1.setSuggestTime(5);
                examPaperEditRequestVM2.setSuggestTime(5);
                List<ExamPaperTitleItemVM> titleItems = new ArrayList<>();
                ExamPaperTitleItemVM item1 = new ExamPaperTitleItemVM();
                item1.setName("题目");
                //下面选择题目
                List<QuestionEditRequestVM> questionList = new ArrayList<>();
                Collections.shuffle(questionNumbers);

                for(int i=0;i<10;i++){
                    Integer randomQuestionNumber = questionNumbers.get(i);
//                            getRandomQuestionNumber(questionNumbers);
                    System.out.println("随机选中的题号是："+randomQuestionNumber);
//                    questionNumbers.remove(randomQuestionNumber);
                    QuestionEditRequestVM questionVM = questionService.getQuestionEditRequestVM(randomQuestionNumber);
                    //设置试卷

                    questionList.add(questionVM);

                }


                item1.setQuestionItems(questionList);

                titleItems.add(item1);
                examPaperEditRequestVM1.setTitleItems(titleItems);
                examPaperEditRequestVM2.setTitleItems(titleItems);
                Student student1 = studentService.get_detail_by_account(stu1_account);
                Student student2 = studentService.get_detail_by_account(stu2_account);
                ExamPaper examPaper1 = examPaperService.savePaperFromVM_pk(examPaperEditRequestVM1, student1);
                ExamPaper examPaper2 = examPaperService.savePaperFromVM_pk(examPaperEditRequestVM2, student2);
                System.out.println("创建examPaper1成功,"+examPaper1.getStuAccount());
                System.out.println("创建examPaper2成功,"+examPaper2.getStuAccount());
                List<Integer> numbers = new ArrayList<>();
                numbers.add(examPaper1.getId());
                numbers.add(examPaper2.getId());
                ActivityItem activityItem = new ActivityItem(activity_id,examPaper1.getId(), examPaper2.getId());
                activityItemMapper.insert(activityItem);

                return numbers;


            }

        }catch (Exception e)
        {
            return null;
        }
    }

    public static Integer getRandomQuestionNumber(List<Integer> questionNumbers) {
        Random random = new Random();
        int randomIndex = random.nextInt(questionNumbers.size()); // 生成随机索引
        return questionNumbers.get(randomIndex); // 返回随机选中的题号
    }


    public  String createNewName(String coursename, String stuaccount) {
        // 生成随机三位数
        Random random = new Random();
        int randomNumber = random.nextInt(900) + 100;
        //新增一个根据课程id，查询到课程名字
//        String courseName = teacherService.get_courseName_by_id(infoclasscontentid);

        // 合并字符串
        String mergedString = "PK_班级" + coursename + "_" + "学生" + stuaccount + "_" + randomNumber;

        System.out.println("合并后的字符串：" + mergedString);
        return mergedString;
    }

    @Override
    public ResposeResult close_pk(Integer activity_id, String course_name) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("activity_id", activity_id);
            queryWrapper.eq("course_name", course_name);
            Pk pk = pkMapper.selectOne(queryWrapper);
            if(pk == null || pk.getIsFinished() == 1)
            {
                throw new Exception();
            }
            pk.setIsFinished(1);
            pkMapper.update(pk, queryWrapper);
            return new ResposeResult(1, "关闭成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "关闭失败");
        }
    }

    @Override
    public List<PkRes> get_all_pk(String course_name) {
        return pkMapper.get_all(course_name);
    }

    @Override
    public ResposeResult get_pk_state(Integer activity_id, String course_name) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("activity_id", activity_id);
            queryWrapper.eq("course_name", course_name);
            Pk pk = pkMapper.selectOne(queryWrapper);
            if(pk == null)
            {
                throw new Exception();
            }
            if(pk.getIsFinished() == 1)
            {
                return new ResposeResult(1, "pk已经结束");
            }else
            {
                return new ResposeResult(1, "pk还在进行中");
            }
        }catch (Exception e)
        {
            return new ResposeResult(0, "查询失败");
        }
    }
}




