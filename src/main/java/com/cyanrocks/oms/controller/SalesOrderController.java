package com.cyanrocks.oms.controller;

import com.cyanrocks.common.constant.OrderServiceNames;
import com.cyanrocks.common.vo.api.ApiData;
import com.cyanrocks.oms.controller.base.BaseController;
import com.cyanrocks.oms.dao.SalesOrderDao;
import com.cyanrocks.microService.helper.Hc;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/order")
public class SalesOrderController extends BaseController {
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
        this.stringRedisTemplate.opsForValue().set("name", "小明", Duration.ofMinutes(5));
        // 读取
        String val = this.stringRedisTemplate.opsForValue().get("name");
        log.info("val:{}",val);
        return new ApiData(200, val);
    }



    /**
     * 查询订单修改详情
     *
     * @param orderNo 是 String 订单编号
     * @return errCode String 错误编码，200成功，其余失败
     * @return errMsg String 错误提示信息
     * @return data Object 订单修改详情信息
     * @return sid Long 申请id
     * @return workflowId Long 工作流流程id
     * @return requestNo String 申请编号
     * @return createTime String 创建时间（yyyy-MM-dd HH:mm:ss）
     * @return requestStatus Integer 审批状态。10-草稿；20-审批中；80-审批通过；90-审批拒绝；91-退回；99-取消
     * @return requestStatusName String 审批状态描述
     * @return orderNo String 订单号
     * @return requestType Integer 申请类型。1-整单取消；2-部分取消；3-订单修改
     * @return requestTypeName String 申请类型描述
     * @return orderChannelName String 订单渠道名称
     * @return updateTypeName String 修改类型
     * @return remark String 备注
     * @return customerCode String 客户编号
     * @return customerName String 客户名称
     * @return updateReasonName String 修改原因名称
     * @return cancelReasonName String 取消原因名称
     * @return cancelTypeName String 取消类型名称
     * @return resjson {"errCode":200,"errMsg":"成功","data":{"sid":10,"requestNo":"M220530164936968","createTime":"2022-05-30 16:49:36","createName":"John Hu","requestStatusName":"审批中","orderNo":"101700241","requestTypeName":"订单修改","orderChannelName":"OMS","requestChannelName":"OMS","updateTypeName":"修改发货日期","remark":"测试","customerCode":"0600300231","customerName":"上海虹桥机航空油料有限公司","updateReasonName":"修改发货日期","workflowId":43145}}
     * @category 开放平台/订单
     * @author stevenqin
     */
    @RequestMapping("/getOrderUpdate/{orderNo}")
    public ApiData getOrderUpdate(@PathVariable String orderNo) {
        return Hc.sendObject(String.format(OrderServiceNames.GET_ORDER_UPDATE, orderNo),
                null, new TypeReference<ApiData>() {
                });
    }

}