package com.ism.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "paymentMethodDetails")
public class PaymentMethodEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentMethodId;
	private String name;
	
	@OneToMany(mappedBy = "paymentMethod")
	private List<ExpenseEntity> expenses;
	
	
	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
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
