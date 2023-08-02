package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.BlindBox;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.ExamPaper;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.admin.blindbox.PageInfoBlindBoxRequestVM;
import com.web.back.viewmodel.student.exam.ExamPaperPageVM;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author Dell
* @description 针对表【blind_box】的数据库操作Service
* @createDate 2023-05-07 13:09:42
*/
public interface BlindBoxService extends IService<BlindBox> {

    public ResposeResult blindbox_answerSubmit(Integer blindBoxID,  Integer is_right,String stu_answer,String true_answer);

    public List<BlindBox> blindbox_view(String stu_account, Integer exam_paper_id);


    public List<BlindBox> view_result(String stuAccount);

    PageInfo<BlindBox> blindBoxPage(PageInfoBlindBoxRequestVM requestVM);
}
