/**
 * 
 */
package com.example.demo.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * 
 */

public class ResponseBean {

	private int statusCode;
	private String errorMsg;
	private Object data; //User
	private HttpStatus statusType;
	
	
	public HttpStatus getStatusType() {
		return statusType;
	}
	public void setStatusType(HttpStatus statusType) {
		this.statusType = statusType;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseBean [statusCode=" + statusCode + ", errorMsg=" + errorMsg + ", data=" + data + ", statusType="
				+ statusType + "]";
	}
	
	
	
}
