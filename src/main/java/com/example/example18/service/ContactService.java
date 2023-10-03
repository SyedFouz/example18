package com.example.example18.service;

import com.example.example18.constants.EazySchoolConstants;
import com.example.example18.model.Contact;
import com.example.example18.repository.JpaContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final Logger logger = LoggerFactory.getLogger(ContactService.class);
    //private ContactRepository contactRepository;
    @Autowired
    private JpaContactRepository contactRepository;

    public boolean saveMessageDetails(Contact contact) {

        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if (null != savedContact && savedContact.getContactId() > 0) {
            isSaved = true;
        }
        logger.info(contact.toString());
        return isSaved;
    }

    public List<Contact> findMessagesWithOpenStatus() {
        List<Contact> contacts = contactRepository.findByStatus(EazySchoolConstants.OPEN);
        return contacts;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy, String status) {


        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(status);
        });

        Contact updatedContact = contactRepository.save(contact.get());
//        int returnCode=contactRepository.updateMsgStatus(contactId,updatedBy,status);

        if (updatedContact.getUpdatedAt() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
