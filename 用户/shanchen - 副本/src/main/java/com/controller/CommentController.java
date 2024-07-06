package com.controller;

import com.domain.Comment;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/{appId}")
    public List<Comment> getCommentsByAppId(@PathVariable Long appId) {
        return commentService.getCommentsByAppId(appId);
    }
}
