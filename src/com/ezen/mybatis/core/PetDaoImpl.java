package com.ezen.mybatis.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<PetDVO> petsByGender(String gender) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("gender", gender);
		List<PetDVO> result = sqlSessionTemplate.selectList("petsByGender",
			param);
		return result;
	}
	
	@Override
	public int insertPet(PetDVO petDVO) {
		Map<String, Object> petMap = new HashMap<String, Object>();

		petMap.put("birth", petDVO.getBirth());
		petMap.put("death", petDVO.getDeath());
		petMap.put("name", petDVO.getName());
		petMap.put("owner", petDVO.getOwner());
		petMap.put("sex", petDVO.getSex());
		petMap.put("species", petDVO.getSpecies());
		sqlSessionTemplate.insert("insertPet", petMap);
		Long longId = (Long)petMap.get("id");

		return longId.intValue();
	}

}
