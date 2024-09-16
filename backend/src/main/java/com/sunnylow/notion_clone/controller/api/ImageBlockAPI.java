package com.sunnylow.notion_clone.controller.api;

import com.sunnylow.notion_clone.dto.ImageBlockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ImageBlock", description = "ImageBlock management APIs")
@RequestMapping("/api")
public interface ImageBlockAPI {

	@PostMapping(
			value = "/images",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a image",
			description = "Get a imageDTO object. The response is imageDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "image created successfully"),
			@ApiResponse(responseCode = "2001", description = "image not valid"),
	})
	ResponseEntity<ImageBlockDTO> createImage(
			@RequestBody ImageBlockDTO dto
	);

	@PatchMapping(
			value = "/images/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a image",
			description = "Update an existing image by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "image updated successfully"),
			@ApiResponse(responseCode = "1001", description = "image not found"),
			@ApiResponse(responseCode = "2001", description = "image not valid"),
	})
	ResponseEntity<ImageBlockDTO> updateImage(
			@PathVariable("id") Integer id,
			@RequestBody ImageBlockDTO dto
	);

	@PatchMapping(
			value = "/images/{id}/position",
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
	ResponseEntity<ImageBlockDTO> updatePosition(
			@PathVariable("id") Integer id,
			@RequestParam Integer newPosition
	);

	@GetMapping(
			value = "/images",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all images",
			description = "Retrieve all images"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "images retrieved successfully"),
	})
	ResponseEntity<List<ImageBlockDTO>> getAllImages();

	@GetMapping(
			value = "pages/{id}/images",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all texts by page id",
			description = "Retrieve all texts by a specific page id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "texts retrieved successfully"),
	})
	ResponseEntity<List<ImageBlockDTO>> getAllImagesByPageId(
			@PathVariable("id") Integer pageId
	);

	@GetMapping(
			value = "/images/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the image by id",
			description = "Retrieve a specific image by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "image retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "image not found"),
	})
	ResponseEntity<ImageBlockDTO> getImageById(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/images/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the image",
			description = "Delete a specific image by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "image deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "image not found"),
	})
	ResponseEntity deleteImageById(
			@PathVariable("id") Integer id
	);
}
