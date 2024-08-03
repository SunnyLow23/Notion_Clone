package com.sunnylow.notion_clone.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BlockDTO {

	private Integer id;
	private String type;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private Integer pageId;
	private Integer createdById;
}
