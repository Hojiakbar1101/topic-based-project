package uz.hojiakbar.newprogect.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uz.hojiakbar.newprogect.Entity.PostData;
import uz.hojiakbar.newprogect.model.Post;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    private final RestTemplate restTemplate;
    private final PostDataService postDataService;

    @Value("https://jsonplaceholder.typicode.com/")
    private String api;

    public PostService(RestTemplate restTemplate, PostDataService postDataService) {
        this.restTemplate = restTemplate;
        this.postDataService = postDataService;
    }
    public Post save(Post post){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity = new HttpEntity<>(post, headers);
        Post result = restTemplate.postForObject(api + "/posts",  entity, Post.class);
        return result;
    }
    public Post update(Long id, Post post){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity = new HttpEntity<>(post, headers);
        Post result = restTemplate.exchange(api + "/posts/" + id, HttpMethod.PUT,  entity, Post.class).getBody();
        return result;
    }
    public Object findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post[]> entity = new HttpEntity<>(headers);
        Post[] result = restTemplate.exchange(this.api + "/posts", HttpMethod.GET, entity, Post[].class).getBody();
        postDataService.saveAll(result);
        return result;
    }

    public List<Post> findAllByQueryParam(Long postId){
        HttpEntity<List<Post>> entity = new HttpEntity<>(getHeaders());
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(this.api + "/comments")
        .queryParam("postId", "{postId}")
                .encode()
        .toUriString();

        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);


        List<Post> result = restTemplate.exchange(
                urlTemplate , HttpMethod.GET, entity, List.class, params).getBody();
        return result;


    }
    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
    public Page<PostData> findAll(Pageable pageable){
        return postDataService.findAll(pageable);
    }
}
