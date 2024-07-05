package com.example.demo.repository;

import com.example.demo.entities.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Rol, Integer> {
}
