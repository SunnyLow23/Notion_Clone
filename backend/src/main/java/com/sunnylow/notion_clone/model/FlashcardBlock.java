package com.sunnylow.notion_clone.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("flashcard")
@Data
public class FlashcardBlock extends Block {

	private String question;

	private String answer;
}
