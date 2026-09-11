package com.company.schoolerp.common;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RestApiResponse<T> {
	
	public RestApiResponse(boolean success, T data){
		this.success = success;
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}
	
	private boolean success;
	
	private T data;
	
	@Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
	
}
