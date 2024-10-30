package com.itembox.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itembox.business.mapper.ItemInfoMapper;
import com.itembox.business.mapper.LocationInfoMapper;
import com.itembox.dao.LocationInfoJpaRepository;
import com.itembox.dto.LocationInfoDto;
import com.itembox.dto.forms.SignupLocationDto;
import com.itembox.entities.ItemInfo;
import com.itembox.entities.LocationInfo;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;


@Controller
@RequestMapping(value = "/sucursal")
public class LocationController {

    @Autowired
    protected LocationInfoJpaRepository locationInfoJpaRepository;
    
    @Autowired
    protected LocationInfoMapper locationInfoMapper;

    @Autowired
    protected ItemInfoMapper itemInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @RequestMapping(value = "/listado", method = RequestMethod.GET)
    public String getAllSucursales(Model model) {
        String threadName = Thread.currentThread().getName();
        logger.info("[LocationController.getAllSucursales] Ini Call. Thread: {}, ProcessDate: {}", threadName, LocalDate.now());
        ArrayList<LocationInfoDto> locations = new ArrayList<>();
        locationInfoJpaRepository.findAll().forEach( i -> locations.add( locationInfoMapper.toDto(i) ) );
        model.addAttribute("locations", locations);
        logger.info("[LocationController.getAllSucursales] Fin Call. Thread: {}, ProcessDate: {}", threadName, LocalDate.now());
        return "location_menu_list";
    }

    @RequestMapping(value = "/listado/{id}", method = RequestMethod.GET)
    public String getLocationById( @PathVariable("id") Integer id, Model model) {
        String threadName = Thread.currentThread().getName();
        logger.info("[LocationController.getLocationById] Ini Call. Thread: {}, ProcesDate: {}", threadName, LocalDate.now() );

        LocationInfoDto location = locationInfoMapper.toDto(locationInfoJpaRepository.findById(id).get());
        logger.info("LocationInfoDto: {}", location);
        model.addAttribute("location", location);

        logger.info("[LocationController.getLocationById] Fin Call. Thread: {}, ProcesDate: {}", threadName, LocalDate.now() );
        return "location_information";
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.GET)
    public String getSignUpSucursalTemplate(Model model) {
        String threadName = Thread.currentThread().getName();
        logger.info("[LocationController.getSignUpSucursalTemplate] Ini Call. Thread: {}, ProcessDate: {}", threadName, LocalDate.now());
        SignupLocationDto location = new SignupLocationDto();
        model.addAttribute("location", location);
        logger.info("[LocationController.getSignUpSucursalTemplate] Fin Call. Thread: {}, ProcessDate: {}", threadName, LocalDate.now());
        return "location_signup"; 
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public String postSignUpSucursalTemplate(
            Model model,
            @Validated @ModelAttribute SignupLocationDto location,
            BindingResult result
            ) {
        String threadName = Thread.currentThread().getName();
        logger.info("[LocationController.postSignUpSucursalTemplate] Ini Call. Thread: {}, ProcessDate: {}", threadName, LocalDate.now());
        if (result.hasErrors()) {
            logger.info("[LocationController.postSignUpSucursalTemplate] Fin Call with validation errors. Thread: {}, ProcessDate: {}", threadName, LocalDate.now());
            return "location_signup";
        }
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setLocationId(location.getLocationId() );
        locationInfo.setLocationDescription( location.getLocationDescription() );
        if ( location.getItems() != null ) {
            List<ItemInfo> items = new ArrayList<>();
            location.getItems().forEach( e -> items.add( itemInfoMapper.toEntity(e) ) );
        } else
            locationInfo.setItems(null);
        locationInfoJpaRepository.save( locationInfo );
        logger.info("[LocationController.postSignUpSucursalTemplate] Fin Call. Thread: {}, ProcessDate: {}", threadName, LocalDate.now());
        return "redirect:/sucursal/listado";
    }
}
