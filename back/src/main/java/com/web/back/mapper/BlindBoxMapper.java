package com.web.back.mapper;

import com.web.back.domain.BlindBox;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.DebateVote;
import com.web.back.domain.ExamPaper;
import com.web.back.viewmodel.admin.blindbox.PageInfoBlindBoxRequestVM;
import com.web.back.viewmodel.student.exam.ExamPaperPageVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【blind_box】的数据库操作Mapper
* @createDate 2023-05-07 13:09:42
* @Entity generator.domain.BlindBox
*/
@Mapper
public interface BlindBoxMapper extends BaseMapper<BlindBox> {

    List<BlindBox> findInfo(String stu_account,Integer exam_paper_id);

    BlindBox findInfoById(Integer id);

    List<BlindBox> view_result(String stuAccount);
    List<BlindBox> blindBoxPage(PageInfoBlindBoxRequestVM requestVM);


}




