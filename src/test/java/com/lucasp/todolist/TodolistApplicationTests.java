package com.lucasp.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.lucasp.todolist.entities.Todo;

// Anotação para configurar o ambiente de teste com um servidor web em uma porta aleatória
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
    "spring.config.location=classpath:application-test.properties"
})
class TodolistApplicationTests {
    // Injeção do cliente de teste para fazer solicitações HTTP
    @Autowired
    private WebTestClient webTestClient;

    // Teste para criar um Todo com sucesso
    @Test
    void testCreateTodoSuccess() {
        // Cria um objeto Todo para ser enviado na requisição
        var todo = new Todo("todo 1", "desc todo1", false, 1);

        // Realiza uma solicitação POST para o endpoint /todos
        webTestClient
            .post()
            .uri("/todos")
            .bodyValue(todo) // Define o corpo da requisição como o objeto Todo
            .exchange() // Envia a requisição
            // Espera um status de resposta OK (200)
            .expectStatus().isOk()
            .expectBody() // Verifica o corpo da resposta
            // Verifica se a resposta é um array JSON com comprimento 1
            .jsonPath("$").isArray().jsonPath("$.length()").isEqualTo(1)
            // Verifica se os atributos do Todo na resposta correspondem aos atributos do objeto enviado
            .jsonPath("$[0].nome").isEqualTo(todo.getNome())
            .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
            .jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
            .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
    }
    
    // Teste para verificar a falha na criação de um Todo
    @Test
    void testCreateFailure() {
        // Envia uma requisição POST com um objeto Todo vazio
        webTestClient
            .post()
            .uri("/todos")
            .bodyValue(new Todo("", "", false, 0)) // Objeto Todo vazio
            .exchange() // Envia a requisição
            // Espera um status de resposta de Bad Request (400)
            .expectStatus().isBadRequest();
    }
}