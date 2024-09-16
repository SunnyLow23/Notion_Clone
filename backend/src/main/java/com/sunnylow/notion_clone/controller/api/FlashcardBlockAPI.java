package com.sunnylow.notion_clone.controller.api;

import com.sunnylow.notion_clone.dto.FlashcardBlockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "FlashcardBlock", description = "FlashcardBlock management APIs")
@RequestMapping("/api")
public interface FlashcardBlockAPI {

	@PostMapping(
			value = "/flashcards",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a flashcard",
			description = "Get a flashcardDTO object. The response is flashcardDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "flashcard created successfully"),
			@ApiResponse(responseCode = "2001", description = "flashcard not valid"),
	})
	ResponseEntity<FlashcardBlockDTO> createFlashcard(
			@RequestBody FlashcardBlockDTO dto
	);

	@PatchMapping(
			value = "/flashcards/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a flashcard",
			description = "Update an existing flashcard by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "flashcard updated successfully"),
			@ApiResponse(responseCode = "1001", description = "flashcard not found"),
			@ApiResponse(responseCode = "2001", description = "flashcard not valid"),
	})
	ResponseEntity<FlashcardBlockDTO> updateFlashcard(
			@PathVariable("id") Integer id,
			@RequestBody FlashcardBlockDTO dto
	);

	@PatchMapping(
			value = "/flashcards/{id}/position",
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
	ResponseEntity<FlashcardBlockDTO> updatePosition(
			@PathVariable("id") Integer id,
			@RequestParam Integer newPosition
	);

	@GetMapping(
			value = "/flashcards",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all flashcards",
			description = "Retrieve all flashcards"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "flashcards retrieved successfully"),
	})
	ResponseEntity<List<FlashcardBlockDTO>> getAllFlashcards();

	@GetMapping(
			value = "/pages/{id}/flashcards",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all texts by page id",
			description = "Retrieve all texts by a specific page id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "texts retrieved successfully"),
	})
	ResponseEntity<List<FlashcardBlockDTO>> getAllFlashcardsByPageId(
			@PathVariable("id") Integer pageId
	);

	@GetMapping(
			value = "/flashcards/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the flashcard by id",
			description = "Retrieve a specific flashcard by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "flashcard retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "flashcard not found"),
	})
	ResponseEntity<FlashcardBlockDTO> getFlashcardById(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/flashcards/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the flashcard",
			description = "Delete a specific flashcard by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "flashcard deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "flashcard not found"),
	})
	ResponseEntity deleteFlashcardById(
			@PathVariable("id") Integer id
	);
}
