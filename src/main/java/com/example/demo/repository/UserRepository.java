package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "select * from user u where u.username = :username AND u.password = :password", nativeQuery = true)
    Optional<User> authenticate(@Param("username") String username, @Param("password") String password);

}
