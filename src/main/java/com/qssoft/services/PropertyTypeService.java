package com.qssoft.services;

import com.qssoft.dao.PropertyTypeDAO;
import com.qssoft.entities.PropertyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyTypeService {
    @Autowired
    private PropertyTypeDAO propertyTypeDAO;

    public List<PropertyType> getAllPropertyTypes() {
        return propertyTypeDAO.getAll();
    }

}
