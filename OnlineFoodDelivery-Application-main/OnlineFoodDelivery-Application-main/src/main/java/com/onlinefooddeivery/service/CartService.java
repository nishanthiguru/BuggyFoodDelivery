package com.onlinefooddeivery.service;

import java.util.List;

import com.onlinefooddeivery.model.Cart;
import com.onlinefooddeivery.model.Customer;
import com.onlinefooddeivery.model.Product;



public interface CartService {

	Cart addCart(Cart cart,long productId,long customerId);
	List<Cart> getAllCarts();
	Cart getCartById(long cartId);
	Cart updateCart(Cart cart, long cartId);
	void deleteCart(long cartId);
	void deleteCartByCustomer(Customer c);
	
	

}