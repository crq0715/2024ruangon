package com.service;

import com.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> getCommentsByAppId(Long appId);
    void deleteComment(Long commentId);
}

