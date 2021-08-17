package com.example.demo.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositry extends CrudRepository<Post,Integer> {
    public ApplicationUser findByUsername(String username);

}
