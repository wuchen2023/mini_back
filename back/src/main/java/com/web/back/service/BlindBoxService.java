package com.web.back.service;

import com.web.back.domain.BlindBox;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.state.ResposeResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author Dell
* @description 针对表【blind_box】的数据库操作Service
* @createDate 2023-05-07 13:09:42
*/
public interface BlindBoxService extends IService<BlindBox> {

    public ResposeResult blindbox_answerSubmit(String stu_account, Integer exam_paper_id,  Integer is_right,  String true_answer, String teacher_account, String class_name);

    public List<BlindBox> blindbox_view(String stu_account, Integer exam_paper_id);

    public List<BlindBox> blindbox_view_history(String class_name, String stu_account, String teacher_account);
}
