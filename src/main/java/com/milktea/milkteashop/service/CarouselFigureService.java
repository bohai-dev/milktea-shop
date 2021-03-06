package com.milktea.milkteashop.service;

import java.util.List;

import com.milktea.milkteashop.domain.TeaCarouselFigure;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.vo.TeaCarouselFigureNationVo;

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
    public void addCarouselFigure(TeaCarouselFigure figure) throws MilkTeaException;
    
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
    public void modifyCarouselFigure(TeaCarouselFigure figure) throws MilkTeaException;
    
    /**
     * 查询轮播图
     * @throws MilkTeaException
     */
    public List<TeaCarouselFigure> queryCarouselFigures(TeaCarouselFigure figure) throws MilkTeaException;
    
    /**
     * 查询轮播图(支持国际化)
     * @throws MilkTeaException
     */
    public List<TeaCarouselFigureNationVo> queryCarouselFigureNation(TeaCarouselFigureNationVo figure) throws MilkTeaException;
    
}
