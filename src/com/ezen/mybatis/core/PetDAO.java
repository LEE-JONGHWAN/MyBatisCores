package com.ezen.mybatis.core;

import java.util.List;

public interface PetDAO {
	List<PetDVO> getAllPetsData();
	PetDVO getPetObject(String petName) throws Exception;
}