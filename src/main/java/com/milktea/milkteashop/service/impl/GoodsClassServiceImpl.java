package com.milktea.milkteashop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteashop.dao.TeaClassInfoMapper;
import com.milktea.milkteashop.domain.TeaClassInfo;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.GoodsClassService;

@Service("goodsClassService")
public class GoodsClassServiceImpl implements GoodsClassService {
    
    static Logger logger = LoggerFactory.getLogger(GoodsClassServiceImpl.class);
    
    @Autowired
    private TeaClassInfoMapper classInfoMapper;

    @Override
    public void addClass(TeaClassInfo classInfo) throws MilkTeaException {
        
        if(classInfo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getCnClassName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_CLASS_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getUsClassName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_CLASS_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getCnClassLogo())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_CLASS_LOGO_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getUsClassLogo())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_CLASS_LOGO_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getClassType())){
            throw new MilkTeaException(MilkTeaErrorConstant.CLASS_TYPE_REQUIRED);
        }
        
        if(classInfo.getIndexNo() == null){
            throw new MilkTeaException(MilkTeaErrorConstant.INDEX_NO_REQUIRED);
        }
        
        try {
            Long count = this.classInfoMapper.countByIndexNo(classInfo.getIndexNo());
            if(count > 0){
                throw new MilkTeaException(MilkTeaErrorConstant.INDEX_NO_EXISTS);
            }
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        
        try {
            classInfo.setClassId(this.classInfoMapper.generateClassId());
            this.classInfoMapper.insertSelective(classInfo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public void removeClass(String classId) throws MilkTeaException {
        
        if(StringUtils.isBlank(classId)){
            throw new MilkTeaException(MilkTeaErrorConstant.CLASS_ID_REQUIRED);
        }
        
        try {
            this.classInfoMapper.deleteByPrimaryKey(classId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public void modifyClass(TeaClassInfo classInfo) throws MilkTeaException {

        if(classInfo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getClassId())){
            throw new MilkTeaException(MilkTeaErrorConstant.CLASS_ID_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getCnClassName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_CLASS_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getUsClassName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_CLASS_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getCnClassLogo())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_CLASS_LOGO_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getUsClassLogo())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_CLASS_LOGO_REQUIRED);
        }
        
        if(StringUtils.isBlank(classInfo.getClassType())){
            throw new MilkTeaException(MilkTeaErrorConstant.CLASS_TYPE_REQUIRED);
        }
        
        try {
            this.classInfoMapper.updateByPrimaryKey(classInfo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public List<TeaClassInfo> queryClassInfo() throws MilkTeaException {

        List<TeaClassInfo> list = null;
        try {
            list = this.classInfoMapper.selectAll();
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        return list;
    }

    @Override
    public TeaClassInfo queryClassInfo(String classId) throws MilkTeaException {

        TeaClassInfo classInfo = null;
        try {
            classInfo = this.classInfoMapper.selectByPrimaryKey(classId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        return classInfo;
    }

}
