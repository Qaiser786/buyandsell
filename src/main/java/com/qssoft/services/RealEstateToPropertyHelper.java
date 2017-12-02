package com.qssoft.services;

import com.qssoft.dao.DealTypeDAO;
import com.qssoft.dao.PropertyStatusDAO;
import com.qssoft.dao.UserDAO;
import com.qssoft.dto.Property;
import com.qssoft.entities.DealType;
import com.qssoft.entities.PropertyPicture;
import com.qssoft.entities.RealEstate;
import com.qssoft.entities.Status;
import com.qssoft.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class RealEstateToPropertyHelperService
{
    @Autowired
    private DealTypeDAO dealTypeDAO;

    @Autowired
    private PropertyStatusDAO propertyStatusDAO;

    @Autowired
    private UserDAO userDAO;

    public Property entityToProperty(RealEstate entity)
    {
        DealType dealType = dealTypeDAO.getDealTypeById(entity.getDealTypeId());

        Status status = propertyStatusDAO.getStatusById(entity.getStatusId());

        User owner = userDAO.getUserById(entity.getOwnerId());

        Property result = new Property(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDealTypeId(),
                entity.getPrice(),
                entity.getOwnerId(),
                entity.getAddress(),
                entity.getNearbyLocations(),
                entity.getAdminNote(),
                entity.getStatusId(),
                entity.getLatitude(),
                entity.getLongitude(),
                dealType.getName(),
                status.getName(),
                owner.getLogin(),
                entity.getPictureCode(),
                entity.getCity(),
                entity.getPropertyTypeId(),
                entity.getPropertyType() != null ? entity.getPropertyType().getName() : null,
                entity.getImg1(),
                entity.getImg2(),
                entity.getImg3(),
                entity.getImg4(),
                entity.getImg5()
        );
        return result;
    }

    public List<Property> entityToPropertyList(List<RealEstate> entities)
    {
        List<Property> result = new ArrayList<Property>(entities.size());
        for(RealEstate realEstate : entities) {
            result.add(entityToProperty(realEstate));
        }
        return result;
    }
}
