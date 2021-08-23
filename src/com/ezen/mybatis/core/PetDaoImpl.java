package com.ezen.mybatis.core;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class PetDaoImpl implements PetDAO {

	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<PetDVO> getAllPetsData() {
		List<PetDVO> result = sqlSessionTemplate.selectList("getAllPets"); 
		return result;
	}
	
	public void setSqlSessionTemplate(SqlSessionTemplate
			sqlSessionTemplate) { 
		this.sqlSessionTemplate = sqlSessionTemplate; 
	}
	
	public PetDVO getPetObject(String petName) throws Exception {
//		HashMap<String, String> inputMap = new HashMap<String, String>();
//		inputMap.put("name", petName);
		return (PetDVO) sqlSessionTemplate.selectOne("getPetObject", petName);
	}
	public List<String> getAllSpecies() {
		return sqlSessionTemplate.selectList("getAllSpecies"); 
	}

}
