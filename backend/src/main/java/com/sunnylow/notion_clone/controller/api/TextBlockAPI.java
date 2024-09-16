package com.sunnylow.notion_clone.controller.api;

import com.sunnylow.notion_clone.dto.TextBlockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TextBlock", description = "TextBlock management APIs")
@RequestMapping("/api")
public interface TextBlockAPI {

	@PostMapping(
			value = "/texts",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a text",
			description = "Get a textDTO object. The response is textDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "text created successfully"),
			@ApiResponse(responseCode = "2001", description = "text not valid"),
	})
	ResponseEntity<TextBlockDTO> createText(
			@RequestBody TextBlockDTO dto
	);

	@PatchMapping(
			value = "/texts/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a text",
			description = "Update an existing text by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "text updated successfully"),
			@ApiResponse(responseCode = "1001", description = "text not found"),
			@ApiResponse(responseCode = "2001", description = "text not valid"),
	})
	ResponseEntity<TextBlockDTO> updateText(
			@PathVariable("id") Integer id,
			@RequestBody TextBlockDTO dto
	);

	@PatchMapping(
			value = "/texts/{id}/position",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update block position",
			description = "Update block position"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "text updated successfully"),
			@ApiResponse(responseCode = "1001", description = "text not found"),
			@ApiResponse(responseCode = "2001", description = "text not valid"),
	})
	ResponseEntity<TextBlockDTO> updatePosition(
			@PathVariable("id") Integer id,
			@RequestParam Integer newPosition
	);

	@GetMapping(
			value = "/texts",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all texts",
			description = "Retrieve all texts"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "texts retrieved successfully"),
	})
	ResponseEntity<List<TextBlockDTO>> getAllTexts();

	@GetMapping(
			value = "/pages/{id}/texts",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all texts by page id",
			description = "Retrieve all texts by a specific page id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "texts retrieved successfully"),
	})
	ResponseEntity<List<TextBlockDTO>> getAllTextsByPageId(
			@PathVariable("id") Integer pageId
	);

	@GetMapping(
			value = "/texts/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the text by id",
			description = "Retrieve a specific text by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "text retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "text not found"),
	})
	ResponseEntity<TextBlockDTO> getTextById(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/texts/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the text",
			description = "Delete a specific text by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "text deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "text not found"),
	})
	ResponseEntity deleteTextById(
			@PathVariable("id") Integer id
	);
}
