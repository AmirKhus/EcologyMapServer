package org.server.service;

import org.server.exceptions.ResourceNotFoundException;
import org.server.model.Posts;
import org.server.model.User;
import org.server.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public Posts addPost(Posts post) {
        return postsRepository.save(post);
    }

    @Override
    public List<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public void deletePostById(Long id) {
        Posts post = postsRepository.getOne(id);

        postsRepository.delete(post);
    }

    @Override
    public Posts savePost(Posts post) {
        return postsRepository.save(post);
    }

    @Override
    public List<Posts> getPostByUser(User user) {
        return postsRepository.findAllByPosterUser(user);
    }

    @Override
    public Posts getPostById(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(("User not exist with id: " + id)));
    }
}
