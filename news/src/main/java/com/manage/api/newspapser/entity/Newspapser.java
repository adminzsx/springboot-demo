package com.manage.api.newspapser.entity;

public class Newspapser {
    private Integer id;

    private String url;

    private String filename;

    private String extendvarchar;

    private Integer extendint;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExtendvarchar() {
        return extendvarchar;
    }

    public void setExtendvarchar(String extendvarchar) {
        this.extendvarchar = extendvarchar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getExtendint() {
        return extendint;
    }

    public void setExtendint(Integer extendint) {
        this.extendint = extendint;
    }
}