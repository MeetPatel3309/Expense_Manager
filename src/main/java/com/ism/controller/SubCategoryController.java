package com.ism.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ism.bean.ResponseBean;
import com.ism.entity.SubCategoryEntity;
import com.ism.repository.SubCategoryRepository;

@CrossOrigin
@RestController
public class SubCategoryController {

	@Autowired
	SubCategoryRepository subCategoryRepository;
	
	
	@PostMapping("/subCategory")
	public ResponseEntity<ResponseBean<SubCategoryEntity>> addSubCategory(@RequestBody SubCategoryEntity subCategoryEntity)
	{
		SubCategoryEntity existSubCategory =  subCategoryRepository.findByName(subCategoryEntity.getName());
		
		ResponseBean<SubCategoryEntity> resp = new ResponseBean<>();
		
		if(existSubCategory == null)
		{
			subCategoryRepository.save(subCategoryEntity);
			
			resp.setData(subCategoryEntity);
			resp.setMsg("SubCategory Added Successfully..!");
			
			return ResponseEntity.ok(resp);
		}
		else
		{
			resp.setData(subCategoryEntity);
			resp.setMsg("Please Enter Unique SubCategory..!");
			
			return new ResponseEntity<ResponseBean<SubCategoryEntity>>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
	@GetMapping("/subCategory")
	public ResponseEntity<ResponseBean<List<SubCategoryEntity>>> getAllSubCategory()
	{
		List<SubCategoryEntity> subCategories =  subCategoryRepository.findAll();
		
		ResponseBean<List<SubCategoryEntity>> resp = new ResponseBean<>();
		resp.setData(subCategories);
		resp.setMsg("SubCategories Fetched Successful..!");
		
		return ResponseEntity.ok(resp);
	}
	
	
	@DeleteMapping("/subCategory/{subCategoryId}")
	public ResponseEntity<ResponseBean<SubCategoryEntity>> deleteBysubCategoryId(@PathVariable("subCategoryId") Integer subCategoryId)
	{
		
		SubCategoryEntity subCategoryEntity = subCategoryRepository.findById(subCategoryId).orElse(null);
		
		ResponseBean<SubCategoryEntity> resp = new ResponseBean<>();
		
		if(subCategoryEntity != null)
		{
			resp.setData(subCategoryEntity);
			resp.setMsg("SubCategory deleted successful..!");
			subCategoryRepository.deleteById(subCategoryId);
			
			return ResponseEntity.ok(resp);
		}
		else
		{
			resp.setData(subCategoryEntity);
			resp.setMsg("SubCategory not deleted..!");
			
			return new ResponseEntity<ResponseBean<SubCategoryEntity>>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
}
