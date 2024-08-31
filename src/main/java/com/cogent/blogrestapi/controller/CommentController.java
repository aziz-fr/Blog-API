package com.cogent.blogrestapi.controller;

import com.cogent.blogrestapi.entity.Comment;
import com.cogent.blogrestapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class CommentController {

  @Autowired
  private CommentService commentService;

  @PostMapping("/{postId}/comments")
  public ResponseEntity<Comment> createComment(@PathVariable("postId") Long postId,
                                               @Valid @RequestBody Comment comment){
    Comment data = commentService.createComment(postId ,comment);
    return new ResponseEntity<>(data, HttpStatus.CREATED);
  }

  @GetMapping("/{postId}/comments")
  public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable("postId") Long postId) {
    List<Comment> data = commentService.getCommentsByPostId(postId);
//    if (data == null)
//      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(data, HttpStatus.OK);
  }

  @GetMapping("/{postId}/comments/{commentId}")
  public ResponseEntity<Comment> getCommentsById(@PathVariable("postId") long postId,
                                                       @PathVariable("commentId") Long commentId) {
    Comment data = commentService.getCommentById(postId, commentId);
    return new ResponseEntity<>(data, HttpStatus.OK);
  }

  @PutMapping("/{postId}/comments/{commentId}")
  public ResponseEntity<Comment> updateComment(@PathVariable("postId") Long postId,
                                         @PathVariable("commentId") Long commentId,
                                         @Valid @RequestBody Comment comment){
    Comment data = commentService.updateComment(postId, commentId, comment);
    return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{postId}/comments/{commentId}")
  public ResponseEntity<String> deleteComment(@PathVariable("postId") Long postId,
                                              @PathVariable("commentId") Long commentId){
    commentService.deleteComment(postId, commentId);
    return new ResponseEntity<>("comment deleted successfully", HttpStatus.NO_CONTENT);
  }

}
