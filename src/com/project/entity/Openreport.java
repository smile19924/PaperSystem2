package com.project.entity;

import java.util.Date;

public class Openreport {
    private Integer openid;

    private String content;

    private String teachersuggest;

    private String departmentsuggest;

    private Integer status;

    private String teacherno;

    private String studentno;

    private Integer paperid;

    private Date createtime;

    private Date checktime;

    public Integer getOpenid() {
        return openid;
    }

    public void setOpenid(Integer openid) {
        this.openid = openid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTeachersuggest() {
        return teachersuggest;
    }

    public void setTeachersuggest(String teachersuggest) {
        this.teachersuggest = teachersuggest == null ? null : teachersuggest.trim();
    }

    public String getDepartmentsuggest() {
        return departmentsuggest;
    }

    public void setDepartmentsuggest(String departmentsuggest) {
        this.departmentsuggest = departmentsuggest == null ? null : departmentsuggest.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTeacherno() {
        return teacherno;
    }

    public void setTeacherno(String teacherno) {
        this.teacherno = teacherno == null ? null : teacherno.trim();
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno == null ? null : studentno.trim();
    }

    public Integer getPaperid() {
        return paperid;
    }

    public void setPaperid(Integer paperid) {
        this.paperid = paperid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }
}