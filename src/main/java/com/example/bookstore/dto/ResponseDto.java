package com.example.bookstore.dto;
/*
 * This class provides the response object to API
 * Created By Prakash K
 * Created 8th Aug 2024
 *  
 */
public class ResponseDto {
	
    private String statusCode;
    
    private String statusMsg;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public ResponseDto(String statusCode, String statusMsg) {
		super();
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}

	public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ResponseDto [statusCode=" + statusCode + ", statusMsg=" + statusMsg + "]";
	}
    
}
