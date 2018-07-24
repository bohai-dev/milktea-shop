package com.milktea.milkteashop.service;

import com.milktea.milkteashop.domain.TeaCarouselFigure;
import com.milktea.milkteashop.exception.MilkTeaException;

/**
 * 轮播图接口
 * @author caojia
 *
 */
public interface CarouselFigureService {

    /**
     * 添加轮播图
     * @param figureVo
     * @throws MilkTeaException
     */
    public void addCarouselFigure(TeaCarouselFigure figureVo) throws MilkTeaException;
    
    /**
     * 删除轮播图
     * @param figureId
     * @throws MilkTeaException
     */
    public void removeCarouselFigure(String figureId) throws MilkTeaException;
    
    /**
     * 修改轮播图
     * @param figureVo
     * @throws MilkTeaException
     */
    public void modifyCarouselFigure(TeaCarouselFigure figureVo) throws MilkTeaException;
    
    /**
     * 查询轮播图
     * @throws MilkTeaException
     */
    public void queryCarouselFigures() throws MilkTeaException;
    
    
}
