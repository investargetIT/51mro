package com.cyanrocks.oms.controller;

import com.cyanrocks.common.constant.OrderServiceNames;
import com.cyanrocks.common.vo.api.ApiData;
import com.cyanrocks.oms.controller.base.BaseController;
import com.cyanrocks.oms.dao.SalesOrderDao;
import com.cyanrocks.oms.entity.SalesOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/order")
public class SalesOrderController extends BaseController {
    @Resource
    private SalesOrderDao salesOrderRepository;

    @GetMapping("/all")
    public @ResponseBody Iterable<SalesOrder> getAllSalesOrder() {
        return salesOrderRepository.findAll();
    }

    // 注入 StringRedisTemplate
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis")
    public ApiData testRedis() {
        // 设置
        this.stringRedisTemplate.opsForValue().set("name", "小明", Duration.ofMinutes(5));
        // 读取
        String val = this.stringRedisTemplate.opsForValue().get("name");
        log.info("val:{}",val);
        return new ApiData(200, val);
    }

}