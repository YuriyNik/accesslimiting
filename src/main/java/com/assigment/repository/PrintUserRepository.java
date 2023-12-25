package com.assigment.repository;

import com.assigment.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
@Component
public  class PrintUserRepository implements CrudRepository<User, String> {

    public <S extends User> S save(S entity) {
        System.out.println("Priniting DB save User="+entity);
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    public Optional<User> findById(String s) {
        Optional<User> user = Optional.of(new User("hello","mock", LocalDateTime.now()));
        System.out.println("find and return some dummy empty user");
        return user;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
