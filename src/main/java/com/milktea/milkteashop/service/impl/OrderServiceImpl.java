package com.milktea.milkteashop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.milktea.milkteashop.dao.TeaAttributesInfoMapper;
import com.milktea.milkteashop.dao.TeaGoodsInfoMapper;
import com.milktea.milkteashop.dao.TeaOrderDetailsMapper;
import com.milktea.milkteashop.dao.TeaOrderInfoMapper;
import com.milktea.milkteashop.domain.TeaAttributesInfo;
import com.milktea.milkteashop.domain.TeaGoodsInfo;
import com.milktea.milkteashop.domain.TeaOrderDetails;
import com.milktea.milkteashop.domain.TeaOrderInfo;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.OrderService;
import com.milktea.milkteashop.vo.OrderDetailsNationVo;
import com.milktea.milkteashop.vo.OrderNationVo;
import com.milktea.milkteashop.vo.PageRequestVo;
import com.milktea.milkteashop.vo.PageResponseVo;
import com.milktea.milkteashop.vo.QueryOrdersRequestVo;
import com.milktea.milkteashop.vo.TeaAttributesInfoNationVo;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    
    static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Autowired
    private TeaOrderInfoMapper orderInfoMapper;
    
    @Autowired
    private TeaOrderDetailsMapper orderDetailsMapper;
    
    @Autowired
    private TeaGoodsInfoMapper goodsInfoMapper;
    
    @Autowired
    private TeaAttributesInfoMapper attributesInfoMapper;

    @Override
    public List<OrderNationVo> queryOrdersByUserNo(QueryOrdersRequestVo requestVo) throws MilkTeaException {
        
        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getUserNo())){
            throw new MilkTeaException(MilkTeaErrorConstant.USER_NO_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getLang())){
            throw new MilkTeaException(MilkTeaErrorConstant.LANG_REQUIRED);
        }
        
        List<TeaOrderInfo> orderInfos = null;
        try {
            orderInfos = this.orderInfoMapper.selectByCondition(requestVo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        List<OrderNationVo> result = null;
        if(orderInfos != null && orderInfos.size() > 0){
            result = new ArrayList<>();
            for (TeaOrderInfo info : orderInfos) {
                OrderNationVo target = new OrderNationVo();
                BeanUtils.copyProperties(info, target);
                
                //查询订单详情
                List<TeaOrderDetails> orderDetails = null;
                try {
                    orderDetails = this.orderDetailsMapper.selectByOrderNo(info.getOrderNo());
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                }
                
                List<OrderDetailsNationVo> nationVos = null;
                if(orderDetails != null && orderDetails.size() > 0){
                    nationVos = new ArrayList<>();
                    for (TeaOrderDetails details : orderDetails) {
                        OrderDetailsNationVo detailTarget = new OrderDetailsNationVo();
                        BeanUtils.copyProperties(details, detailTarget);
                        
                        //查询商品名称及图片
                        TeaGoodsInfo goodsInfo = null;
                        try {
                            goodsInfo = this.goodsInfoMapper.selectByGoodsId(details.getGoodsId());
                        } catch (Exception e) {
                            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                        }
                        if(goodsInfo != null){
                            if(requestVo.getLang().equals("zh")){
                                detailTarget.setGoodsName(goodsInfo.getCnGoodsName());
                                detailTarget.setGoodsPictureBig(goodsInfo.getCnGoodsPictureBig());
                            }else {
                                detailTarget.setGoodsName(goodsInfo.getUsGoodsName());
                                detailTarget.setGoodsPictureBig(goodsInfo.getUsGoodsPictureBig());
                            }
                        }
                        
                        //查询所购买商品的属性
                        List<TeaAttributesInfoNationVo> attrsVos = null;
                        List<TeaAttributesInfo> attributesInfos = null;
                        try {
                            attributesInfos = this.attributesInfoMapper.selectByOrderDetailId(details.getOrderDetailId());
                        } catch (Exception e) { 
                            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                        }
                        
                        if(attributesInfos != null && attributesInfos.size() > 0){
                            attrsVos = new ArrayList<>();
                            for (TeaAttributesInfo teaAttributesInfo : attributesInfos) {
                                TeaAttributesInfoNationVo attrTarget = new TeaAttributesInfoNationVo();
                                BeanUtils.copyProperties(teaAttributesInfo, attrTarget);
                                if(requestVo.getLang().equals("zh")){
                                    attrTarget.setAttrName(teaAttributesInfo.getCnAttrName());
                                }else {
                                    attrTarget.setAttrName(teaAttributesInfo.getUsAttrName());
                                }
                                attrsVos.add(attrTarget);
                            }
                        }
                        
                        detailTarget.setAttrs(attrsVos);
                        nationVos.add(detailTarget);
                    }
                    
                }
                target.setOrderDetails(nationVos);
                result.add(target);
            }
            
        }
        
        return result;
    }

    @Override
    public OrderNationVo queryOrderByOrderNo(QueryOrdersRequestVo requestVo) throws MilkTeaException {
        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getOrderNo())){
            throw new MilkTeaException(MilkTeaErrorConstant.ORDER_NO_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getLang())){
            throw new MilkTeaException(MilkTeaErrorConstant.LANG_REQUIRED);
        }
        
        TeaOrderInfo orderInfo = null;
        try {
            orderInfo = this.orderInfoMapper.selectByPrimaryKey(requestVo.getOrderNo());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        OrderNationVo result = null;
        if(orderInfo != null){
            result = new OrderNationVo();
            BeanUtils.copyProperties(orderInfo, result);
            
            //查询订单详情
            List<TeaOrderDetails> orderDetails = null;
            try {
                orderDetails = this.orderDetailsMapper.selectByOrderNo(orderInfo.getOrderNo());
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
            }
            
            List<OrderDetailsNationVo> nationVos = null;
            if(orderDetails != null && orderDetails.size() > 0){
                nationVos = new ArrayList<>();
                for (TeaOrderDetails details : orderDetails) {
                    OrderDetailsNationVo detailTarget = new OrderDetailsNationVo();
                    BeanUtils.copyProperties(details, detailTarget);
                    
                    //查询商品名称及图片
                    TeaGoodsInfo goodsInfo = null;
                    try {
                        goodsInfo = this.goodsInfoMapper.selectByGoodsId(details.getGoodsId());
                    } catch (Exception e) {
                        logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                        throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                    }
                    if(goodsInfo != null){
                        if(requestVo.getLang().equals("zh")){
                            detailTarget.setGoodsName(goodsInfo.getCnGoodsName());
                            detailTarget.setGoodsPictureBig(goodsInfo.getCnGoodsPictureBig());
                        }else {
                            detailTarget.setGoodsName(goodsInfo.getUsGoodsName());
                            detailTarget.setGoodsPictureBig(goodsInfo.getUsGoodsPictureBig());
                        }
                    }
                    
                    //查询所购买商品的属性
                    List<TeaAttributesInfoNationVo> attrsVos = null;
                    List<TeaAttributesInfo> attributesInfos = null;
                    try {
                        attributesInfos = this.attributesInfoMapper.selectByOrderDetailId(details.getOrderDetailId());
                    } catch (Exception e) { 
                        logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                        throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                    }
                    
                    if(attributesInfos != null && attributesInfos.size() > 0){
                        attrsVos = new ArrayList<>();
                        for (TeaAttributesInfo teaAttributesInfo : attributesInfos) {
                            TeaAttributesInfoNationVo attrTarget = new TeaAttributesInfoNationVo();
                            BeanUtils.copyProperties(teaAttributesInfo, attrTarget);
                            if(requestVo.getLang().equals("zh")){
                                attrTarget.setAttrName(teaAttributesInfo.getCnAttrName());
                            }else {
                                attrTarget.setAttrName(teaAttributesInfo.getUsAttrName());
                            }
                            attrsVos.add(attrTarget);
                        }
                    }
                    
                    detailTarget.setAttrs(attrsVos);
                    nationVos.add(detailTarget);
                }
                
            }
            result.setOrderDetails(nationVos);
        }
        
        return result;
    }

    @Override
    public PageResponseVo<OrderNationVo> queryOrderPage(PageRequestVo<QueryOrdersRequestVo> requestVo)
            throws MilkTeaException {

        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(requestVo.getPageNumber() == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PAGE_NUMBER_REQUIRED);
        }
        
        if(requestVo.getPageSize() == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PAGE_SIZE_REQUIRED);
        }
        
        PageResponseVo<OrderNationVo> result = new PageResponseVo<OrderNationVo>();
        
        List<OrderNationVo> resultList = null;
        List<TeaOrderInfo> orderInfos = null;
        try {
            PageHelper.startPage(requestVo.getPageNumber(), requestVo.getPageSize());
            orderInfos = this.orderInfoMapper.selectByCondition(requestVo.getParams());
            Page<TeaOrderInfo> page = (Page<TeaOrderInfo>)orderInfos;
            result.setTotal(page.getTotal());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(orderInfos != null && orderInfos.size() > 0){
            resultList = new ArrayList<>();
            for (TeaOrderInfo info : orderInfos) {
                OrderNationVo target = new OrderNationVo();
                BeanUtils.copyProperties(info, target);
                
                //查询订单详情
                List<TeaOrderDetails> orderDetails = null;
                try {
                    orderDetails = this.orderDetailsMapper.selectByOrderNo(info.getOrderNo());
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                }
                
                List<OrderDetailsNationVo> nationVos = null;
                if(orderDetails != null && orderDetails.size() > 0){
                    nationVos = new ArrayList<>();
                    for (TeaOrderDetails details : orderDetails) {
                        OrderDetailsNationVo detailTarget = new OrderDetailsNationVo();
                        BeanUtils.copyProperties(details, detailTarget);
                        
                        //查询商品名称及图片
                        TeaGoodsInfo goodsInfo = null;
                        try {
                            goodsInfo = this.goodsInfoMapper.selectByGoodsId(details.getGoodsId());
                        } catch (Exception e) {
                            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                        }
                        if(goodsInfo != null){
                            detailTarget.setGoodsName(goodsInfo.getCnGoodsName());
                            detailTarget.setGoodsPictureBig(goodsInfo.getCnGoodsPictureBig());
                        }
                        
                        //查询所购买商品的属性
                        List<TeaAttributesInfoNationVo> attrsVos = null;
                        List<TeaAttributesInfo> attributesInfos = null;
                        try {
                            attributesInfos = this.attributesInfoMapper.selectByOrderDetailId(details.getOrderDetailId());
                        } catch (Exception e) { 
                            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                        }
                        
                        if(attributesInfos != null && attributesInfos.size() > 0){
                            attrsVos = new ArrayList<>();
                            for (TeaAttributesInfo teaAttributesInfo : attributesInfos) {
                                TeaAttributesInfoNationVo attrTarget = new TeaAttributesInfoNationVo();
                                BeanUtils.copyProperties(teaAttributesInfo, attrTarget);
                                attrTarget.setAttrName(teaAttributesInfo.getCnAttrName());
                                attrsVos.add(attrTarget);
                            }
                        }
                        
                        detailTarget.setAttrs(attrsVos);
                        nationVos.add(detailTarget);
                    }
                    
                }
                target.setOrderDetails(nationVos);
                resultList.add(target);
            }
            
        }
        
        result.setRows(resultList);
        return result;
    }

}