package ru.gb;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


public interface ProductControllerInterface {

    @GetMapping("/products")
    String findAll();

    @GetMapping("/products-add/{id}")
    String updateProduct();


    @PostMapping("/products-add")
    String save();


    @GetMapping("/products-add")
    String saveForm();

    @DeleteMapping("/delete/{id}")
    String delete();
}
