package com.example.example18.repository;

import com.example.example18.model.Contact;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaContactRepository extends CrudRepository<Contact,Integer> {

    List<Contact> findByStatus(String status);

}
