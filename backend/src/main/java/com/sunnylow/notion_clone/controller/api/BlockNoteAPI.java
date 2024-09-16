package com.sunnylow.notion_clone.controller.api;

import com.sunnylow.notion_clone.dto.BlockNoteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "BlockNote", description = "BlockNote management APIs")
@RequestMapping("/api")
public interface BlockNoteAPI {

	@PostMapping(
			value = "/notes",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a block",
			description = "Get a blockNoteDTO object. The response is blockNoteDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "block created successfully"),
			@ApiResponse(responseCode = "2001", description = "block not valid"),
	})
	ResponseEntity<BlockNoteDTO> createNote(
			@RequestBody BlockNoteDTO dto
	);

	@PatchMapping(
			value = "/notes/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a block",
			description = "Update an existing block by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "block updated successfully"),
			@ApiResponse(responseCode = "1001", description = "block not found"),
			@ApiResponse(responseCode = "2001", description = "block not valid"),
	})
	ResponseEntity<BlockNoteDTO> updateNote(
			@PathVariable("id") Integer id,
			@RequestBody BlockNoteDTO dto
	);

	@GetMapping(
			value = "/notes",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all blocks",
			description = "Retrieve all blocks"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "blocks retrieved successfully"),
	})
	ResponseEntity<List<BlockNoteDTO>> getAllNotes();

	@GetMapping(
			value = "/notes/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the block by id",
			description = "Retrieve a specific block by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "block retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "block not found"),
	})
	ResponseEntity<BlockNoteDTO> getNoteById(
			@PathVariable("id") Integer id
	);

	@GetMapping(
			value = "/pages/{id}/notes",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the block by id",
			description = "Retrieve a specific block by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "block retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "block not found"),
	})
	ResponseEntity<BlockNoteDTO> getNoteByPageId(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/notes/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the block",
			description = "Delete a specific block by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "block deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "block not found"),
	})
	ResponseEntity deleteNoteById(
			@PathVariable("id") Integer id
	);
}
