package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.domain.TeaCarouselFigure;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.CarouselFigureService;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
public class CarouselFigureController {

    @Autowired
    private CarouselFigureService figureService;
    
    @RequestMapping(value="saveCarouselFigure", method=RequestMethod.POST)
    public ResponseHeader saveCarouselFigure(TeaCarouselFigure figure) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.figureService.addCarouselFigure(figure);
        return header;
    }
    
    @RequestMapping(value="removeCarouselFigure/{figureId}", method=RequestMethod.DELETE)
    public ResponseHeader removeCarouselFigure(@PathVariable String figureId) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.figureService.removeCarouselFigure(figureId);
        return header;
    }
    
    @RequestMapping(value="modifyCarouselFigure", method=RequestMethod.POST)
    public ResponseHeader modifyCarouselFigure(TeaCarouselFigure figure) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.figureService.modifyCarouselFigure(figure);
        return header;
    }
    
    @RequestMapping(value="queryCarouselFigure", method=RequestMethod.GET)
    public ResponseBody<List<TeaCarouselFigure>> queryCarouselFigure(TeaCarouselFigure figure) 
            throws MilkTeaException{
        ResponseBody<List<TeaCarouselFigure>> responseBody = new ResponseBody<>();
        responseBody.setData(this.figureService.queryCarouselFigures(figure));
        return responseBody;
    }
}
