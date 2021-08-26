package com.ezen.mybatis.core;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class petDaoImpl {
	SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(null);
	
	@Override
	public List<PetDVO> findAllSnakes() {
		HashMap<String, String> inputMap = new HashMap<String, String>();
		inputMap.put("species", "뱀");
		inputMap.put("sex", "m");
		inputMap.put("owner", "남%");

		return sqlSessionTemplate.selectList("findAllSnakes", inputMap); 
	}
}
