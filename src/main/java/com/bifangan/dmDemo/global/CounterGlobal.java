package com.bifangan.dmDemo.global;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;

public class CounterGlobal {
	
	private static Map<String, AtomicInteger> counter = new HashMap<String, AtomicInteger>();
	
	public static void get(String key) {
		counter.get(key);
	}
	
	public static void put(String key, AtomicInteger value) {
		counter.put(key, value);
	}
	
}
