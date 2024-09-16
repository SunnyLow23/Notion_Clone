package com.sunnylow.notion_clone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pages")
@Data
public class Page {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	private String background;

	private LocalDate createdAt;

	private LocalDate updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workspace_id")
	@JsonBackReference
	private Workspace workspace;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	@JsonBackReference
	private User author;

	@ManyToMany(
			fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinTable(
			name = "page_tags",
			joinColumns = @JoinColumn(name = "page_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	@JsonManagedReference
	private List<Tag> tags = new ArrayList<>();

	@OneToMany(
			mappedBy = "page",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<Block> blocks = new ArrayList<>();

	@OneToMany(
			mappedBy = "page",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<BlockNote> blockNotes = new ArrayList<>();

	@OneToMany(
			mappedBy = "page",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<SharedPage> sharedPages = new ArrayList<>();

	@OneToMany(
			mappedBy = "page",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<StaticNote> staticNotes = new ArrayList<>();
}
