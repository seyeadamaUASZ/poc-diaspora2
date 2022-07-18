package sn.gainde2000.userservice.exceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
	private Date timestamp;
    private String message;
    private String details;
    private HttpStatus httpStatus;
    
    
    
	public ErrorDetails(Date timestamp, String message, String details, HttpStatus httpStatus) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.httpStatus = httpStatus;
	}
	
	
	
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}



	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
    
    
}
