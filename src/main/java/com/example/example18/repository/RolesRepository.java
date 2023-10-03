package com.example.example18.repository;

import com.example.example18.model.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<Roles,Integer> {
  Roles getByRoleName(String roleName);
}
