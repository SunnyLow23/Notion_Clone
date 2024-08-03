package com.sunnylow.notion_clone.controller.api;

import com.sunnylow.notion_clone.dto.JournalBlockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "JournalBlock", description = "JournalBlock management APIs")
@RequestMapping("/api/blocks")
public interface JournalBlockAPI {

	@PostMapping(
			value = "/journals",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a journal",
			description = "Get a journalDTO object. The response is journalDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "journal created successfully"),
			@ApiResponse(responseCode = "2001", description = "journal not valid"),
	})
	ResponseEntity<JournalBlockDTO> createJournal(
			@RequestBody JournalBlockDTO dto
	);

	@PatchMapping(
			value = "/journals/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a journal",
			description = "Update an existing journal by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "journal updated successfully"),
			@ApiResponse(responseCode = "1001", description = "journal not found"),
			@ApiResponse(responseCode = "2001", description = "journal not valid"),
	})
	ResponseEntity<JournalBlockDTO> updateJournal(
			@PathVariable("id") Integer id,
			@RequestBody JournalBlockDTO dto
	);

	@GetMapping(
			value = "/journals",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all journals",
			description = "Retrieve all journals"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "journals retrieved successfully"),
	})
	ResponseEntity<List<JournalBlockDTO>> getAllJournals();

	@GetMapping(
			value = "/journals/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the journal by id",
			description = "Retrieve a specific journal by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "journal retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "journal not found"),
	})
	ResponseEntity<JournalBlockDTO> getJournalById(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/journals/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the journal",
			description = "Delete a specific journal by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "journal deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "journal not found"),
	})
	ResponseEntity deleteJournalById(
			@PathVariable("id") Integer id
	);
}
