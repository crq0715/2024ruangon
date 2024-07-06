package com.controller;

import com.domain.order;
import com.untils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class orderController {
    @Autowired
    public com.service.orderService orderService;


    @GetMapping("/SelectPage/{size}/{current}")
    public PageResult selectPage(@PathVariable Integer size, @PathVariable Integer current, order order)
    {
        return  orderService.SelectPage(order,size,current);
    }


    @GetMapping("/SelectPage1/{size}/{current}")
    public PageResult selectPage1(@PathVariable Integer size, @PathVariable Integer current, order order)
    {
        return  orderService.SelectPage1(order,size,current);
    }

    @PostMapping("/Save")
    public  int Save(@RequestBody order order)
    {
        return orderService.save(order);

    }

    @GetMapping("/cha/{userId}/{musicId}")
    public Boolean cha(@PathVariable Integer userId, @PathVariable Integer musicId)
    {
        return  orderService.cha(userId,musicId);
    }
}
