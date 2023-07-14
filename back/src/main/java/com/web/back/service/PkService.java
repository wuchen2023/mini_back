package com.web.back.service;

import com.web.back.domain.Pk;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.result.PkRes;
import com.web.back.state.ResposeResult;

import java.util.List;

/**
* @author Dell
* @description 针对表【pk】的数据库操作Service
* @createDate 2023-05-07 13:10:00
*/
public interface PkService extends IService<Pk> {

    public ResposeResult add_pk(Integer activity_id, String course_name);

    public ResposeResult close_pk(Integer activity_id, String course_name);

    public List<PkRes> get_all_pk(String course_name);

    public ResposeResult get_pk_state(Integer activity_id, String course_name);

}
