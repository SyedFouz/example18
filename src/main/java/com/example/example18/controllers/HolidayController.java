package com.example.example18.controllers;

import com.example.example18.model.Holiday;
import com.example.example18.repository.JpaHolidayRepository;
import com.example.example18.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HolidayController {
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private JpaHolidayRepository jpaHolidayRepository;


    @RequestMapping(value = {"/holidays/{display}", "/holidays"}, method = RequestMethod.GET)
    public String getHolidays(Model model, @PathVariable(name = "display", required = false) String holidayType) {
        if (holidayType.equalsIgnoreCase("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (holidayType.equalsIgnoreCase("federal")) {
            model.addAttribute("federal", true);
        } else if (holidayType.equalsIgnoreCase("festival")) {
            model.addAttribute("festival", true);
        }
        Iterable<Holiday> holidays = jpaHolidayRepository.findAll();
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(), false).toList();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            if (type.toString().equals(holidayType) || holidayType.equalsIgnoreCase("all"))
                model.addAttribute(type.toString(), holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holidays.html";
    }
}


   /* @RequestMapping(value = {"/holidays/{display}", "/holidays"}, method = RequestMethod.GET)
    public String getHolidays(Model model, @PathVariable(name = "display", required = false) String holidayType) {

        holidayService.displayHolidays(holidayType);


        model.addAttribute(holidayType,holidayService.displayHolidays(holidayType);)
        return "holiday.html";
    }*/

       /* @RequestMapping(value = {"/holidays/{display}","/holidays"}, method = RequestMethod.GET)
    public String getHolidays(Model model, @PathVariable(name = "display",required = false) String holidayType) {
            if (holidayType.equalsIgnoreCase("all")){
                model.addAttribute("festival",true);
                model.addAttribute("federal",true);
            } else if (holidayType.equalsIgnoreCase("federal")) {
                model.addAttribute("federal",true);
            }else if (holidayType.equalsIgnoreCase("festival")){
                model.addAttribute("festival",true);
            }

            List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ", "Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL),
                new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL)
        );
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            if (type.toString().equals(holidayType)|| holidayType.equalsIgnoreCase("all"))
                model.addAttribute(type.toString(), holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holidays.html";
    }*/
   /* @RequestMapping(value = "/holidays", method = RequestMethod.GET)
    public String getHolidays(Model model, @RequestParam(required = false) boolean festival, @RequestParam(required = false) boolean federal) {
        model.addAttribute("festival",festival);
        model.addAttribute("federal",federal);
        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ", "Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL),
                new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL)
        );
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            //if (type.toString().equals(Holiday.Type.FEDERAL.toString())&& federal || (type.toString().equals(Holiday.Type.FESTIVAL.toString())&& festival))
                model.addAttribute(type.toString(), holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holidays.html";
    }*/

