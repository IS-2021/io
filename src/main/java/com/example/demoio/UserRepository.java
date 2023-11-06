package com.example.demoio;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);

    User save(User user);

}
