package com.cogent.blogrestapi.service;

import com.cogent.blogrestapi.entity.Post;

import java.util.List;

public interface PostService {
  Post createPost(Post newPost);
  List<Post> getAllPosts();
  Post getPostById(Long postId);
  Post updatePost(Long postId, Post updatePost);
  void deletePost(Long postId);

}
