package com.bifangan.dmDemo.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfo {

	private String UserID;
	
	private String Nonce;
	
	private String TimeStamp;
	
	private String Signature;

	@JSONField(name = "UserID",ordinal=1)
	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}
	
	@JSONField(name = "Nonce",ordinal=2)
	public String getNonce() {
		return Nonce;
	}
	
	public void setNonce(String nonce) {
		Nonce = nonce;
	}
	
	@JSONField(name = "TimeStamp",ordinal=3)
	public String getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	@JSONField(name = "Signature",ordinal=4)
	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}
	
	

	
}
