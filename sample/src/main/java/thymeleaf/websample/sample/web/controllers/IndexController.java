package thymeleaf.websample.sample.web.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import thymeleaf.websample.sample.web.services.ProductServiceImpl;

@Controller
public class IndexController {
    public static final Logger logger = LogManager.getLogger();
    @Autowired
    protected ProductServiceImpl productService;
    @RequestMapping("/")
    public String indexController(Model model) {
        logger.info("-- Products: {}", productService.listProducts() );
        model.addAttribute("products", productService.listProducts());

        return "index";
    }
}
