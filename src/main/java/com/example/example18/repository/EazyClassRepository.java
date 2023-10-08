package com.example.example18.repository;

import com.example.example18.model.EazyClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EazyClassRepository extends CrudRepository<EazyClass,Integer> {
}
