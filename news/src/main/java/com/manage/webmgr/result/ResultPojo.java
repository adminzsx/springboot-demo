package com.manage.webmgr.result;

public class ResultPojo {

    private Integer resultCode;
    private String resultMsg;
    private Object resultObj;

    public ResultPojo() {
        this.resultCode = ResultCode.SUCCESS_CODE;
    }

    public void error(String resultMsg) {
        this.setResultCode(ResultCode.FAIL_CODE);
        this.setResultMsg(resultMsg);
    }

    public void error(Integer resultCode, String resultMsg) {
        this.setResultCode(resultCode);
        this.setResultMsg(resultMsg);
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

    @Override
    public String toString() {
        return "{\"resultCode\":\"" + resultCode + "\",\"resultMsg\":\"" + resultMsg + "\",\"resultObj\":\"" + resultObj
                + "\"}";
    }
}
