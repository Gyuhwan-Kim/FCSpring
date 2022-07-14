package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.apache.coyote.Response;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Service
public class RestTemplateService {
    // http://localhost/api/server/hello 호출
    // reponse 받아올 것
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "lueble")
                .queryParam("age", 29)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class);
        ResponseEntity<UserResponse> result
                = restTemplate.getForEntity(uri, UserResponse.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse post(){
        // http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "lueble")
                .toUri();

        System.out.println(uri);

        // HTTP request body -> object -> object mapper -> JSON
        // -> rest template -> HTTP body에 JSON으로
        UserRequest req = new UserRequest();
        req.setName("lueble");
        req.setAge(29);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }

    public UserResponse exchange(){
        // http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "lueble")
                .toUri();

        System.out.println(uri);

        // HTTP request body -> object -> object mapper -> JSON
        // -> rest template -> HTTP body에 JSON으로
        UserRequest req = new UserRequest();
        req.setName("lueble");
        req.setAge(29);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "abcde")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);

        return response.getBody();
    }

    public UserResponse genericExchage(){
        // http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "lueble")
                .toUri();

        System.out.println(uri);

        // HTTP request body -> object -> object mapper -> JSON
        // -> rest template -> HTTP body에 JSON으로
        UserRequest userRequest = new UserRequest();
        userRequest.setName("lueble");
        userRequest.setAge(29);

        Req<UserRequest> req = new Req<UserRequest>();
        req.setHeader(
            new Req.Header()
        );
        req.setResBody(
            userRequest
        );

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "abcde")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});

        return response.getBody().getResBody();
    }

    public String naver(){
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", "코노")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "2MqdmUGPmPGw6E7ytaBN")
                .header("X-Naver-Client-Secret", "5Eyhbq4ccz")
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        return responseEntity.getBody();
    }
}
