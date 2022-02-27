package ru.gb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.entity.Product;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

}