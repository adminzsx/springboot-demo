package com.manage.webmgr.user.entity;

import java.io.Serializable;

public class Newspapser implements Serializable {
    private Integer fId;

    private String fUrl;

    private Integer fType;

    private String fFileName;

    private Integer fExtendInt;

    private String fExtendVarchar;

    private static final long serialVersionUID = 1L;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfUrl() {
        return fUrl;
    }

    public void setfUrl(String fUrl) {
        this.fUrl = fUrl == null ? null : fUrl.trim();
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public String getfFileName() {
        return fFileName;
    }

    public void setfFileName(String fFileName) {
        this.fFileName = fFileName == null ? null : fFileName.trim();
    }

    public Integer getfExtendInt() {
        return fExtendInt;
    }

    public void setfExtendInt(Integer fExtendInt) {
        this.fExtendInt = fExtendInt;
    }

    public String getfExtendVarchar() {
        return fExtendVarchar;
    }

    public void setfExtendVarchar(String fExtendVarchar) {
        this.fExtendVarchar = fExtendVarchar == null ? null : fExtendVarchar.trim();
    }
}