package com.service;

import com.domain.like;
import com.domain.music;
import com.untils.PageResult;

public interface musicService {
    public PageResult SelectPage(music music, int size, int current);
    public PageResult SelectPageRe(music music, int size, int current);

    public PageResult SelectPageLike(music music, int size, int current,int userId);

    public int insert(music music);
    public int delete(int id);
    public int edit(music music);
    public music findById(int id);

    public  Boolean pan(int userId,int musicId);

    public  void add(like like);

    public void deleteById(int userId,int musicId);
}
