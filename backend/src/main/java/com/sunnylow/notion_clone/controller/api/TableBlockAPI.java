package com.sunnylow.notion_clone.controller.api;

import com.sunnylow.notion_clone.dto.TableBlockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TableBlock", description = "TableBlock management APIs")
@RequestMapping("/api/blocks")
public interface TableBlockAPI {

	@PostMapping(
			value = "/tables",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a table",
			description = "Get a tableDTO object. The response is tableDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "table created successfully"),
			@ApiResponse(responseCode = "2001", description = "table not valid"),
	})
	ResponseEntity<TableBlockDTO> createTable(
			@RequestBody TableBlockDTO dto
	);

	@PatchMapping(
			value = "/tables/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a table",
			description = "Update an existing table by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "table updated successfully"),
			@ApiResponse(responseCode = "1001", description = "table not found"),
			@ApiResponse(responseCode = "2001", description = "table not valid"),
	})
	ResponseEntity<TableBlockDTO> updateTable(
			@PathVariable("id") Integer id,
			@RequestBody TableBlockDTO dto
	);

	@GetMapping(
			value = "/tables",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all tables",
			description = "Retrieve all tables"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "tables retrieved successfully"),
	})
	ResponseEntity<List<TableBlockDTO>> getAllTables();

	@GetMapping(
			value = "/tables/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the table by id",
			description = "Retrieve a specific table by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "table retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "table not found"),
	})
	ResponseEntity<TableBlockDTO> getTableById(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/tables/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the table",
			description = "Delete a specific table by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "table deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "table not found"),
	})
	ResponseEntity deleteTableById(
			@PathVariable("id") Integer id
	);
}
