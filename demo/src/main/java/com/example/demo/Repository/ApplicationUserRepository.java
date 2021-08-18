package com.example.demo.Repository;

import com.example.demo.entity.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Integer> {
    public ApplicationUser findByUsername(String username);


}
