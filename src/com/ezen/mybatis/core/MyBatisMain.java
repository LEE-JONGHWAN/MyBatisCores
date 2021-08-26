package com.ezen.mybatis.core;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyBatisMain {
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext appContext =
				new ClassPathXmlApplicationContext(
				  new String[]{"applicationContext-myBatis.xml"});
			
			PetDAO petDaoImpl =
					(PetDAO)appContext.getBean("petDaoImpl");
			
			
			/**
			 * 애완동물 목록 길이 출력
			 */
			List <PetDVO> pets = petDaoImpl.getAllPetsData();
			System.out.println("--- pets ---" + pets.size());
			
			/**
			 * 애완동물 이름으로 찾아 출력
			 */
			PetDVO pet = petDaoImpl.getPetObject("나비");
			System.out.println("--- 애완동물 ---");
			System.out.println(pet);
			
			/**
			 * 모든 애완 동물 정보 출력
			 */
			List <PetDVO> allPets = petDaoImpl.getAllPetsData();
			allPets.forEach(System.out::println);
			
			/**
			 * 모든 애완 동물 종류를 출력
			 */
			List <String> species = petDaoImpl.getAllSpecies();
			for (String animal : species) {
				System.out.println(animal);
			}
			
			/**
			 * 모든 애완 동물 암수 구별 목록 채취
			 */
			List <PetDVO> genderPets = petDaoImpl.petsByGender("f");
			genderPets.forEach(System.out::println);
			
			/**
			 * 애완동물 생성하여 삽입
			 */
			PetDVO newPet = new PetDVO();

			newPet.setBirth(Util.getDate("1992-01-03")); // JB_module 프로젝트 Util 클래스
			newPet.setDeath(null);
			newPet.setName("Rolf");
			newPet.setOwner("종범3");;
			newPet.setSex('m');;
			newPet.setSpecies("개");
			

			int petId = petDaoImpl.insertPet(newPet);
			System.out.println("ID: " + petId);

			/**
			 * 투캅스 정보를 일부 수정한다. 투캅스 두 마리 청와대 입양 반영
			 * 수정(갱신) 후, 그 동물의 정보를 출력
			 */
			PetDVO petDataObj = new PetDVO();
			String name = "Rolf";

			petDataObj.setOwner("자연인");
			petDataObj.setName(name);
			petDataObj.setDeath(null);
			petDataObj.setId(petId);

			petDaoImpl.updatePetData(petDataObj);	
			PetDVO changedPet = petDaoImpl.getPetObject(petId); // 행1
			System.out.println("--- 애완동물 ---"); // 행2
			System.out.println(changedPet); // 행3

			/**
			 * 특정 종(species)과 이름이 주어졌을 때 애완동물을 삭제하기
			 */
			int delCnt = petDaoImpl.deletePet("개", "Rolf");
			System.out.println("삭제된 애완 동물 수: " + delCnt);
			
			/**
			 * 특정 종(species)과 출생일 상한이 주어졌을 때 애완동물(들)을 삭제하기
			 */
			delCnt = petDaoImpl.deleteByBirth("강아지", 
					Util.getDate("2000-1-16"));
			System.out.println("삭제된 애완 동물 수: " + delCnt);
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
