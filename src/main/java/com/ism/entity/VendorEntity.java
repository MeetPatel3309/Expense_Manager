package com.ism.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vendorDetails")
public class VendorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vendorId;
	private String name;
	
	@OneToMany(mappedBy = "vendor")
	private List<ExpenseEntity> expenses;
	
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ExpenseEntity> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<ExpenseEntity> expenses) {
		this.expenses = expenses;
	}
	
	
	

}
