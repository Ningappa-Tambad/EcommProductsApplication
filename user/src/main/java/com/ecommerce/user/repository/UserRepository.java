package com.ecommerce.user.repository;

import com.ecommerce.user.models.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Custom query methods can be defined here if needed
    // For example, findByFirstname, findByLastname, etc.


    @Override
    Optional<User> findById(String aLong);


}
