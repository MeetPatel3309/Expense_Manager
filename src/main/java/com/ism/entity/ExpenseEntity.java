package com.ism.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expenseDetails")
public class ExpenseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer expenseId;
	private Integer amount;
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private CategoryEntity category;
	
	@ManyToOne
	@JoinColumn(name = "subCategoryId")
	private SubCategoryEntity subCategory;
	
	@ManyToOne
	@JoinColumn(name = "vendorId")
	private VendorEntity vendor;
	
	@ManyToOne
	@JoinColumn(name = "paymentMethodId")
	private PaymentMethodEntity paymentMethod;

	public Integer getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public SubCategoryEntity getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategoryEntity subCategory) {
		this.subCategory = subCategory;
	}

	public VendorEntity getVendor() {
		return vendor;
	}

	public void setVendor(VendorEntity vendor) {
		this.vendor = vendor;
	}

	public PaymentMethodEntity getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethodEntity paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
}
