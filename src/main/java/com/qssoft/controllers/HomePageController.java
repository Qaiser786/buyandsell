package com.qssoft.controllers;

import com.qssoft.dto.Property;
import com.qssoft.security.UserAccessHelper;
import com.qssoft.services.PropertyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController
{
    @Autowired
    private PropertyDetailsService propertyDetailsService;

    @RequestMapping(method=RequestMethod.GET)
    public String index(Model model)
    {
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

        return "index";
    }

    @RequestMapping(value="search", method = RequestMethod.GET)

    public String search(@RequestParam("searchQuery") final String searchQuery, @RequestParam("searchCondition") final String searchCondition, Model model) {

        List<Property> propertiesList = null;

        if(searchCondition.equals("price")) {

            propertiesList = propertyDetailsService.searchByPrice(new BigDecimal(searchQuery));

        } else if(searchCondition.equals("city")) {

            propertiesList = propertyDetailsService.searchByCity(searchQuery);
        }

        model.addAttribute("propertiesList", propertiesList);

        return "index";
    }
}
