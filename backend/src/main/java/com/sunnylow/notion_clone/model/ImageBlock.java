package com.sunnylow.notion_clone.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("image")
@Data
public class ImageBlock extends Block {

	private String imageUrl;

	private String caption;
}
