package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.exception.ErrorCode;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorDTO {

	private Integer httpStatusCode;
	private ErrorCode errorCode;
	private String message;
	private List<String> errors;
}
