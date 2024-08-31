package com.cogent.blogrestapi.controller;

import com.cogent.blogrestapi.entity.Post;
import com.cogent.blogrestapi.service.CommentService;
import com.cogent.blogrestapi.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class PostController {

  @Autowired
  private PostService postService;

  @PostMapping
  public ResponseEntity<Post> createPost(@RequestBody Post post){
    Post data = postService.createPost(post);
    return new ResponseEntity<>(data, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Post>> getAllPosts(){
    List<Post> data = postService.getAllPosts();
    return new ResponseEntity<>(data, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Post> getPostById(@PathVariable("id") Long postId){
    Post data = postService.getPostById(postId);
    if (data == null)
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(data, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Post> updatePost(@PathVariable("id") Long postId,
                                         @Valid @RequestBody Post post){
    Post data = postService.updatePost(postId, post);
    return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable("id") Long postId){
    postService.deletePost(postId);
    return new ResponseEntity<>("post deleted successfully", HttpStatus.NO_CONTENT);
  }
}
