package com.manage.api.mqtt;

/**
 * @author n010878
 *
 */
public class MessageModel {
	
	private String roomNum;
	private String employeeName;
	private String gift;
	private String giftPic;
	private String employeePic;
	
	
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getGift() {
		return gift;
	}
	public void setGift(String gift) {
		this.gift = gift;
	}
	public String getGiftPic() {
		return giftPic;
	}
	public void setGiftPic(String giftPic) {
		this.giftPic = giftPic;
	}
	public String getEmployeePic() {
		return employeePic;
	}
	public void setEmployeePic(String employeePic) {
		this.employeePic = employeePic;
	}
	
	public MessageModel(String roomNum, String employeeName, String gift, String giftPic, String employeePic) {
		super();
		this.roomNum = roomNum;
		this.employeeName = employeeName;
		this.gift = gift;
		this.giftPic = giftPic;
		this.employeePic = employeePic;
	}
	
	public MessageModel() {
		
	}

}
