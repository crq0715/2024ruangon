package com.service;

import com.domain.order;
import com.domain.order;
import com.untils.PageResult;

import java.util.List;

public interface orderService {
    public int save(order order);
    public PageResult SelectPage(order order, int size, int current);
    public PageResult SelectPage1(order order, int size, int current);

  public Boolean cha(int userId,int musicId);

}
