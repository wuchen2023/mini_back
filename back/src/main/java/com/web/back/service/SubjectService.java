package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.Subject;
import com.web.back.viewmodel.admin.education.SubjectPageRequestVM;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
public interface SubjectService extends BaseService<Subject> {
    List<Subject> getSubjectByLevel(Integer level);

    List<Subject> allSubject();

    Integer levelBySubjectId(Integer id);

    PageInfo<Subject> page(SubjectPageRequestVM requestVM);
}
