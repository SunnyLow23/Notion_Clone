package com.sunnylow.notion_clone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "block_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "blocks")
@Data
public abstract class Block {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(
			name = "block_type",
			insertable = false,
			updatable = false
	)
	private String type;

	private LocalDate createdAt;

	private LocalDate updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id")
	@JsonBackReference
	private Page page;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id")
	@JsonBackReference
	private User createdBy;
}
