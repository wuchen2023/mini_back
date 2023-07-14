package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Pk;
import com.web.back.domain.StudentClass;
import com.web.back.domain.result.PkRes;
import com.web.back.mapper.StudentClassMapper;
import com.web.back.mapper.StudentMapper;
import com.web.back.service.PkService;
import com.web.back.mapper.PkMapper;
import com.web.back.state.ResposeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

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

    @Override
    public ResposeResult add_pk(Integer activity_id, String course_name) {
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
            Pk pk1 = new Pk(activity_id, student_id_1, student_id_2, 0, course_name);
            pkMapper.insert(pk1);
            return new ResposeResult(1, "创建Pk成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "创建Pk失败");
        }
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
}




