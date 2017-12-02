package com.qssoft.controllers;

import com.qssoft.dto.Property;
import com.qssoft.security.UserAccessHelper;
import com.qssoft.services.PropertyDetailsService;
import com.qssoft.services.PropertyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {
    @Autowired
    private PropertyDetailsService propertyDetailsService;
    @Autowired
    private PropertyTypeService propertyTypeService;

    @RequestMapping(method=RequestMethod.GET)
    public String index(Model model) {
        List<Property> propertiesList = null;

        if(UserAccessHelper.isAdmin()) {
            propertiesList = propertyDetailsService.getNonDeleteProperties();
        } else if (UserAccessHelper.isOwner()) {
            int userId = UserAccessHelper.getUserId();
            propertiesList = propertyDetailsService.getPropertiesByOwnerId(userId);
        } else if (UserAccessHelper.isBuyer()) {
            propertiesList = propertyDetailsService.getApprovedProperties();
        } else if (UserAccessHelper.isAnonymous()) {
            propertiesList = propertyDetailsService.getApprovedProperties();
        }
        model.addAttribute("propertiesList", propertiesList);
        model.addAttribute("propertyTypes", propertyTypeService.getAllPropertyTypes());
        model.addAttribute("popularCities", propertyDetailsService.getPopularCities());

        return "index";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "city", required = true) String city,
                         @RequestParam(value = "address", required = false) String address,
                         @RequestParam(value = "minPrice", required = false) Double minPrice,
                         @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                         @RequestParam(value = "propertyTypeId", required = false) Integer propertyTypeId,
                         Model model) {
        if ( address != null && address.isEmpty() ) { address = null; }
        if ( city == null || city.isEmpty() ) {
            model.addAttribute("error", "Please enter a City.");
        } else {
            List<Property> propertiesList = new LinkedList<>();
            try {
                if ( propertyTypeId != null && propertyTypeId < 0 ) { propertyTypeId = null; }
                propertiesList = propertyDetailsService.searchByFilters(city, address, minPrice, maxPrice, propertyTypeId);
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
            }
            model.addAttribute("propertiesList", propertiesList);
        }

        model.addAttribute("propertyTypes", propertyTypeService.getAllPropertyTypes());
        model.addAttribute("popularCities", propertyDetailsService.getPopularCities());

        return "index";
    }


}
