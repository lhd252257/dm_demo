package com.bifangan.dmDemo.common;

import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UUIDUtils {

	public static String getUUID32() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getUUID36() {
		return UUID.randomUUID().toString();
	}
}
