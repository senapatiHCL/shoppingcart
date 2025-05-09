package com.wu.shopping.constant;

public class WUConstant {

	// URL MAPPING
	public static final String abc="hello";
	public static final String AUTHENTICATION_CONTROLLER_CONTEXT="/auth";
	public static final String CART_CONTROLLER_CONTEXT="/cart";
	public static final String CORS_DOMAIN="http://localhost:3000";
	public static final String US_SIGNUP_URL="/us/signup";
	public static final String IN_SIGNUP_URL="/in/signup";
	public static final String LOGIN_URL="/login";
	public static final String ADD_CART="addToCart";
	public static final String REMOVE_FROM_CART="removeFromCart";
	public static final String VIEW_CART_DETAIL="viewCartDetail";
	public static final String ORDER_CONTROLLER_CONTEXT="/order";
	public static final String ORDER_PLACE="placeorder";
	public static final String ORDER_DETAIL="getOrderDetail";
	public static final String ORDER_CANCEL="cancelOrder";
	public static final String PRODUCT_CONTROLLER_CONTEXT="/public/product";
	public static final String PRODUCT_SAVE="saveProduct";
	public static final String GET_PRODUCT="getProductById";
	public static final String GET_ALLPRODUCT="getAllProductCatalog";
	public static final String PROFILE_CONTROLLER="/profile/";
	public static final String PROFILE_VIEW_EMAIL="viewProfileByEmail";
	public static final String PROFILE_VIEW_ID="viewProfileById";
	public static final String PROFILE_UPDATE="/updateProfile";
	public static final String PASSWORD_SET="/setNewPassword";
	public static final String GET_ADDRESS="/getAddress";
	public static final String SAVE_ADDRESS="/saveNewAddress";
	public static final String WALLET_CONTROLLER="/wallet";
	public static final String FETCH_WALLET_BALANCE="fetchWalletBalanceById";
	public static final String GET_ALL_ORDER_DETAIL_FOR_ADMIN="getAllOrderDetailForAdmin";
	public static final String UPDATE_ORDER_STATUS_BY_ADMIN="updateOrderStatusByAdmin";
	
	// RESPONSE ATTRIBUTES
	 
	public static final String DESCRIPTION="description";
	public static final String STATUS="status";
	public static final String DETAIL="detail";
	public static final String ACCESS_DENIED="Acess Denied";
	public static final String ORDER_PLACED="Order placed";
	public static final String ORDER_CANCEELED="Order cancelled";
	public static final String CREDIT_CARD="creditcard";
	public static final String COD="cod";
	public static final String WALLET="wallet";
	public static final String CONTAINS_ADMIN="admin";
	public static final String DELIVERY_CHARGE="delivery.charge";
	public static final String REFUNDED="Refunded";
	public static final String ORDERED="Ordered";
	public static final String PAYMENT_DONE="payment done";
	public static final String PAYMENT_AWAITING="payment awaiting";
	public static final String ORDER_ID="orderId";
	public static final String PAYMENT_ID="PaymentId";
	public static final String ORDER_ID_PREFIX="order";
	public static final String PAYMENT_ID_PREFIX="zara";
	
	//ERROR CODE
	public static final String ERROR_INCORRECT_CARD_DETAIL="Error.incorrectCardDetail";
	public static final String ERROR_CANT_CANCELL_ORDER="Error.cantCancellOrder";
	public static final String NO_DATA_FOUND="No Data Found";

}
