package ru.gb.ropositor;

import org.springframework.data.repository.CrudRepository;
import ru.gb.entity.Role;

public interface RoleRepositoryAuth extends CrudRepository<Role, Long> {

}
