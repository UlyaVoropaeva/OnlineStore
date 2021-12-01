package ru.gb.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.gb.entity.Product;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public interface ProductService extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);
    public List<Product> findAll();

}
