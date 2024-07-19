package com.cyanrocks.oms.controller;

import com.cyanrocks.common.vo.api.ApiData;
import com.cyanrocks.oms.dao.SalesOrderDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/order")
public class SalesOrderController {
    @Resource
    private SalesOrderDao salesOrderRepository;

    @GetMapping("/all")
    public ApiData getAllSalesOrder() {
       // return salesOrderRepository.findAll();
        return new ApiData(444, "接口临时关闭,等候开放..");
    }

    // 注入 StringRedisTemplate
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis")
    public ApiData testRedis() {
        // 设置
        //this.stringRedisTemplate.opsForValue().set("title", "spring 中文网", Duration.ofMinutes(5));
        // 读取
        String val = this.stringRedisTemplate.opsForValue().get("title");
        log.info("val:{}",val);
        return new ApiData(200, val);
    }

}