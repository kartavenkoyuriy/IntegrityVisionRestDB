package com.integrityVision.simpleRestDB.repository;

import com.integrityVision.simpleRestDB.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    String FIND_BY_ADDRESS_QUERY = "SELECT u FROM User u WHERE u.lastLogOn = :date";
    String FIND_BY_NAME = "SELECT u FROM User u WHERE u.firstName LIKE :name";

    @Query(FIND_BY_ADDRESS_QUERY)
    List<User> getUsersByDate(@Param("date") Date date);

    @Query(FIND_BY_NAME)
    List<User> getUsersByName(@Param("name") String name);
}
