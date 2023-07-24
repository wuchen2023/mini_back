package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Qiangda;
import com.web.back.service.QiangdaService;
import com.web.back.mapper.QiangdaMapper;
import com.web.back.state.ResposeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Dell
* @description 针对表【qiangda】的数据库操作Service实现
* @createDate 2023-07-16 11:38:22
*/
@Service
public class QiangdaServiceImpl extends ServiceImpl<QiangdaMapper, Qiangda>
    implements QiangdaService{

    @Resource
    QiangdaMapper qiangdaMapper;

    @Override
    public ResposeResult add_qiangda(Qiangda qiangda) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("teacher_id", qiangda.getTeacherId());
            queryWrapper.eq("qiangda_type", qiangda.getQiangdaType());
            queryWrapper.eq("course_name", qiangda.getCourseName());
            Qiangda qiangda1 = qiangdaMapper.selectOne(queryWrapper);
            if(qiangda1 != null)
            {
                throw new Exception();
            }
            qiangdaMapper.insert(qiangda);
            return new ResposeResult(1, "添加抢答成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "添加抢答失败");
        }
    }

    @Override
    public List<Qiangda> get_all_qiangda(Integer teacher_id, String course_name) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacher_id);
        queryWrapper.eq("course_name", course_name);
//        List<Qiangda> list = qiangdaMapper.selectList(queryWrapper);
//        System.out.println(list);
        return qiangdaMapper.selectList(queryWrapper);
    }

    @Override
    public List<Qiangda> get_all_qiangda1(Integer teacher_id, String course_name) {
        return qiangdaMapper.find_info(course_name, teacher_id);
    }

    @Override
    public ResposeResult get_qiangda_state(Integer qiangda_id) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", qiangda_id);
            Qiangda qiangda = qiangdaMapper.selectOne(queryWrapper);
            if(qiangda == null)
            {
                throw new Exception();
            }
            if(qiangda.getIsFinished() == 0)
            {
                return new ResposeResult(1, "抢答还在继续");
            }else
            {
                return new ResposeResult(1, "抢答已经结束");
            }

        }catch (Exception e)
        {
            return new ResposeResult(0, "查询失败");
        }
    }

    @Override
    public ResposeResult close_qiangda(Integer qiangda_id) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", qiangda_id);
            Qiangda qiangda = qiangdaMapper.selectOne(queryWrapper);
            if(qiangda == null || qiangda.getIsFinished() == 1)
            {
                throw new Exception();
            }
            qiangda.setIsFinished(1);
            qiangdaMapper.update(qiangda, queryWrapper);
            return new ResposeResult(1, "关闭成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "关闭失败");
        }
    }

    @Override
    public ResposeResult qiangda_is_right(Integer qiangda_id, String is_right){
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", qiangda_id);
            Qiangda qiangda = qiangdaMapper.selectOne(queryWrapper);
            if(qiangda ==null){
                throw new Exception();
            }
            Qiangda qiangda1 = new Qiangda(qiangda_id, is_right);
            qiangdaMapper.update(qiangda1,queryWrapper);
            return new ResposeResult(1, "提交成功");


        }catch (Exception e){
            return new ResposeResult(0, "提交失败");
        }
    }

}




