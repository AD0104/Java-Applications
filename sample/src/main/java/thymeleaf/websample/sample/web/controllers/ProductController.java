package thymeleaf.websample.sample.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import thymeleaf.websample.sample.web.services.ProductServiceImpl;

@Controller
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @RequestMapping("/product/{id}")
    public String getProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));

        return "product";
    }
}
