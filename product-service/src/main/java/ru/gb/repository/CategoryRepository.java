package ru.gb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.entity.Category;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryRepository extends CrudRepository<Category, Long> {}