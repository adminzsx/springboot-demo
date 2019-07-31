package com.manage.webmgr.result;

public class ResultCode {
	
	 public final static int SUCCESS_CODE = 0;

     public final static int FAIL_CODE = 1;
     
     /**
      * 非法操作
      */
     public final static int FAIL_ILLEGAL_CODE = 10001;
     
     /**
      * 设备未激活
      */
     public final static int FAIL_DEVICE_NOREGISTER_CODE = 10002;
     

     /**
      * TOKEN无效
      */
     public final static int FAIL_TOKEN_CODE = 10003;
     
     
     /**
      * IC卡无效
      */
     public final static int FAIL_IC_CODE = 10004;
     
     /**
      * 参数异常
      */
     public final static int FAIL_ILLEGAL_PARAM_CODE = 10005;
     
     /**
      * 已经评价了
      */
     public final static int FAIL_SCORED_CODE = 10006;
    

}
