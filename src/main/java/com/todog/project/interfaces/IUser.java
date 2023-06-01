package com.todog.project.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todog.project.models.User;

@Repository
public interface IUser extends CrudRepository<User, Integer> {

}
