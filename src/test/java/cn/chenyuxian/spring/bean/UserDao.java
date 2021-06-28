package cn.chenyuxian.spring.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
	
	private static Map<String, String> hashMap = new HashMap<>();
	
	static {
		hashMap.put("10001", "chenyuxian");
		hashMap.put("10002", "就被谁");
		hashMap.put("10003", "阿毛");
	}
	
	public String queryUserName(String uid) {
		return hashMap.get(uid);
	}
}
