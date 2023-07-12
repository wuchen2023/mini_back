package com.web.back.mapper;

import com.web.back.domain.ExamPaper;
import com.web.back.domain.other.KeyValue;
import com.web.back.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.web.back.viewmodel.student.exam.ExamPaperPageVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/21.
 * @DESC:
 */
@Mapper
public interface ExamPaperMapper extends BaseMapper<ExamPaper>{
    List<ExamPaper> page(ExamPaperPageRequestVM requestVM);

    List<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);

//    List<ExamPaper> studentPage(ExamPaperPageVM requestVM);

//    List<PaperInfo> indexPaper(PaperFilter paperFilter);

    Integer selectAllCount();

    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int updateTaskPaper(@Param("taskId") Integer taskId,@Param("paperIds") List<Integer> paperIds);

    int clearTaskPaper(@Param("paperIds") List<Integer> paperIds);

    List<ExamPaper> examPage(ExamPaperPageVM requestVM);
}
