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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ism.bean.ResponseBean;
import com.ism.entity.CategoryEntity;
import com.ism.repository.CategoryRepository;

@CrossOrigin
@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;

	@PostMapping("/category")
	public ResponseEntity<ResponseBean<CategoryEntity>> addCategory(@RequestBody CategoryEntity categoryEntity)
	{
		CategoryEntity existCategory =  categoryRepository.findByName(categoryEntity.getName());
		
		ResponseBean<CategoryEntity> resp = new ResponseBean<>();
		
		if(existCategory == null)
		{
			categoryRepository.save(categoryEntity);
			resp.setData(categoryEntity);
			resp.setMsg("Category Added Successfully..!");
			
			return ResponseEntity.ok(resp);
		}
		else
		{
			resp.setData(categoryEntity);
			resp.setMsg("Please Enter Unique Category..!");
			
			return new ResponseEntity<ResponseBean<CategoryEntity>>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/category")
	public ResponseEntity<ResponseBean<List<CategoryEntity>>> getAllCategory()
	{
		List<CategoryEntity> categories =  categoryRepository.findAll();
		
		ResponseBean<List<CategoryEntity>> resp = new ResponseBean<>();
		resp.setData(categories);
		resp.setMsg("Categories Fetched Successful..!");
		
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<ResponseBean<CategoryEntity>> deleteBycategoryId(@PathVariable("categoryId") Integer categoryId)
	{
		
		System.out.println("-----"+categoryId+"--------");
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);
		
		ResponseBean<CategoryEntity> resp = new ResponseBean<>();
		
		if(categoryEntity != null)
		{
			resp.setData(categoryEntity);
			resp.setMsg("Category deleted successful..!");
			categoryRepository.deleteById(categoryId);
			
			return ResponseEntity.ok(resp);
		}
		else
		{
			resp.setData(categoryEntity);
			resp.setMsg("Category not deleted..!");
			
			return new ResponseEntity<ResponseBean<CategoryEntity>>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
