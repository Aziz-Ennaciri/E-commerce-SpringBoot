package com.ecommerce.aze_ecom.service.Impl;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.beans.Product;
import com.ecommerce.aze_ecom.dao.CategoryDao;
import com.ecommerce.aze_ecom.dao.ProductDAO;
import com.ecommerce.aze_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.aze_ecom.mappers.ProductMapper;
import com.ecommerce.aze_ecom.playload.ProductDTO;
import com.ecommerce.aze_ecom.playload.ProductResponse;
import com.ecommerce.aze_ecom.service.Interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {
        Category category = categoryDao.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("Category","categoryId",categoryId));
        product.setImage("default image");
        product.setCategory(category);
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.010)*product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productDAO.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = productDAO.findAll();
        List<ProductDTO> productDTOS = products.stream().map(productMapper::toDto).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId) {
        Category category = categoryDao.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Product> products = productDAO.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productDTOS = products.stream().map(productMapper::toDto).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword) {
        List<Product> products = productDAO.findByProductNameLikeIgnoreCase('%'+keyword+'%');
        List<ProductDTO> productDTOS = products.stream().map(productMapper::toDto).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }
}
