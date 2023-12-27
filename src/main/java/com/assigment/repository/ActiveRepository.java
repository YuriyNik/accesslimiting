package com.assigment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.TimeZone;

@Component
public class ActiveRepository {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrintUserRepository printUserRepository;
//@todo think about autoswither of repositories by timer
    private CrudRepository currentRepository;

    public CrudRepository getRepository() {
        return isDayTime()?userRepository:printUserRepository;
    }

    public static boolean isDayTime() {
        ZoneId utcZone = ZoneId.of("UTC");
        TimeZone.setDefault(TimeZone.getTimeZone(utcZone));
        LocalTime currentTime = LocalTime.now(utcZone);
        LocalTime startOfDay = LocalTime.of(9, 0);
        LocalTime endOfDay = LocalTime.of(17, 0);
        return currentTime.isAfter(startOfDay) && currentTime.isBefore(endOfDay);
    }

}
