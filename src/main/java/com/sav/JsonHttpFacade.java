package com.sav;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sav.dto.GetUsersResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@RequiredArgsConstructor
public class JsonHttpFacade{
    private final HttpClient client = HttpClient.newBuilder().build();
    private final ObjectMapper mapper = new ObjectMapper();

    public<T> T get(String uri, Class<T> responseClass) {
        HttpRequest  request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        T response = null;
        try {
            response = mapper.readValue(httpResponse.body(), responseClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return (T)response;
    }


    public <T> T post (String uri, Object body,Class<T> responseClass) {
        HttpRequest request=null;

        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                    .header("Content-Type", "application/json")
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        T response = null;
        try {
            response = mapper.readValue(httpResponse.body(), responseClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  response;
    }


    public <T> T getAuthorized (String uri, Class<T> responseClass, String token) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        T response = null;
        try {
            response = mapper.readValue(httpResponse.body(), responseClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

    public <T> T postAuthorized (String uri, Object body,Class<T> responseClass, String token) {
        HttpRequest request=null;

        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        T response = null;
        try {
            response = mapper.readValue(httpResponse.body(), responseClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
