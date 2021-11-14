package ru.gb.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Category;
import ru.gb.entity.Product;
import ru.gb.repository.CategoryRepository;
import ru.gb.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Controller
@RequestMapping(value = "/products")

public class ProductController {

    private final ProductRepository productService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository=categoryRepository;
    }


    @GetMapping("/products")
    public String findAll(@ModelAttribute("productList") List<Product> product) {

        product = new ArrayList<>();
        productService.findAll().forEach(product::add);

        return "products/products";

    }

    @GetMapping("/products-add/{id}")
    public String updateProduct (@PathVariable long id, @NotNull Model model) {
        model.addAttribute("product", productService.findById(id));

        return "/products/products-add";
    }

   @PostMapping("/products-add")
    public String update(@RequestParam Long id,
                         @RequestParam(value = "product", required = false) boolean edit,
                         @RequestParam(value = "categories", required = false) boolean editCategories) {
        productService.update(id);
        return "redirect: products/products";
    }

    @PostMapping
    public String save(@RequestParam Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "products/products-add";
        }
        productService.save(product);
        return "redirect:products/products";
    }

    @GetMapping("/products-add")
    public String saveForm(Model model) {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories ::add);
        model.addAttribute("categories", new Product());
        model.addAttribute("product", new Product());

        return "products/products-add";
    }

    @GetMapping("products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

}