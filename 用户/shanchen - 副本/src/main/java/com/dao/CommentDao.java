package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends BaseMapper<Comment> {
    List<Comment> findByAppId(Long appId);
}
