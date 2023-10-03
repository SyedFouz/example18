package com.example.example18.repository;

import com.example.example18.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


public class HolidayRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public HolidayRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> getHolidays() {

        String sql = "SELECT * FROM holidays";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Holiday.class));


    }

    /* return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, holidayType);
            }
        }, new HolidayMapper());*/
}
