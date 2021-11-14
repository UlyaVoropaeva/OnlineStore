package ru.gb.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.entity.Product;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {


    @Override
    Iterable<Product> findAll();

    @Query("SELECT p FROM Product  as p WHERE p.id = :id")
    Product getById(@Param("id") long id);

    @Modifying
    @Query("UPDATE Product as p SET p.name='$name',p.description='$description' where p.id = :id")
    Product update(long id);
}
