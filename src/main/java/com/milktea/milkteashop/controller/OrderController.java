package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.OrderService;
import com.milktea.milkteashop.vo.HandleOrderRequestVo;
import com.milktea.milkteashop.vo.OrderNationVo;
import com.milktea.milkteashop.vo.PageRequestVo;
import com.milktea.milkteashop.vo.PageResponseVo;
import com.milktea.milkteashop.vo.QueryOrdersRequestVo;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
@CrossOrigin
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    /**
     * 根据用户名查询订单信息(支持国际化)
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryOrdersByUserNo", method=RequestMethod.POST)
    public ResponseBody<List<OrderNationVo>> queryOrdersByUserNo(@RequestBody(required=false) QueryOrdersRequestVo requestVo) throws MilkTeaException{
        
        ResponseBody<List<OrderNationVo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.orderService.queryOrdersByUserNo(requestVo));
        return responseBody;
    }
    
    /**
     * 根据订单编号查询订单信息（支持国际化）
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryOrderByOrderNo", method=RequestMethod.POST)
    public ResponseBody<OrderNationVo> queryOrderByOrderNo(@RequestBody(required=false) QueryOrdersRequestVo requestVo) throws MilkTeaException{
        ResponseBody<OrderNationVo> responseBody = new ResponseBody<>();
        responseBody.setData(this.orderService.queryOrderByOrderNo(requestVo));
        return responseBody;
    }
    
    /**
     * 分页查询订单信息
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryOrderPage", method=RequestMethod.POST)
    public PageResponseVo<OrderNationVo> queryOrderPage(@RequestBody(required=false) PageRequestVo<QueryOrdersRequestVo> requestVo) throws MilkTeaException{
        return this.orderService.queryOrderPage(requestVo);
    }
    
    /**
     * 推送订单信息
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="handleOrder", method=RequestMethod.POST)
    public ResponseHeader handleOrder(@RequestBody(required=false) HandleOrderRequestVo requestVo) throws MilkTeaException{
        
        return new ResponseHeader();
    }
}
