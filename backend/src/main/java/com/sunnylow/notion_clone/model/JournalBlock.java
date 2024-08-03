package com.sunnylow.notion_clone.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("JOURNAL")
@Data
public class JournalBlock extends Block {

	private LocalDate journalDate;

	private String content;
}