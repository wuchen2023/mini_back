package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.TaskExam;
import com.web.back.domain.Teacher;
import com.web.back.viewmodel.admin.task.TaskPageRequestVM;
import com.web.back.viewmodel.admin.task.TaskRequestVM;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */

public interface TaskExamService extends BaseService<TaskExam> {

    PageInfo<TaskExam> page(TaskPageRequestVM requestVM);

    void edit(TaskRequestVM model, Teacher teacher);

    TaskRequestVM taskExamToVM(Integer id);

    List<TaskExam> getByGradeLevel(Integer gradeLevel);
}