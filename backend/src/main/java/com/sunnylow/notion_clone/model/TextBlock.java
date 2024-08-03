package com.sunnylow.notion_clone.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("TEXT")
@Data
public class TextBlock extends Block {

	private String content;
}
