package com.milktea.milkteashop.service;

import java.util.List;

import com.milktea.milkteashop.domain.TeaContact;
import com.milktea.milkteashop.exception.MilkTeaException;

public interface ContactService {
    
    /**
     * 保存联系信息
     * @param contact
     * @throws MilkTeaException
     */
    public void save(TeaContact contact) throws MilkTeaException;
    
    /**
     * 查询联系信息
     * @throws MilkTeaException
     */
    public List<TeaContact> queryContacts() throws MilkTeaException;

}
