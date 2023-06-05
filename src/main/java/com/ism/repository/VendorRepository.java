package com.ism.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ism.entity.VendorEntity;

@Repository
public interface VendorRepository extends CrudRepository<VendorEntity, Integer>{

	List<VendorEntity> findAll();
}
