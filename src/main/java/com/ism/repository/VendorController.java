package com.ism.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ism.bean.ResponseBean;
import com.ism.entity.VendorEntity;

@RestController
@CrossOrigin
public class VendorController {

	@Autowired
	VendorRepository vendorRepository;
	
	@PostMapping("/vendor")
	public ResponseEntity<ResponseBean<VendorEntity>> addVendor(@RequestBody VendorEntity vendorEntity)
	{
		vendorRepository.save(vendorEntity);
		
		ResponseBean<VendorEntity> resp = new ResponseBean<>();
		resp.setData(vendorEntity);
		resp.setMsg("Vendor added successful..");
		
		return ResponseEntity.ok(resp);
	}
	
	
	@GetMapping("/vendor")
	public ResponseEntity<ResponseBean<List<VendorEntity>>> getAllVendor()
	{
		List<VendorEntity> vendors = vendorRepository.findAll();
		
		ResponseBean<List<VendorEntity>> resp = new ResponseBean<>();
		
		if(vendors.isEmpty())
		{
			resp.setData(vendors);
			resp.setMsg("Error while fetching vendor list or empty list..");
			
			return ResponseEntity.unprocessableEntity().body(resp);
		}
		else
		{
			resp.setData(vendors);
			resp.setMsg("Vendor list fetched successfully..!");
			
			return ResponseEntity.ok(resp);
		}
	}
	
	@DeleteMapping("/vendor/{vendorId}")
	public ResponseEntity<ResponseBean<VendorEntity>> deleteVendor(@PathVariable("vendorId") Integer vendorId)
	{
		VendorEntity vendorEntity = vendorRepository.findById(vendorId).orElse(null);
		ResponseBean<VendorEntity> resp = new ResponseBean<>();
		
		if(vendorId == null)
		{
			resp.setData(vendorEntity);
			resp.setMsg("Vendor can not deleted..");
			
			return ResponseEntity.unprocessableEntity().body(resp);
		}
		else
		{
			resp.setData(vendorEntity);
			resp.setMsg("Vendor deleted successful..!");
			
			return ResponseEntity.ok(resp);
		}
	}
	
	
	
}
