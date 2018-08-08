package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.milktea.milkteashop.domain.TeaCarouselFigure;

public interface TeaCarouselFigureMapper {
    int deleteByPrimaryKey(String figureId);

    int insert(TeaCarouselFigure record);

    int insertSelective(TeaCarouselFigure record);

    TeaCarouselFigure selectByPrimaryKey(String figureId);

    int updateByPrimaryKeySelective(TeaCarouselFigure record);

    int updateByPrimaryKey(TeaCarouselFigure record);
    
    List<TeaCarouselFigure> selectByCondition(TeaCarouselFigure figure);
    
    @Select(value="select TEA_FIGURE_ID_SEQ.NEXTVAL from dual")
    String generateFigureId();
}