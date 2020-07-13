package com.bifangan.dmDemo.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class User {

	private String ID;
	
	private String Secret;

	
	@JSONField(name = "ID",ordinal=1)
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	@JSONField(name = "Secret",ordinal=1)
	public String getSecret() {
		return Secret;
	}

	public void setSecret(String secret) {
		Secret = secret;
	}
	
	
}
