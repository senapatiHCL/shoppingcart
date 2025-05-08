package com.wu.shopping.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.wu.shopping.dto.PlaceOrderDto;
import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.exception.SomeThingWentWrongException;
import com.wu.shopping.model.CartProduct;
import com.wu.shopping.model.OrderDetail;
import com.wu.shopping.model.PaymentDetail;
import com.wu.shopping.model.User;
import com.wu.shopping.repo.OrderDetailRepo;
import com.wu.shopping.repo.PaymentDetailRepo;

@Service
public class OrderDetailService {

	Logger logger = LoggerFactory.getLogger(OrderDetailService.class);

	@Autowired
	private OrderDetailRepo orderDetailRepo;

	@Autowired
	private PaymentDetailRepo paymentDetailRepo;

	@Autowired
	private CartProductService cartProductService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Value("${delivery.charge}")
	private int deliveryCharge;
	
	public 	OrderDetail findByOrderId(String orderId){
		return orderDetailRepo.findById(orderId).orElseThrow(() -> new NoDataFoundException("No Data Found"));
	}
	
	public 	List<OrderDetail> findOrderDetailByUserId(String userId){
		return orderDetailRepo.findByUserid(userId);
	}
	
	public 	List<OrderDetail> getAllOrderDetailForAdmin(){
		return orderDetailRepo.findAll();
	}
	
	public 	String updateOrderStatusByAdmin( String OrderId, String status){
		OrderDetail orderDetail= orderDetailRepo.findById(OrderId).orElseThrow();
		orderDetail.setStatus(status);
		orderDetailRepo.save(orderDetail);
		return orderDetail.getId();
	}

	public 	OrderDetail cancelOrder(String oderId,String userId){
		OrderDetail orderFetch= orderDetailRepo.findByIdAndUserid(oderId,userId).orElseThrow(() -> new NoDataFoundException("No Data Found"));
		Instant now = Instant.now();
		Instant OrderDate=orderFetch.getOrderDate();
		 Duration timeDifference = Duration.between(now, OrderDate);
		 System.out.println("timeDifference"+timeDifference);
		 System.out.println("hours"+timeDifference.toHours());
		 if(timeDifference.toHours()<5 && orderFetch.getStatus().equalsIgnoreCase("Ordered")) {
			 orderFetch.setStatus("Cancelled");
			orderFetch.setPaymentStatus("Refunded");
			walletService.refundWalletAmountByUserId(orderFetch.getUserid(), orderFetch.getTotalAmount(), orderFetch.getId());
			orderDetailRepo.save(orderFetch);
		 }
		 else throw new SomeThingWentWrongException("Error.catCancellOrder");
		
		return orderFetch;
	}
	public Map placeorder(PlaceOrderDto pod) {
		logger.info("Inside placeOrder | for user " + pod.getUserid());
		
		User user= registrationService.findUserById(pod.getUserid());
		if(user.getFirstName().isEmpty() || user.getFirstName().isBlank() ||user.getLastName().isBlank() ||user.getLastName().isEmpty()) {
			throw new SomeThingWentWrongException("Error.NofirstLastName");
		}
		OrderDetail orderCreated = createOrderForUser(pod);
		logger.info(
				"------ placing final Order | with status payment awaiting |for user " + pod.getUserid() + "-------");
		logger.info(".*********** waiting for payment to be success |for user " + pod.getUserid() + "***********");

		PaymentDetail paymentCreated = createPaymentForUser(orderCreated, pod);

		logger.info("*********** payment successfull  |for user " + pod.getUserid() + "***********");
		OrderDetail orderbyUser = orderDetailRepo.findById(orderCreated.getId()).orElseThrow();
		orderbyUser.setPaymentStatus("payment done");
		if (pod.getMode().equalsIgnoreCase("cod")) {
			orderbyUser.setPaymentStatus("payment awaiting");
		}
		orderDetailRepo.save(orderbyUser);
		logger.info("---------Updating  Order | with status payment done |for user " + pod.getUserid() + "--------");
		cartProductService.deleteCartItemByUserid(pod.getUserid());
		logger.info("---------Deleting products from cart |for user " + pod.getUserid() + "--------");
		orderCreated.getCartProductList().stream().forEach(cartProduct -> productService
				.decreaseProductQuantity(cartProduct.getProduct(), cartProduct.getQuantity()));
		logger.info("---------| Reducing quantity of  products from inventory |--------");

		Map responseMap = new HashMap<>();
        responseMap.put("orderId", orderCreated.getId());
        responseMap.put("PaymentId", paymentCreated.getId());
        
		return responseMap;
	}

	public OrderDetail getPlacedOrderById(String id) {
		logger.info("Inside getPlacedOrderById() | getPlacedOrderById  product ");
		return orderDetailRepo.findById(id).orElseThrow(() -> new NoDataFoundException("No Data Found"));
	}

	public OrderDetail createOrderForUser(PlaceOrderDto pod) {
		List<CartProduct> cartList = cartProductService.getAllCartProductByUserId(pod.getUserid());
		double productAmount = cartList.stream()
				.mapToDouble(productlist -> productlist.getQuantity() * productlist.getProduct().getPrice()).sum();

		OrderDetail oder = new OrderDetail();
		Instant now = Instant.now();
		long timestamp = now.toEpochMilli();
		oder.setId("order" + timestamp);
		oder.setDeliveryCharge(deliveryCharge);
		oder.setOrderDate(now);
		oder.setUserid(pod.getUserid());
		oder.setCartProductList(cartList);
		oder.setTax((productAmount * 12) / 100);
		oder.setProductAmount(productAmount);
		oder.setTotalAmount(productAmount + oder.getTax() + oder.getDeliveryCharge());
		oder.setStatus("Ordered");
		oder.setPaymentMode(pod.getMode());
		oder.setPaymentStatus("payment awaiting");
		oder.setShippingAddress(pod.getShippingAddress());
		orderDetailRepo.save(oder);
		return oder;
	}

	public PaymentDetail createPaymentForUser(OrderDetail orderCreated, PlaceOrderDto pod) {
		Instant now = Instant.now();
		long timestamp = now.toEpochMilli();
		PaymentDetail paymentDetail = new PaymentDetail();
		paymentDetail.setUserId(orderCreated.getUserid());
		paymentDetail.setAmount(orderCreated.getTotalAmount());
		paymentDetail.setId("zara" + timestamp);
		paymentDetail.setOrderId(orderCreated.getId());
		paymentDetail.setPaymentMode(orderCreated.getPaymentMode());
		paymentDetail.setPaymentDate(now);
		paymentDetail.setPaymentStatus("payment done");
		if (pod.getMode().equalsIgnoreCase("cod")) {
			paymentDetail.setPaymentStatus("payment awaiting");
		}
		if (pod.getMode().equalsIgnoreCase("creditcard")) {
		//	paymentDetail.setCrediCardNumber(pod.getCardNumber());
			paymentDetail.setCrediCardNumber("****-****-****-"+pod.getCardNumber().substring(11));
		//	paymentDetail.setCrediCardNumber("****-****-****-"+pod.getCardNumber().substring(15)) //with -;
			paymentDetail.setExpiry(pod.getExpiry());
			// cardnumber,cvv,exp
		}
		if (pod.getMode().equalsIgnoreCase("wallet")) {
			walletService.deductWalletAmountByUserId(orderCreated.getUserid(), orderCreated.getTotalAmount(), orderCreated.getId());
			// username,passowrd,bank
		}
		return paymentDetailRepo.save(paymentDetail);

	}
}
