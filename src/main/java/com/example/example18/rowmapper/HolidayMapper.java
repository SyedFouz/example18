package com.example.example18.rowmapper;

import com.example.example18.model.Holiday;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HolidayMapper implements RowMapper<Holiday> {
    @Override
    public Holiday mapRow(ResultSet rs, int rowNum) throws SQLException {
        Holiday holiday = new Holiday();
        holiday.setDay(rs.getString("DAY"));
        holiday.setReason(rs.getString("REASON"));
        holiday.setType(Holiday.Type.valueOf(rs.getString("TYPE")));
        return holiday;
    }
}
