package com.sunnylow.notion_clone.controller.api;

import com.sunnylow.notion_clone.dto.TodoBlockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TodoBlock", description = "TodoBlock management APIs")
@RequestMapping("/api/blocks")
public interface TodoBlockAPI {

	@PostMapping(
			value = "/todos",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a todo",
			description = "Get a todoDTO object. The response is todoDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "todo created successfully"),
			@ApiResponse(responseCode = "2001", description = "todo not valid"),
	})
	ResponseEntity<TodoBlockDTO> createTodo(
			@RequestBody TodoBlockDTO dto
	);

	@PatchMapping(
			value = "/todos/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a todo",
			description = "Update an existing todo by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "todo updated successfully"),
			@ApiResponse(responseCode = "1001", description = "todo not found"),
			@ApiResponse(responseCode = "2001", description = "todo not valid"),
	})
	ResponseEntity<TodoBlockDTO> updateTodo(
			@PathVariable("id") Integer id,
			@RequestBody TodoBlockDTO dto
	);

	@GetMapping(
			value = "/todos",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all todos",
			description = "Retrieve all todos"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "todos retrieved successfully"),
	})
	ResponseEntity<List<TodoBlockDTO>> getAllTodos();

	@GetMapping(
			value = "/todos/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the todo by id",
			description = "Retrieve a specific todo by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "todo retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "todo not found"),
	})
	ResponseEntity<TodoBlockDTO> getTodoById(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/todos/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the todo",
			description = "Delete a specific todo by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "todo deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "todo not found"),
	})
	ResponseEntity deleteTodoById(
			@PathVariable("id") Integer id
	);
}
