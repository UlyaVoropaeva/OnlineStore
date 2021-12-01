package ru.gb.application.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.gb.controller.ProductController;
import ru.gb.entity.Product;

import ru.gb.repository.ProductRepository;
import ru.gb.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


class ProductControllerTest {

    private Product product = new Product(1, "ProductTest", "UnitTest", BigDecimal.valueOf(10));

    @Test
    void findAllProductControllerUnitTest() {
        ProductServiceImpl productService = Mockito.mock(ProductServiceImpl.class);
        List<Product> productsList = Arrays.asList(product);
        Mockito.when(productService.findAll()).thenReturn(productsList);
    }

    @Test
    void findByIdProductControllerUnitTest() {
        ProductServiceImpl productService = Mockito.mock(ProductServiceImpl.class);
        Product result = new Product();
        Mockito.when(productService.findById(product.getId())).thenReturn(java.util.Optional.of(result));
    }

    @Test
    void productControllerUnitTest() {
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

        Product result = new Product();
        Mockito.when(productRepository.save(product)).thenReturn(result);

        ProductController productController = new ProductController(productRepository);
        Assertions.assertThat(productController.delete(product)).isNotEmpty();
    }
}