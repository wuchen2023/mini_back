package com.web.back.service;

import com.web.back.domain.Qiangda;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.state.ResposeResult;

import java.util.List;

/**
* @author Dell
* @description 针对表【qiangda】的数据库操作Service
* @createDate 2023-07-16 11:38:22
*/
public interface QiangdaService extends IService<Qiangda> {
    public ResposeResult add_qiangda(Qiangda qiangda);

    public List<Qiangda> get_all_qiangda(Integer teacher_id, String course_name);
    public List<Qiangda> get_all_qiangda1(Integer teacher_id, String course_name);

    public ResposeResult get_qiangda_state(Integer qiangda_id);

    public ResposeResult close_qiangda(Integer qiangda_id);

    public ResposeResult qiangda_is_right(Integer qiangda_id, String is_right);

}
