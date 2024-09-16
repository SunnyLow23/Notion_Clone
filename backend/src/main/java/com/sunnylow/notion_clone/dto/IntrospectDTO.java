package com.sunnylow.notion_clone.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntrospectDTO {

	private String token;
	private boolean valid;
}
