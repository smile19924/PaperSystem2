package com.project.entity;

import java.util.Date;

public class Progressreport {
    private Integer progressid;

    private String progresscontent;

    private String teachersuggest;

    private Integer isfinished;

    private Integer attitude;

    private Integer suggestresult;

    private Integer quality;

    private Integer status;

    private String studentno;

    private Date createtime;

    private Date checktime;

    private String teacherno;

    public Integer getProgressid() {
        return progressid;
    }

    public void setProgressid(Integer progressid) {
        this.progressid = progressid;
    }

    public String getProgresscontent() {
        return progresscontent;
    }

    public void setProgresscontent(String progresscontent) {
        this.progresscontent = progresscontent == null ? null : progresscontent.trim();
    }

    public String getTeachersuggest() {
        return teachersuggest;
    }

    public void setTeachersuggest(String teachersuggest) {
        this.teachersuggest = teachersuggest == null ? null : teachersuggest.trim();
    }

    public Integer getIsfinished() {
        return isfinished;
    }

    public void setIsfinished(Integer isfinished) {
        this.isfinished = isfinished;
    }

    public Integer getAttitude() {
        return attitude;
    }

    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
    }

    public Integer getSuggestresult() {
        return suggestresult;
    }

    public void setSuggestresult(Integer suggestresult) {
        this.suggestresult = suggestresult;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno == null ? null : studentno.trim();
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