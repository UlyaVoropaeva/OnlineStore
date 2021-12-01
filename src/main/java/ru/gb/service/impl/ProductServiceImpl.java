package ru.gb.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.entity.Product;
import ru.gb.repository.ProductRepository;
import ru.gb.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public abstract class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }

}
