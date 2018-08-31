package com.milktea.milkteashop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteashop.dao.TeaContactMapper;
import com.milktea.milkteashop.domain.TeaContact;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService {
    
    static Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
    
    @Autowired
    private TeaContactMapper contactMapper;

    @Override
    public void save(TeaContact contact) throws MilkTeaException {
        
        if(contact == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(contact.getTelephone())){
            throw new MilkTeaException(MilkTeaErrorConstant.TELEPHONE_REQUIRED);
        }
        
        if(StringUtils.isBlank(contact.getName())){
            throw new MilkTeaException(MilkTeaErrorConstant.NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(contact.getContext())){
            throw new MilkTeaException(MilkTeaErrorConstant.CONTEXT_REQUIRED);
        }
        

        try {
            this.contactMapper.insertSelective(contact);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public List<TeaContact> queryContacts() throws MilkTeaException {

        List<TeaContact> list = null;
        try {
            list = this.contactMapper.selectAll();
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        return list;
    }

}
