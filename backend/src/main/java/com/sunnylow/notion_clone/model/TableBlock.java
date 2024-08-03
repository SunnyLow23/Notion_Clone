package com.sunnylow.notion_clone.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("TABLE")
@Data
public class TableBlock extends Block {

	@Column(columnDefinition = "json")
	private String tableData;
}
