package com.example.example18.service;

import com.example.example18.model.Holiday;
import com.example.example18.repository.HolidayRepository;
import com.example.example18.repository.JpaHolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HolidayService {
  /*  @Autowired
    HolidayRepository holidayRepository;*/
    @Autowired
    JpaHolidayRepository holidayRepository;

    public List<Holiday> displayHolidays() {

        return StreamSupport.stream(holidayRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
}
