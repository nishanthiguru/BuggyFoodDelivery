package com.onlinefooddeivery.service;

import java.util.List;

import com.onlinefooddeivery.model.Admin;
import com.onlinefooddeivery.model.Customer;
import com.onlinefooddeivery.model.Product;

public interface AdminService {
	Admin saveAdmin(Admin admin);
	Admin loginAdmin(Admin admin);
	
	public List<Product> getAllProducts(long adminId);
	public List<Customer> getAllCustomers(long adminId);
}