package com.lucasp.todolist;

import org.glassfish.jaxb.core.v2.TODO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.lucasp.todolist.entities.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
	    "spring.config.location=classpath:application-test.properties"
	})
class TodolistApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateTodoSuccess() {
        var todo = new Todo("todo 1", "desc todo1", false, 1);

        webTestClient
            .post()
            .uri("/todos")
            .bodyValue(todo)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$").isArray().jsonPath("$.length()").isEqualTo(1)
            .jsonPath("$[0].nome").isEqualTo(todo.getNome())
            .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
            .jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
            .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
    }
    
    @Test
    void testCreateFailure() {
    	
    	webTestClient
    		.post()
    		.uri("/todos")
    		.bodyValue(new Todo("", "", false, 0))
    		.exchange()
    		.expectStatus().isBadRequest();
    				
    	
    }
}