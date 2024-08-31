package com.cogent.blogrestapi.service.impl;

import com.cogent.blogrestapi.entity.Comment;
import com.cogent.blogrestapi.entity.Post;
import com.cogent.blogrestapi.exception.BlogApiException;
import com.cogent.blogrestapi.exception.ResourceNotFoundException;
import com.cogent.blogrestapi.repository.CommentRepository;
import com.cogent.blogrestapi.repository.PostRepository;
import com.cogent.blogrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentRepository commentRepository;

  @Override
  public Comment createComment(Long postId, Comment newComment) {
    // retrieve the post by id from database
    Post post = postRepository
            .findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

    // set post to comment entity
    newComment.setPost(post);
    // save comment to database
    return commentRepository.save(newComment);
  }

  @Override
  public List<Comment> getCommentsByPostId(Long postId) {
    // retrieve comment by postId - create a custom method in repository
    List<Comment> comments = commentRepository.findByPostId(postId);
    return comments;
  }

  @Override
  public Comment getCommentById(Long postId, Long commentId) {
    // retrieve the post by postId from database
    Post post = postRepository
            .findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

    // retrieve comment by commentId
    Comment comment = commentRepository
            .findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
    if (!comment.getPost().getId().equals(post.getId())){
      throw new BlogApiException(HttpStatus.BAD_REQUEST, "commend does not belong to post");
    }
    return comment;
  }

  @Override
  public Comment updateComment(Long postId, Long commentId, Comment updateComment) {
    // retrieve the post by postId from database
    Post post = postRepository
            .findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

    // retrieve comment by commit id
    Comment comment = commentRepository
            .findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
    if (!comment.getPost().getId().equals(post.getId())){
      throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
    }

    comment.setName(updateComment.getName());
    comment.setEmail(updateComment.getEmail());
    comment.setBody(updateComment.getBody());
    return commentRepository.save(comment);
  }

  @Override
  public void deleteComment(Long postId, Long commentId) {
// retrieve the post by postId from database
    Post post = postRepository
            .findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

    // retrieve comment by commit id
    Comment comment = commentRepository
            .findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
    if (!comment.getPost().getId().equals(post.getId())){
      throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
    }
    commentRepository.deleteById(commentId);
  }
}
