package com.ism.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ism.entity.SubCategoryEntity;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategoryEntity, Integer>{

	SubCategoryEntity findByName(String name);
	
	List<SubCategoryEntity> findAll();

}
