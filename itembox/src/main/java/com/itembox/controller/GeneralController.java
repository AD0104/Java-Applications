package com.itembox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ch.qos.logback.core.model.Model;

@Controller
public class GeneralController {
    // ToDo: Map with new entity to get all locations and display on the main menu
    @GetMapping("/menu")
    public String getGeneralMenu(Model model) {
        return "index";
    }

    // ToDo: This window must show a table with all the location's items registered.
    @GetMapping("/location/{locationId}/items")
    public String getLocationItemsById(@PathVariable(name = "locationId") Long locationid) {
        return "index";
    }

}
