package com.milktea.milkteashop.service;

import java.util.List;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.vo.OrderNationVo;
import com.milktea.milkteashop.vo.PageRequestVo;
import com.milktea.milkteashop.vo.PageResponseVo;
import com.milktea.milkteashop.vo.QueryOrdersRequestVo;

public interface OrderService {
    
    /**
     * 根据用户名查询订单信息(支持国际化)
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    public List<OrderNationVo> queryOrdersByUserNo(QueryOrdersRequestVo requestVo) throws MilkTeaException;
    
    /**
     * 根据订单编号查询订单详情(支持国际化)
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    public OrderNationVo queryOrderByOrderNo(QueryOrdersRequestVo requestVo) throws MilkTeaException;
    
    /**
     * 分页查询订单信息
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    public PageResponseVo<OrderNationVo> queryOrderPage(PageRequestVo<QueryOrdersRequestVo> requestVo) throws MilkTeaException;

}
