package com.service.Imple;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.CommentDao;
import com.domain.Comment;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImple implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment addComment(Comment comment) {
        commentDao.insert(comment);
        return comment;
    }

    @Override
    public List<Comment> getCommentsByAppId(Long appId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("appId", appId);
        return commentDao.selectList(queryWrapper);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentDao.deleteById(commentId);
    }
}
