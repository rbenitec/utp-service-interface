package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    //Optional<User> findByUsername(String username);

    @Query(value = "select * from user u where u.username = ?1", nativeQuery = true)
    Optional<User> getName(String username);
}
