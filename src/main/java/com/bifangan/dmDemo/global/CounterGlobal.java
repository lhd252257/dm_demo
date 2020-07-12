package com.bifangan.dmDemo.global;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;

public class CounterGlobal {
	
	private static Map<String, Integer> counter = new HashMap<String, Integer>();
	
	static {
		counter.put("35effcb9-7530-466f-b83a-b187a5054db8", 0);
		counter.put("ad09b49c-6094-4186-91f9-c5f443d381fe", 0);
	}
	
	public static Integer get(String key) {
		return counter.get(key);
	}
	
	public static void put(String key, Integer value) {
		counter.put(key, value);
	}
	
}
