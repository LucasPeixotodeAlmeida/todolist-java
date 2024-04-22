package com.lucasp.todolist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasp.todolist.entities.Todo;
import com.lucasp.todolist.services.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {

	private TodoService todoService;

	// Injeção de dependência no construtor para inicializar o TodoService

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	// Endpoint para criar um novo todo

	@PostMapping
	List<Todo> create(@RequestBody @Valid Todo todo) {
		return todoService.create(todo);
	}

	// Endpoint para listar todos os todos

	@GetMapping
	List<Todo> list() {
		return todoService.list();
	}

	// Endpoint para atualizar um todo pelo seu id

	@PutMapping("/{id}")
	public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
		Todo updatedTodo = todoService.update(id, todo);
		if (updatedTodo != null) {
			return ResponseEntity.ok(updatedTodo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint para deletar um todo pelo seu id

	@DeleteMapping("{id}")
	List<Todo> delete(@PathVariable("id") Long id) {
		return todoService.delete(id);
	}
}
