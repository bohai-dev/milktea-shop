package com.milktea.milkteashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.OrderService;
import com.milktea.milkteashop.vo.HandleOrderRequestVo;
import com.milktea.milkteashop.vo.ModifyOrderStatusRequestVo;
import com.milktea.milkteashop.vo.OrderNationVo;
import com.milktea.milkteashop.vo.PageRequestVo;
import com.milktea.milkteashop.vo.PageResponseVo;
import com.milktea.milkteashop.vo.QueryOrdersRequestVo;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;
import com.milktea.milkteashop.websocket.WebsocketHandler;

@RestController
@CrossOrigin
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    WebsocketHandler websocketHandler;

    /**
     * 根据用户名查询订单信息(支持国际化)
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryOrdersByUserNo", method=RequestMethod.POST)
    public PageResponseVo<OrderNationVo> queryOrdersByUserNo(@RequestBody(required=false) PageRequestVo<QueryOrdersRequestVo> requestVo) throws MilkTeaException{
        
        return this.orderService.queryOrdersByUserNo(requestVo);
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
     * 推送新的订单信息
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="handleOrder", method=RequestMethod.POST)
    public ResponseHeader handleOrder(@RequestBody(required=false) HandleOrderRequestVo requestVo) throws MilkTeaException{
        QueryOrdersRequestVo ordersRequestVo = new QueryOrdersRequestVo();
        ordersRequestVo.setOrderNo(requestVo.getOrderNo());
        ordersRequestVo.setLang("zh");
        OrderNationVo nationVo = orderService.queryOrderByOrderNo(ordersRequestVo);
        if(nationVo != null){
            nationVo.setMessageType("1");
            try {
                websocketHandler.sendMessage(nationVo.getStoreNo(), JSON.toJSONString(nationVo,SerializerFeature.WriteMapNullValue));
            } catch (Exception e) {
                throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION,e);
            }
        }
        return new ResponseHeader();
    }
    
    /**
     * 更新订单状态
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="modifyOrderStatus", method=RequestMethod.POST)
    public ResponseHeader modifyOrderStatus(@RequestBody(required=false) ModifyOrderStatusRequestVo requestVo) throws MilkTeaException{
        this.orderService.modifyOrderStatus(requestVo);
        
        QueryOrdersRequestVo ordersRequestVo = new QueryOrdersRequestVo();
        ordersRequestVo.setOrderNo(requestVo.getOrderNo());
        ordersRequestVo.setLang("zh");
        OrderNationVo nationVo = orderService.queryOrderByOrderNo(ordersRequestVo);
        if(nationVo != null){
            if("1".equals(nationVo.getOrderStatus())){
                //制作完成，修改推送
                nationVo.setMessageType("2");
            }else if ("2".equals(nationVo.getOrderStatus())) {
                //取货完成，完成订单推送
                nationVo.setMessageType("3");
            }
            try {
                websocketHandler.sendMessage(nationVo.getStoreNo(), JSON.toJSONString(nationVo,SerializerFeature.WriteMapNullValue));
            } catch (Exception e) {
                throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION,e);
            }
        }
        return new ResponseHeader();
    }
}
