package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.PkWiner;
import com.web.back.domain.result.PkWinnerRes;
import com.web.back.service.PkWinerService;
import com.web.back.mapper.PkWinerMapper;
import com.web.back.state.ResposeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Dell
* @description 针对表【pk_winer】的数据库操作Service实现
* @createDate 2023-07-14 16:41:02
*/
@Service
public class PkWinerServiceImpl extends ServiceImpl<PkWinerMapper, PkWiner>
    implements PkWinerService{


    @Resource
    PkWinerMapper pkWinerMapper;

    @Override
    public ResposeResult add_pk_winner(Integer student_id, Integer activity_id) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_id", student_id);
            queryWrapper.eq("activity_id", activity_id);

            PkWiner pkWiner = pkWinerMapper.selectOne(queryWrapper);
            if(pkWiner != null)
            {
                throw new Exception();
            }

            PkWiner pkWiner1 = new PkWiner(student_id, activity_id);
            pkWinerMapper.insert(pkWiner1);
            return new ResposeResult(1, "添加winner成功");
        }catch (Exception e)
        {
            return new ResposeResult(0, "添加winner失败");
        }
    }

    @Override
    public PkWinnerRes get_winner(Integer activity_id) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("activity_id", activity_id);
            PkWiner pkWiner = pkWinerMapper.selectOne(queryWrapper);
            if(pkWiner == null)
            {
                throw new Exception();
            }
            PkWinnerRes pkWinnerRes = pkWinerMapper.get_winner(activity_id);
            return pkWinnerRes;
        }catch (Exception e)
        {
            return null;
        }
    }
}




