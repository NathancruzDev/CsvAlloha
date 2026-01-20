package com.example.demo.repository;

public interface ExternalHttpClient {
    String get(String url) throws Exception;
}
