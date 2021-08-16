package com.example.demo.entity;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Integer> {
    public ApplicationUser findByUsername(String username);

}
