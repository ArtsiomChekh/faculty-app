package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
