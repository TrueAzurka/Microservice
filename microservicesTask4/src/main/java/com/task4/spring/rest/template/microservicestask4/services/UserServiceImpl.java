package com.task4.spring.rest.template.microservicestask4.services;

import com.task4.spring.rest.template.microservicestask4.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl {
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private String URL = "http://94.198.50.185:7081/api/users";

//    @Autowired
    public UserServiceImpl(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.headers.set("Cookie",
                String.join(";", Objects.requireNonNull(restTemplate.headForHeaders(String.valueOf(URL)).get("Set-Cookie"))));
    }


    public String getAnswer() {
        return addUser().getBody() + updateUser().getBody() + deleteUser(3L).getBody();
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() { });
        System.out.println(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    public ResponseEntity<String> addUser() {
        User user = new User(3L, "James", "Brown", (byte) 5);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.postForEntity(URL, entity, String.class);
    }

    private ResponseEntity<String> updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 5);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
    }

    private ResponseEntity<String> deleteUser(Long id) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
    }
}

