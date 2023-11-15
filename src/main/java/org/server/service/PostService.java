package org.server.service;

import org.server.model.Posts;
import org.server.model.User;

import java.util.List;

public interface PostService {
    Posts addPost(Posts post);
    List<Posts> getAllPosts();
    void deletePostById(Long id);
    Posts savePost(Posts post);
    List<Posts> getPostByUser(User user);
    Posts getPostById(Long id);
}
