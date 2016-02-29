package com.project.entity;

import java.util.Date;

public class Paper {
    private Integer paperid;

    private String studentno;

    private String designname;

    private String papername;

    private Integer type;

    private Integer source;

    private Integer reason;

    private String departmentsuggest;

    private Integer isone;

    private Integer status;

    private Date createtime;

    private Date checktime;

    private String teacherno;

    public Integer getPaperid() {
        return paperid;
    }

    public void setPaperid(Integer paperid) {
        this.paperid = paperid;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno == null ? null : studentno.trim();
    }

    public String getDesignname() {
        return designname;
    }

    public void setDesignname(String designname) {
        this.designname = designname == null ? null : designname.trim();
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername == null ? null : papername.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getDepartmentsuggest() {
        return departmentsuggest;
    }

    public void setDepartmentsuggest(String departmentsuggest) {
        this.departmentsuggest = departmentsuggest == null ? null : departmentsuggest.trim();
    }

    public Integer getIsone() {
        return isone;
    }

    public void setIsone(Integer isone) {
        this.isone = isone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getTeacherno() {
        return teacherno;
    }

    public void setTeacherno(String teacherno) {
        this.teacherno = teacherno == null ? null : teacherno.trim();
    }
}