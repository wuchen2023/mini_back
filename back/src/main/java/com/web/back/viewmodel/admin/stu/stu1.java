package com.web.back.viewmodel.admin.stu;

/**
 * @author by hongdou
 * @date 2023/8/10.
 * @DESC:
 */
public class stu1 {
    private int pageNum;
    private int pageSize;
    private String name;

    private String account;

    private Long subjectId;

    private int role;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }


    public void setAccount(String account) {
        this.account = account;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
