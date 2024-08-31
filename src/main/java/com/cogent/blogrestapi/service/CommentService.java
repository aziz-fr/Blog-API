package com.cogent.blogrestapi.service;

import com.cogent.blogrestapi.entity.Comment;

import java.util.List;

public interface CommentService {
  Comment createComment(Long postId, Comment newComment);

  List<Comment> getCommentsByPostId(Long postId);

  Comment getCommentById(Long postId, Long commentId);

  Comment updateComment(Long postId, Long commentId, Comment updateComment);
  void deleteComment(Long postId, Long commentId);
}
