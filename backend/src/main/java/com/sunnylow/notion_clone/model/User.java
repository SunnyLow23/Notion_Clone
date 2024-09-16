package com.sunnylow.notion_clone.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	private String password;

	@Column(unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@OneToMany(
			mappedBy = "user",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<Workspace> workspaces = new ArrayList<>();

	@OneToMany(
			mappedBy = "author",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<Page> pages = new ArrayList<>();

	@OneToMany(
			mappedBy = "createdBy",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<Tag> tags = new ArrayList<>();

	@OneToMany(
			mappedBy = "createdBy",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<Block> blocks = new ArrayList<>();

	@OneToMany(
			mappedBy = "createdBy",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<BlockNote> blockNotes = new ArrayList<>();

	@OneToMany(
			mappedBy = "user",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<StaticNote> staticNotes = new ArrayList<>();

	@OneToMany(
			mappedBy = "user",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<SharedPage> sharedPages = new ArrayList<>();
}
