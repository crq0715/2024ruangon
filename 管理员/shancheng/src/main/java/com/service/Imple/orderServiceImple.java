package com.service.Imple;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dao.orderDao;
import com.domain.order;
import com.domain.order;
import com.domain.order;
import com.service.orderService;
import com.untils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderServiceImple implements orderService {
    @Autowired
    private orderDao orderDao;
    @Override
    public int save(order order) {
        return orderDao.insert(order);
    }

    @Override
    public PageResult SelectPage(order order, int size, int current) {
        try
        {

            IPage page=new Page(current,size);
            QueryWrapper<order> queryWrapper = new QueryWrapper<order>();
            queryWrapper.like("name",order.getName());

            page=orderDao.selectPage(page, queryWrapper);
            while (true)
            {
                if(page.getRecords().size()==0&&current>1)
                {
                    page=new Page(current-1,size);
                    page=orderDao.selectPage(page, queryWrapper);
                }
                else
                {
                    break;
                }
            }



            return new PageResult(page.getTotal(),page.getRecords());


        }
        catch (Exception e)
        {
            return  null;
        }
    }

    @Override
    public PageResult SelectPage1(order order, int size, int current) {
        try
        {

            IPage page=new Page(current,size);
            QueryWrapper<order> queryWrapper = new QueryWrapper<order>();
            queryWrapper.like("name",order.getName());
            queryWrapper.eq("userId",order.getUserId());

            page=orderDao.selectPage(page, queryWrapper);
            while (true)
            {
                if(page.getRecords().size()==0&&current>1)
                {
                    page=new Page(current-1,size);
                    page=orderDao.selectPage(page, queryWrapper);
                }
                else
                {
                    break;
                }
            }



            return new PageResult(page.getTotal(),page.getRecords());


        }
        catch (Exception e)
        {
            return  null;
        }
    }

    @Override
    public Boolean cha(int userId, int musicId) {
        QueryWrapper<order> queryWrapper = new QueryWrapper<order>();
        queryWrapper.like("musicId",musicId);
        queryWrapper.eq("userId",userId);
       return orderDao.selectList(queryWrapper).size()>0;

    }


}
