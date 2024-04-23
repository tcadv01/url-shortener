package cl.tcadv.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class GenericResponseDTO<T> {

	private int statusCode;
	private String message;
	private T data;

	public GenericResponseDTO(int statusCode, String message, T data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public GenericResponseDTO() {
		super();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "GenericResponseDTO [statusCode=" + statusCode + ", message=" + message + "]";
	}

}
