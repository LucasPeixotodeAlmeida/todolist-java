package com.lucasp.todolist.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lucasp.todolist.entities.Todo;
import com.lucasp.todolist.repository.TodoRepository;

@Service
public class TodoService {

	private TodoRepository todoRepository;

	// injeção de dependencia via construtor não precisa do '@Autowired' pois o
	// framework ja faz o trabalho
	public TodoService(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	public List<Todo> create(Todo todo) {
		todoRepository.save(todo);
		return list();
	}

	public List<Todo> list() {
	    Sort sort = Sort.by(
	            Sort.Order.desc("prioridade"),
	            Sort.Order.asc("nome")
	    );
	    return todoRepository.findAll(sort);
	}


	public List<Todo> update(Todo todo) {
		todoRepository.save(todo);
		return list();
	}

	public List<Todo> delete(Long id) {
		todoRepository.deleteById(id);;
		return list();
	}
}
