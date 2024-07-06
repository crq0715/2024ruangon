package com.service;

import com.domain.music;
import com.untils.PageResult;

public interface musicService {
    public PageResult SelectPage(music music, int size, int current);

    public int insert(music music);
    public int delete(int id);
    public int edit(music music);
    public music findById(int id);
    PageResult adminSelectPage(music music, int size, int current);
    int updateStatus(int id, String status);
}
