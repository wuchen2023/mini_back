package com.web.back.service;

import com.web.back.domain.PkWiner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.result.PkRes;
import com.web.back.domain.result.PkWinnerRes;
import com.web.back.state.ResposeResult;

/**
* @author Dell
* @description 针对表【pk_winer】的数据库操作Service
* @createDate 2023-07-14 16:41:02
*/
public interface PkWinerService extends IService<PkWiner> {

    public ResposeResult add_pk_winner(Integer student_id, Integer activity_id);

    public PkWinnerRes get_winner(Integer activity_id);

}
