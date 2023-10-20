package com.onlinefooddeivery.servicelmpl;

import java.util.List;
//import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefooddeivery.exception.ResourceNotFoundException;
import com.onlinefooddeivery.model.Admin;
import com.onlinefooddeivery.model.Customer;
import com.onlinefooddeivery.model.Product;
import com.onlinefooddeivery.repository.AdminRepository;
import com.onlinefooddeivery.repository.CustomerRepository;
import com.onlinefooddeivery.repository.ProductRepository;
import com.onlinefooddeivery.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
private AdminRepository adminRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		System.out.println("admin register service"+admin);

		return adminRepository.save(admin);
	}

	@Override
	public Admin loginAdmin(Admin admin) {
		return this.adminRepository.findByAdminEmailIdAndAdminPassword(admin.adminEmailId,admin.adminPassword).orElseThrow(()->new ResourceNotFoundException("Admin ", "Id",admin.adminEmailId+"and password "+admin.adminPassword ));
	}

	@Override
	public List<Product> getAllProducts(long adminId) {
		
		// TODO Auto-generated method stub
		return  productRepository.findAll();
	}
	
	@Override
	public List<Customer> getAllCustomers(long adminId) {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

			
		

		
}