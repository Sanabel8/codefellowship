package com.example.demo.Repository;

import com.example.demo.entity.ApplicationUser;
import com.example.demo.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositry extends CrudRepository<Post,Integer> {
    public ApplicationUser findByUsername(String username);

}
