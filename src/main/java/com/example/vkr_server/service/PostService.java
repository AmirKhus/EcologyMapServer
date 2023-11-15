package com.example.vkr_server.service;

import com.example.vkr_server.model.Posts;
import com.example.vkr_server.model.User;

import java.util.List;

public interface PostService {
    Posts addPost(Posts post);
    List<Posts> getAllPosts();
    void deletePostById(Long id);
    Posts savePost(Posts post);
    List<Posts> getPostByUser(User user);
    Posts getPostById(Long id);
}
