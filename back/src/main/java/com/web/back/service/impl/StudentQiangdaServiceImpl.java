package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Qiangda;
import com.web.back.domain.StudentQiangda;
import com.web.back.domain.result.StudentQiangDaRes;
import com.web.back.mapper.QiangdaMapper;
import com.web.back.service.StudentQiangdaService;
import com.web.back.mapper.StudentQiangdaMapper;
import com.web.back.state.ResposeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Dell
* @description 针对表【student_qiangda】的数据库操作Service实现
* @createDate 2023-07-16 12:10:05
*/
@Service
public class StudentQiangdaServiceImpl extends ServiceImpl<StudentQiangdaMapper, StudentQiangda>
    implements StudentQiangdaService{

    @Resource
    QiangdaMapper qiangdaMapper;

    @Resource
    StudentQiangdaMapper studentQiangdaMapper;

    @Override
    public ResposeResult add_student_qiangda(StudentQiangda studentQiangda) {
        try{
            //检验抢答是否关闭
            Integer qiangda_id = studentQiangda.getQiangdaId();
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", qiangda_id);
            Qiangda qiangda = qiangdaMapper.selectOne(queryWrapper);
            if(qiangda == null || qiangda.getIsFinished() == 1)
            {
                throw new Exception();
            }
            //进行抢答
            QueryWrapper queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("qiangda_id", studentQiangda.getQiangdaId());
            queryWrapper1.eq("course_name", studentQiangda.getCourseName());
            StudentQiangda studentQiangda1 = studentQiangdaMapper.selectOne(queryWrapper1);
            if(studentQiangda1 != null)
            {
                throw new Exception();
            }
            //关闭抢答
            qiangda.setIsFinished(1);
            qiangdaMapper.update(qiangda, queryWrapper);
            //更新抢答
            studentQiangdaMapper.insert(studentQiangda);
            return new ResposeResult(1, "抢答成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "抢答失败");
        }
    }

    @Override
    public StudentQiangDaRes get_qiangda_res(Integer qiangda_id, String course_name) {
        return studentQiangdaMapper.get_qiangda_res(qiangda_id, course_name);
    }
}




