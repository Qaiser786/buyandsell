package com.qssoft.services;

import com.qssoft.dao.DealTypeDAO;
import com.qssoft.entities.DealType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealTypeService
{
    @Autowired
    private DealTypeDAO dealTypeDAO;

    public List<DealType> getAllDealTypes() {
        return dealTypeDAO.getAllDealTypes();
    }
}
