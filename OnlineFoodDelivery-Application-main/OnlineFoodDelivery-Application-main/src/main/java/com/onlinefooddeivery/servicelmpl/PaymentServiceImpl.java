package com.onlinefooddeivery.servicelmpl;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefooddeivery.exception.ResourceNotFoundException;
import com.onlinefooddeivery.model.Customer;
import com.onlinefooddeivery.model.Order;
import com.onlinefooddeivery.model.Payment;
import com.onlinefooddeivery.model.Product;
import com.onlinefooddeivery.repository.OrderRepository;
import com.onlinefooddeivery.repository.PaymentRepository;
import com.onlinefooddeivery.service.CartService;
import com.onlinefooddeivery.service.CustomerService;
import com.onlinefooddeivery.service.OrderService;
import com.onlinefooddeivery.service.PaymentService;
import com.onlinefooddeivery.service.ProductService;




@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;


        public PaymentServiceImpl(PaymentRepository paymentRepository, ProductService productService,
			CustomerService customerService,OrderService orderService) {
		super();
		this.paymentRepository = paymentRepository;
		
		this.customerService = customerService;
		this.orderService = orderService;

	}
        @Override
        public Payment addPayment(Payment payment,long orderId,long customerId) {
    		
        	// TODO Auto-generated method stub
        	Order order = orderRepository.findById(orderId)
					.orElseThrow(() -> new ResourceNotFoundException("Order", "orderId", orderId));
			payment.setOrderId(orderId);
			payment.setTotalPrice(order.getTotalPrice());
			payment.setPaidDate(LocalDate.now());
			payment.setPaidAmount(order.getTotalPrice());
			if (payment.getTotalPrice() == payment.getPaidAmount()) {
				order.setPaymentStatus("PAID");
				order.setOrderStatus("Delivered");
			} else {

				order.setPaymentStatus("NOT-PAID");
				order.setOrderStatus("payment pending");
			}
				  Customer customer=customerService.getCustomerById(customerId);
			    	
			    	payment.setCustomer(customer);
			    	
			    	
			    	     //return paymentRepository.save(payment);
			    	
			
			return paymentRepository.save(payment);
			
        }
              // order = orderService.getOrderById(orderId);
        	//payment.setOrderId(order.getOrderId());
        	//payment.setTotalPrice(payment.getTotalPrice());
        	//payment.setPaidDate(payment.getPaidDate());
  



	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}
	@Override
	public List<Payment> getAllPaymentsByCustomerId(long customerId) {
		return paymentRepository.findByOrderId(customerId);
	}


	@Override
	public Payment getPaymentById(long paymentId) {
		
		return paymentRepository.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("Payement","Id",paymentId));
	}


    @Override
	public void deletePayment(long paymentId) {
		paymentRepository.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("Payement","Id",paymentId));
		paymentRepository.deleteById(paymentId);
		
	}
	
}