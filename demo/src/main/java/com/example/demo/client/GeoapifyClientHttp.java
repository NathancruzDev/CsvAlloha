package com.example.demo.client;

import com.example.demo.repository.ExternalHttpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeoapifyClientHttp implements ExternalHttpClient {
    //instancia da conexao web.
    private final HttpClient httpClient=HttpClient.newHttpClient();
    GeoapifyClient geoapifyClient=new GeoapifyClient();

    @Override
    public String get(String url) throws Exception {

        HttpRequest httpRequest= HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        HttpResponse<String> httpResponse= httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());

        return httpResponse.body();
    }
}
