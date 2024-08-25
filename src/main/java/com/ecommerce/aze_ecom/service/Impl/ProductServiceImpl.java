package com.ecommerce.aze_ecom.service.Impl;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.beans.Product;
import com.ecommerce.aze_ecom.repositories.CategoryRepository;
import com.ecommerce.aze_ecom.repositories.ProductRepository;
import com.ecommerce.aze_ecom.exceptions.APIException;
import com.ecommerce.aze_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.aze_ecom.mappers.ProductMapper;
import com.ecommerce.aze_ecom.playload.ProductDTO;
import com.ecommerce.aze_ecom.playload.ProductResponse;
import com.ecommerce.aze_ecom.service.Interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private FileServiceImpl fileService;

    @Value("${project.image}")
    private String path;

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("Category","categoryId",categoryId));

        boolean isProductNotPresent = true;
        List<Product> products = category.getProducts();

        for (Product value : products) {
            if (value.getProductName().equals(productDTO.getProductName())) {
                isProductNotPresent = false;
                break;
            }
        }
        if (isProductNotPresent) {
            Product product = productMapper.toEntity(productDTO);
            product.setImage("default image");
            product.setCategory(category);
            double specialPrice = product.getPrice() - ((product.getDiscount() * 0.010) * product.getPrice());
            product.setSpecialPrice(specialPrice);
            Product savedProduct = productRepository.save(product);
            return productMapper.toDto(savedProduct);
        }else {
            throw new APIException("The Product it's already exist");
        }
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageableDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> productPage = productRepository.findAll(pageableDetails);

        List<Product> products = productPage.getContent();
        List<ProductDTO> productDTOS = products.stream().map(productMapper::toDto).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setPageSize(productPage.getSize());
        productResponse.setTotalElements(productPage.getTotalElements());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setLastPage(productPage.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("Category","categoryId",categoryId));

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageableDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> productPage = productRepository.findByCategoryOrderByPriceAsc(category,pageableDetails);

        List<Product> products = productPage.getContent();

        if (products.isEmpty()){
            throw new APIException(category.getCategoryName()+" "+" Category doesn't have any product");
        }

        List<ProductDTO> productDTOS = products.stream().map(productMapper::toDto).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setPageSize(productPage.getSize());
        productResponse.setTotalElements(productPage.getTotalElements());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setLastPage(productPage.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageableDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> productPage = productRepository.findByProductNameLikeIgnoreCase('%'+keyword+'%',pageableDetails);

        List<Product> products = productPage.getContent();

        if (products.isEmpty()){
            throw new APIException("Products not found with name of "+" "+keyword);
        }


        List<ProductDTO> productDTOS = products.stream().map(productMapper::toDto).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setPageSize(productPage.getSize());
        productResponse.setTotalElements(productPage.getTotalElements());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setLastPage(productPage.isLast());
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product existProduct = productRepository.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("Product","productId",productId));
        Product product = productMapper.toEntity(productDTO);
        existProduct.setProductName(product.getProductName());
        existProduct.setDescription(product.getDescription());
        existProduct.setQuantity(product.getQuantity());
        existProduct.setDiscount(product.getDiscount());
        existProduct.setPrice(product.getPrice());
        existProduct.setSpecialPrice(product.getSpecialPrice());
        Product savedProduct = productRepository.save(existProduct);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product existProduct = productRepository.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("Product","productId",productId));
        ProductDTO productDTO = productMapper.toDto(existProduct);
        productRepository.delete(existProduct);
        return productDTO;
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        // Get the product from DB
        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        // Upload image to server
        // Get the file name of uploaded image
        String fileName = fileService.uploadImage(path, image);

        // Updating the new file name to the product
        productFromDb.setImage(fileName);

        // Save updated product
        Product updatedProduct = productRepository.save(productFromDb);

        // return DTO after mapping product to DTO
        return productMapper.toDto(updatedProduct);
    }
}
