package com.sunnylow.notion_clone.controller.api;

import com.sunnylow.notion_clone.dto.TagDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tag", description = "Tag management APIs")
@RequestMapping("/api")
public interface TagAPI {

	@PostMapping(
			value = "/tags",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a tag",
			description = "Get a TagDTO object. The response is TagDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Tag created successfully"),
			@ApiResponse(responseCode = "2001", description = "Tag not valid"),
	})
	ResponseEntity<TagDTO> createTag(
			@RequestBody TagDTO dto
	);

	@PatchMapping(
			value = "/tags/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a tag",
			description = "Update an existing tag by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tag updated successfully"),
			@ApiResponse(responseCode = "1001", description = "Tag not found"),
			@ApiResponse(responseCode = "2001", description = "Tag not valid"),
	})
	ResponseEntity<TagDTO> updateTag(
			@PathVariable("id") Integer id,
			@RequestBody TagDTO dto
	);

	@GetMapping(
			value = "/tags",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all tags",
			description = "Retrieve all tags"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tags retrieved successfully"),
	})
	ResponseEntity<List<TagDTO>> getAllTags();

	@GetMapping(
			value = "/tags/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the tag by id",
			description = "Retrieve a specific tag by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tag retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "Tag not found"),
	})
	ResponseEntity<TagDTO> getTagById(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/tags/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the tag",
			description = "Delete a specific tag by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Tag deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "Tag not found"),
	})
	ResponseEntity deleteTagById(
			@PathVariable("id") Integer id
	);
}
