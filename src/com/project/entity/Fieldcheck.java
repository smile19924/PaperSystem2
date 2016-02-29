package com.project.entity;

public class Fieldcheck {
    private Integer fieldid;

    private String tablename;

    private String fieldname;

    private Byte fieldvalue;

    private String fielddesc;

    public Integer getFieldid() {
        return fieldid;
    }

    public void setFieldid(Integer fieldid) {
        this.fieldid = fieldid;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname == null ? null : fieldname.trim();
    }

    public Byte getFieldvalue() {
        return fieldvalue;
    }

    public void setFieldvalue(Byte fieldvalue) {
        this.fieldvalue = fieldvalue;
    }

    public String getFielddesc() {
        return fielddesc;
    }

    public void setFielddesc(String fielddesc) {
        this.fielddesc = fielddesc == null ? null : fielddesc.trim();
    }
}