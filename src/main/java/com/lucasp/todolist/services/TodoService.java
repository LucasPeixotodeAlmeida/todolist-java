package com.lucasp.todolist.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lucasp.todolist.entities.Todo;
import com.lucasp.todolist.repository.TodoRepository;

@Service
public class TodoService {

	private TodoRepository todoRepository;

<<<<<<< HEAD
	/// Injeta o TodoRepository no serviço
=======
	// injeção de dependencia via construtor não precisa do '@Autowired' pois o
	// framework ja faz o trabalho
>>>>>>> 02758962dd9f335e1afe1605c24a12916acba6ed
	public TodoService(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

<<<<<<< HEAD
	// Cria uma nova tarefa e a salva no repositório
=======
>>>>>>> 02758962dd9f335e1afe1605c24a12916acba6ed
	public List<Todo> create(Todo todo) {
		todoRepository.save(todo);
		return list();
	}

<<<<<<< HEAD
	// Lista todas as tarefas ordenadas por prioridade e nome
=======
>>>>>>> 02758962dd9f335e1afe1605c24a12916acba6ed
	public List<Todo> list() {
	    Sort sort = Sort.by(
	            Sort.Order.desc("prioridade"),
	            Sort.Order.asc("nome")
	    );
	    return todoRepository.findAll(sort);
	}

<<<<<<< HEAD
	// Atualiza uma tarefa existente com base no ID fornecido
=======

>>>>>>> 02758962dd9f335e1afe1605c24a12916acba6ed
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

<<<<<<< HEAD
	// Deleta uma tarefa com base no ID fornecido
=======
>>>>>>> 02758962dd9f335e1afe1605c24a12916acba6ed
	public List<Todo> delete(Long id) {
		todoRepository.deleteById(id);;
		return list();
	}
}
