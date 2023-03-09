package com.techelevator.tenmo.services;

import org.springframework.web.client.RestTemplate;

public class UserService {

    public static final String API_BASE_URL = "http://localhost:8080/user/account";
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;
}
