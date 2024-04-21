package com.lucasp.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.lucasp.todolist.entities.Todo;

// Classe de teste para a aplicação Todolist
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
    "spring.config.location=classpath:application-test.properties" // Configuração para o ambiente de teste
})
class TodolistApplicationTests {

    @Autowired
    private WebTestClient webTestClient; // Cliente de teste web para interação com o aplicativo

    // Teste para verificar a criação bem-sucedida de uma tarefa
    @Test
    void testCreateTodoSuccess() {
        var todo = new Todo("todo 1", "desc todo1", false, 1); // Criando uma nova tarefa

        webTestClient
            .post()
            .uri("/todos")
            .bodyValue(todo)
            .exchange()
            .expectStatus().isOk() // Verificando se o status de resposta é OK
            .expectBody()
            .jsonPath("$").isArray().jsonPath("$.length()").isEqualTo(1) // Verificando se há uma tarefa criada
            .jsonPath("$[0].nome").isEqualTo(todo.getNome()) // Verificando se o nome da tarefa corresponde ao esperado
            .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao()) // Verificando se a descrição da tarefa corresponde ao esperado
            .jsonPath("$[0].realizado").isEqualTo(todo.isRealizado()) // Verificando se o status de realização da tarefa corresponde ao esperado
            .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade()); // Verificando se a prioridade da tarefa corresponde ao esperado
    }
    
    // Teste para verificar a falha na criação de uma tarefa com dados inválidos
    @Test
    void testCreateFailure() {
    	
    	webTestClient
    		.post()
    		.uri("/todos")
    		.bodyValue(new Todo("", "", false, 0)) // Criando uma nova tarefa com dados inválidos
    		.exchange()
    		.expectStatus().isBadRequest(); // Verificando se o status de resposta é BadRequest, indicando uma falha na criação da tarefa
    }
}