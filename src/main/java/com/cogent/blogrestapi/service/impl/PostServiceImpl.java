package com.cogent.blogrestapi.service.impl;

import com.cogent.blogrestapi.entity.Post;
import com.cogent.blogrestapi.exception.ResourceNotFoundException;
import com.cogent.blogrestapi.repository.PostRepository;
import com.cogent.blogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;

  @Override
  public Post createPost(Post newPost) {
    return postRepository.save(newPost);
  }

  @Override
  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }

  @Override
  public Post getPostById(Long postId) {
    Post post = postRepository
            .findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
    return post;
  }

  @Override
  public Post updatePost(Long postId, Post updatePost) {
    // get post by id from database
    Post post = postRepository
            .findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
    // get updated data
    post.setTitle(updatePost.getTitle());
    post.setDescription(updatePost.getDescription());
    post.setContent(updatePost.getContent());
    // save the changes
    return postRepository.save(post);
  }

  @Override
  public void deletePost(Long postId) {
    // get post by id from database
    Post post = postRepository
            .findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
    // delete
    postRepository.deleteById(postId);
  }
}
