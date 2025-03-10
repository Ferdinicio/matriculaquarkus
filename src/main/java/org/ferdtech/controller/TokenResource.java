package org.ferdtech.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Path("/token")
public class TokenResource {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(
            @FormParam("username") String username,
            @FormParam("password") String password) throws Exception {

        // Dados da requisição
        String formData = "username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) +
                "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8) +
                "&grant_type=password" +
                "&client_id=backend-service" +
                "&client_secret=secret";

        // Cria a requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8180/realms/quarkus/protocol/openid-connect/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + java.util.Base64.getEncoder()
                        .encodeToString("backend-service:secret".getBytes(StandardCharsets.UTF_8)))
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        // Envia a requisição e obtém a resposta
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Retorna a resposta do Keycloak
        return Response.ok(response.body()).build();
    }
}