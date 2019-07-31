package com.manage.webmgr.user.entity;

public enum UserStateEnum {

	
	//状态   状态 0：正常 1：禁用 2：删除
	   /**
	    * 0：正常 
	    */
		normal(0,"正常 "),
	   /**
	    * 1：禁用
	    */
		disable(1,"禁用"),
	   /**
	    * 2：删除
	    */
	   deled(2,"删除");
		
		private int state;  
		private String description;
	     
	    private UserStateEnum(int  state,String description) {  
	        this.state =  state;  
	        this.description=description;
	    }

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		 
}
