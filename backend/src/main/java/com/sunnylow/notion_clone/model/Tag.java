package com.sunnylow.notion_clone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
@Data
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Enumerated(EnumType.STRING)
	private TagColor color;

	private LocalDate createdAt;

	private LocalDate updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id")
	@JsonBackReference
	private User createdBy;

	@ManyToMany(
			fetch = FetchType.LAZY,
			mappedBy = "tags"
	)
	@JsonBackReference
	private List<Page> pages = new ArrayList<>();
}
