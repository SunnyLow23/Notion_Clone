package com.sunnylow.notion_clone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

	private Integer position;

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

	@ElementCollection
	@CollectionTable(name = "block_attributes", joinColumns = @JoinColumn(name = "block_id"))
	@MapKeyColumn(name = "attribute_key")
	@Column(name = "attribute_value")
	private Map<String, String> attributes;

	@OneToMany(mappedBy = "parentBlock", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Block> children;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_block_id")
	private Block parentBlock;
}
