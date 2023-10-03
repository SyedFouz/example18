package com.example.example18.controllers;

import com.example.example18.constants.EazySchoolConstants;
import com.example.example18.model.Contact;
import com.example.example18.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class ContactController {
//    private final Logger logger = LoggerFactory.getLogger(ContactController.class);

    ContactService contactService;

    @RequestMapping("/contact")
    public ModelAndView getContact(ModelAndView modelAndView) {
        modelAndView.addObject("contact",new Contact());
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    //    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
//    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
//                                    @RequestParam String email, @RequestParam String subject,@RequestParam String message) {
//        logger.info("Name : " + name);
//        logger.info("Mobile Number : " + mobileNum);
//        logger.info("Email Address : " + email);
//        logger.info("Subject : " + subject);
//        logger.info("Message : " + message);
//        return new ModelAndView("redirect:/contact");
//    }
    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public ModelAndView saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if (errors.hasErrors()){
            log.error("Contact form validation failed due to :"+ errors);
            return new ModelAndView("contact");
        }
        contactService.saveMessageDetails(contact);
        log.info(contact.toString());
        return new ModelAndView("redirect:/contact");
    }
    @RequestMapping(value = "/displayMessages",method = RequestMethod.GET)
    public ModelAndView displayMessages(Model model){
        List<Contact> contacts= contactService.findMessagesWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("messages.html");
        modelAndView.addObject("contactMsgs",contacts);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg",method = RequestMethod.GET)
    public String closeMsg(@RequestParam int id, Authentication authentication){
        contactService.updateMsgStatus(id,authentication.getName(), EazySchoolConstants.ClOSE);
        return "redirect:/displayMessages";
    }


}
