package com.assigment.repository;

import com.assigment.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryInt extends CrudRepository<User, String> {
}
