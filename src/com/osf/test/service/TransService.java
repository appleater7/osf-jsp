package com.osf.test.service;

import java.util.Map;

public interface TransService {
	// 1. 서비스의 역할 에러인지 아닌지?
	// 2. 에러가 아니면 정상수행
	public Map<String, String> transperText(String source, String target, String text);
}
