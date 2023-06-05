package com.ism.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category_details")
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<ExpenseEntity> expenses;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
