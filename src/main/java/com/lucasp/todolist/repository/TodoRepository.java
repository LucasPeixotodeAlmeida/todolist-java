package com.lucasp.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasp.todolist.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
