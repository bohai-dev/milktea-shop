package com.milktea.milkteashop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteashop.dao.TeaCarouselFigureMapper;
import com.milktea.milkteashop.domain.TeaCarouselFigure;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.CarouselFigureService;

@Service("carouselFigureService")
public class CarouselFigureServiceImpl implements CarouselFigureService {
    
    static Logger logger = LoggerFactory.getLogger(CarouselFigureServiceImpl.class);
    
    @Autowired
    private TeaCarouselFigureMapper carouselFigureMapper;

    @Override
    public void addCarouselFigure(TeaCarouselFigure figure) throws MilkTeaException {

        if(figure == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(figure.getCnFigureAddress())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_FIGURE_ADDRESS_REQUIRED);
        }
        
        if(StringUtils.isBlank(figure.getUsFigureAddress())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_FIGURE_ADDRESS_REQUIRED);
        }
        
        try {
            figure.setFigureId(this.carouselFigureMapper.generateFigureId());
            this.carouselFigureMapper.insert(figure);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public void removeCarouselFigure(String figureId) throws MilkTeaException {

        if(StringUtils.isBlank(figureId)){
            throw new MilkTeaException(MilkTeaErrorConstant.FIGURE_ID_REQUIRED);
        }
        
        try {
            this.carouselFigureMapper.deleteByPrimaryKey(figureId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public void modifyCarouselFigure(TeaCarouselFigure figure) throws MilkTeaException {

        if(figure == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(figure.getFigureId())){
            throw new MilkTeaException(MilkTeaErrorConstant.FIGURE_ID_REQUIRED);
        }
        
        if(StringUtils.isBlank(figure.getCnFigureAddress())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_FIGURE_ADDRESS_REQUIRED);
        }
        
        if(StringUtils.isBlank(figure.getUsFigureAddress())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_FIGURE_ADDRESS_REQUIRED);
        }
        
        try {
            this.carouselFigureMapper.updateByPrimaryKey(figure);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
    }

    @Override
    public List<TeaCarouselFigure> queryCarouselFigures(TeaCarouselFigure figure) throws MilkTeaException {
        
        if(figure == null ){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(figure.getStoreNo())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        List<TeaCarouselFigure> list = null;
        try {
            list = this.carouselFigureMapper.selectByCondition(figure);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        return list;
    }

}
