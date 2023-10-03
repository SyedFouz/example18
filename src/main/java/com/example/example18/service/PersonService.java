package com.example.example18.service;

import com.example.example18.constants.EazySchoolConstants;
import com.example.example18.model.Person;
import com.example.example18.model.Roles;
import com.example.example18.repository.PersonRepository;
import com.example.example18.repository.RolesRepository;
import com.example.example18.security.EazySchoolPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    EazySchoolPasswordEncoder eazySchoolPasswordEncoder;

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(eazySchoolPasswordEncoder.encode(person.getPwd()));
        Person person1 = personRepository.save(person);
        return person1.getPersonId() > 0;
    }
}
