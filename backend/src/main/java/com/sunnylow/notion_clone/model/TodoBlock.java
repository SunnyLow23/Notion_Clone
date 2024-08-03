package com.sunnylow.notion_clone.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("TODO")
@Data
public class TodoBlock extends Block {

	private String content;

	private Boolean completed;

	private LocalDate dueDate;
}
