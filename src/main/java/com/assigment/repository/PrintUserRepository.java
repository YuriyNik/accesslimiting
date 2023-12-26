package com.assigment.repository;

import com.assigment.controller.QuotaController;
import com.assigment.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;
@Component
public  class PrintUserRepository implements CrudRepository<User, String> {
    private static final Logger log = LoggerFactory.getLogger(QuotaController.class);

    public <S extends User> S save(S entity) {
        log.info("Priniting DB save User="+entity);
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    public Optional<User> findById(String s) {
        Optional<User> user = Optional.of(new User("hello","mock", LocalDateTime.now()));
        log.info("find and return some dummy empty user id="+s+";"+user);
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
