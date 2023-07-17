package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.*;
import com.web.back.mapper.*;
import com.web.back.service.DebateService;
import com.web.back.service.StudentGroupService;
import com.web.back.service.StudentService;
import com.web.back.service.TeacherService;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.admin.debate1.StudentInfoVM;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
* @author Dell
* @description 针对表【debate】的数据库操作Service实现
* @createDate 2023-05-07 13:09:49
*/
@Service
public class DebateServiceImpl extends ServiceImpl<DebateMapper, Debate>
    implements DebateService {
    @Resource
    DebateMapper debateMapper;

    @Resource
    StudentGroupService studentGroupService;

    @Resource
    TeacherGroupMapper teacherGroupMapper;

    @Resource
    StudentService studentService;

    @Resource
    DebateVoteMapper debateVoteMapper;

    @Override
    public List<Object> add_debateTopic(Integer activityId, Integer teacher_id, String course_name, String debateTopic, Integer group_id){
        //首先插入数据到数据表
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("activity_id", activityId);
            queryWrapper.eq("course_name", course_name);
            queryWrapper.eq("debate_topic", debateTopic);
            Debate debate = debateMapper.selectOne(queryWrapper);
            if(debate !=null){
                throw new Exception();
            }
            System.out.println("进行到这里");
            //先根据老师id和groupid查出teacher_group_id
            QueryWrapper queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("teacher_id", teacher_id);
            queryWrapper1.eq("group_id", group_id);
            TeacherGroup teacherGroup = teacherGroupMapper.selectOne(queryWrapper1);
            System.out.println("获得的teacher_group_id为："+teacherGroup.getGroupId());
            List<Integer> stugroupIds = studentGroupService.findAllStuGroupIds(teacherGroup.getGroupId());
            if(stugroupIds.size()>=2){
                System.out.println("stu_group已有的id为：" + stugroupIds);
                List<Integer> ids = pickTwoRandomNumbers(stugroupIds);
                System.out.println("随机选择的stu_group的两个id为："+ids);
                Debate debate1 = new Debate(activityId, 0, ids.get(0), ids.get(1), course_name,debateTopic);
                debateMapper.insert(debate1); //插入debate中
                //根据两个组号，查询grouping表中该组的学生，然后返回学生信息的列表

//                List<Grouping> students = studentService.get_all_group_member(ids.get(0));
//                System.out.println("获取到的组中的学生信息："+students);
//                List<Grouping> students1 = studentService.get_all_group_member(ids.get(1));
//                System.out.println("获取到的组中的学生信息："+students1);
               List<Object>  li= new ArrayList<>();
                li.add(Collections.singletonList(studentService.get_all_group_member(ids.get(0))));
                li.add(Collections.singletonList(studentService.get_all_group_member(ids.get(1))));

                li.add(Collections.singletonList(debate1.getId()));
                //在debate_vote中插入本次辩论会的数据
                QueryWrapper queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("debate_id",debate1.getId());
                queryWrapper2.eq("stu_group_id",ids.get(0));
                DebateVote  debateVote = debateVoteMapper.selectOne(queryWrapper2);
                if(debateVote !=null){
                    throw new Exception();
                }
                DebateVote debateVote1 = new DebateVote(debate1.getId(), ids.get(0), 0);
                debateVoteMapper.insert(debateVote1);

                QueryWrapper queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("debate_id",debate1.getId());
                queryWrapper3.eq("stu_group_id",ids.get(1));
                DebateVote  debateVote2 = debateVoteMapper.selectOne(queryWrapper3);
                if(debateVote2 !=null){
                    throw new Exception();
                }
                DebateVote debateVote3 = new DebateVote(debate1.getId(), ids.get(1), 0);
                debateVoteMapper.insert(debateVote3);

                System.out.println("debate_vote相关数据已插入");
                return li;
//                return new ResposeResult(1, "创建debate成功");
            }else{
                return null;
            }

        }catch (Exception e){
            return null;
        }
    }

    public static List<Integer> pickTwoRandomNumbers(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();

        // 检查给定列表是否包含足够的数
        if (numbers.size() < 2) {
            throw new IllegalArgumentException("列表中的元素数量不足");
        }

        // 使用随机数生成器选择两个不同的索引
        Random random = new Random();
        int index1 = random.nextInt(numbers.size());
        int index2;

        // 通过循环确保选择两个不同的索引
        do {
            index2 = random.nextInt(numbers.size());
        } while (index1 == index2);

        // 根据索引选取对应的数，并将它们添加到结果列表
        result.add(numbers.get(index1));
        result.add(numbers.get(index2));

        return result;
    }

//    public static List<StudentInfoVM> mergeLists(List<StudentInfoVM> list1, List<StudentInfoVM> list2, String debateTopic) {
//        List<StudentInfoVM> mergedList = new ArrayList<>();
//
//        mergedList.addAll(list1);
//        mergedList.addAll(list2);
//
//
//
//        return mergedList;
//    }

    @Override
    public ResposeResult close_debate(Integer debate_id){
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", debate_id);
            Debate debate = debateMapper.selectOne(queryWrapper);
            if(debate == null || debate.getIsFinished() ==1){
                throw new Exception();
            }
            debate.setIsFinished(1);
            debateMapper.update(debate, queryWrapper);
            return new ResposeResult(1, "关闭成功");

        }catch (Exception e){
            return new ResposeResult(0, "关闭失败");
        }
    }


    @Override
    public ResposeResult get_debate_state(Integer debate_id){
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", debate_id);
            Debate debate = debateMapper.selectOne(queryWrapper);
            if(debate == null)
            {
                throw new Exception();
            }
            if(debate.getIsFinished() == 1)
            {
                return new ResposeResult(1, "debate已经结束");
            }else
            {
                return new ResposeResult(1, "debate还在进行中");
            }
        }catch (Exception e)
        {
            return new ResposeResult(0, "查询失败");
        }
    }


    @Override
    public List<Debate> get_all_debate(String course_name){
       return debateMapper.select_by_coursename(course_name);
    }

}




