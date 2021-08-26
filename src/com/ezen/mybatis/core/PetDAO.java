package com.ezen.mybatis.core;

import java.util.Date;
import java.util.List;

public interface PetDAO {
	List<PetDVO> getAllPetsData();
	PetDVO getPetObject(String petName) throws Exception;
	PetDVO getPetObject(int petId);
	public List<String> getAllSpecies();
	
	List<PetDVO> petsByGender(String gender);
	int insertPet(PetDVO petDVO);
	void updatePetData(PetDVO petDVO) throws Exception;
	int deletePet(String species, String name) throws Exception;
	int deleteByBirth(String species, Date birth) throws Exception;
}

