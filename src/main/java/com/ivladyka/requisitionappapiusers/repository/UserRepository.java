package com.ivladyka.requisitionappapiusers.repository;

import com.ivladyka.requisitionappapiusers.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByPhoneNumber(String number);
}
