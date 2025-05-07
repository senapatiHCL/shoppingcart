package com.wu.shopping.service;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wu.shopping.dto.CartProductDto;
import com.wu.shopping.exception.NoDataFoundException;
import com.wu.shopping.exception.SomeThingWentWrongException;
import com.wu.shopping.model.CartProduct;
import com.wu.shopping.model.Product;
import com.wu.shopping.repo.CartProductRepo;

@Service
public class CartProductService {
	Logger logger=LoggerFactory.getLogger(CartProductService.class);
	@Autowired
	private CartProductRepo cartProductRepo;
	
	@Autowired
	private ProductService productService;
	
	public CartProduct addToCart(CartProductDto cartProductDto) {
		logger.info(" addToCart | Product adding to cart for "+cartProductDto.getUserId());
		CartProduct cartProduct = getCartProductByUserIdAndProductId(cartProductDto.getUserId(),cartProductDto.getProductId());
		Product pr= productService.getProductById(cartProductDto.getProductId());
		System.out.println(pr);
		if(cartProduct==null) {
			cartProduct=new CartProduct();
			Instant now = Instant.now();
			long timestamp = now.toEpochMilli();
			cartProduct.setId("cr_"+timestamp);
			cartProduct.setUserid(cartProductDto.getUserId());
			cartProduct.setQuantity(cartProductDto.getQuantity());
			cartProduct.setProduct(pr);
		}else {
			logger.info(" addToCart | already In cart so increasing quantity for user"+cartProductDto.getUserId());
			cartProduct.setQuantity(cartProduct.getQuantity()+cartProductDto.getQuantity());
		}
		
		if(cartProduct.getQuantity()>pr.getQuantity()) {
			logger.info(" | user quantity "+cartProduct.getQuantity()+" Product quantity "+pr.getQuantity()+" for user "+cartProductDto.getUserId());
			throw new SomeThingWentWrongException("Error.outofstock");
			}
		
		return cartProductRepo.save(cartProduct);
	}
	
	public CartProduct getCartDetailById(String cartId){
		logger.info("Inside getCartDetailById | for cart "+cartId);
		return cartProductRepo.findById(cartId).orElseThrow(()-> new NoDataFoundException("No Data Found"));
	}
	
	public List<CartProduct> getAllCartProductByUserId(String userId){
		logger.info("Inside getAllCartProductByUserId() | finding all product | in cart by user "+userId);
		return cartProductRepo.findAllByUserid(userId);
	}
	
	public void updateCartProductByUserId(CartProduct cartProduct){
		logger.info("Inside updateCartProductByUserId() | update cart product ");
		 cartProductRepo.save(cartProduct);
	}
	
	public CartProduct getCartProductByUserIdAndProductId(String userId,String productId){
		logger.info("Inside getCartProductByUserIdAndProductId() | finding all product | in cart by user & product Id "+userId);
		return cartProductRepo.findByUseridAndProductProductId(userId,productId);
	}
	
	public void deleteCartProductById(String cartId){
		logger.info("Inside deleteCartProductById() | deleting by cartId  "+cartId);
		 cartProductRepo.deleteById(cartId);
	}
	
	public void deleteCartProductByProductId(String productId){
		logger.info("Inside deleteCartProductByProductId() | deleting by productId  "+productId);
		 cartProductRepo.deleteByProductProductId(productId);
	}
	public void deleteCartItemByUseridAndProductId(String userid,String productId){
		logger.info("Inside deleteByUseridAndProductProductId() | deleting by productId and userid "+productId);
		 cartProductRepo.deleteByUseridAndProductProductId(userid,productId);
	}

	public void deleteCartItemByUserid(String userid){
		logger.info("Inside deleteCartItemByUserid() | deleting by userid "+userid);
		 cartProductRepo.deleteByUserid(userid);
	}
	
	public void removeCartItemByUseridAndProductId(CartProductDto cartProductDto){
		logger.info("Inside removeCartItemByUseridAndProductId() | removing cart item for userid "+cartProductDto.getUserId());
		 CartProduct cartProduct = getCartProductByUserIdAndProductId(cartProductDto.getUserId(),cartProductDto.getProductId());
			
			if(cartProduct != null) {
				cartProduct.setQuantity(cartProduct.getQuantity()-cartProductDto.getQuantity());
				if(cartProduct.getQuantity()<0) {
					throw new SomeThingWentWrongException("Error.removingquantityError");
					}
				if(cartProduct.getQuantity()==0) {
					logger.info(" deleting from Cart | only one product In cart for user ");
					deleteCartItemByUseridAndProductId(cartProductDto.getUserId(),cartProductDto.getProductId());
				}if(cartProduct.getQuantity()>0) {
					logger.info(" removing from Cart | more that one product In cart so decresing quantity for user ");
					cartProductRepo.save(cartProduct);
				}
				
			}else {
				throw new NoDataFoundException("No data found");
			}
	}
	
}
