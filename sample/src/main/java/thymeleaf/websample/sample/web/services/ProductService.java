package thymeleaf.websample.sample.web.services;

import java.util.List;

import thymeleaf.websample.sample.web.domain.Product;

public interface ProductService {
    Product getProduct(Integer id);
    List<Product> listProducts();
}
