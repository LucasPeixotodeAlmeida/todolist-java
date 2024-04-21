package com.lucasp.todolist.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lucasp.todolist.entities.Todo;
import com.lucasp.todolist.repository.TodoRepository;

@Service
public class TodoService {

	private TodoRepository todoRepository;

	/// Injeta o TodoRepository no serviço
	public TodoService(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	// Cria uma nova tarefa e a salva no repositório
	public List<Todo> create(Todo todo) {
		todoRepository.save(todo);
		return list();
	}

	// Lista todas as tarefas ordenadas por prioridade e nome
	public List<Todo> list() {
	    Sort sort = Sort.by(
	            Sort.Order.desc("prioridade"),
	            Sort.Order.asc("nome")
	    );
	    return todoRepository.findAll(sort);
	}

	// Atualiza uma tarefa existente com base no ID fornecido
	public Todo update(Long id, Todo todo) {
	    Todo existingTodo = todoRepository.findById(id).orElse(null);
	    if (existingTodo != null) {
	        // Atualiza os campos relevantes do existingTodo com os valores do todo recebido
	        existingTodo.setNome(todo.getNome());
	        existingTodo.setPrioridade(todo.getPrioridade());
	        existingTodo.setDescricao(todo.getDescricao());
	        existingTodo.setRealizado(todo.isRealizado());
	        return todoRepository.save(existingTodo);
	    } else {
	        return null;
	    }
	}

	// Deleta uma tarefa com base no ID fornecido
	public List<Todo> delete(Long id) {
		todoRepository.deleteById(id);;
		return list();
	}
}
