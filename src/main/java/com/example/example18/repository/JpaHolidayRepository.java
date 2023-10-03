package com.example.example18.repository;

import com.example.example18.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHolidayRepository extends CrudRepository<Holiday,String> {

}
