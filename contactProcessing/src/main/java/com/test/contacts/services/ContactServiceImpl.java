package com.test.contacts.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.test.contacts.domain.Contact;
import com.test.contacts.domain.ContactRepository;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact save(Contact contact) {
	    try {
	      contactRepository.save(contact);
	      System.out.println("Contact saved :" + contact.getId());
	    } catch (Exception ex) {
	    	System.out.println("Error while saving contact: "+ contact.getId() +" : "+ ex.toString());
	      return null;
	    }
	    return contact;
	}

	@Override
	public Contact findOne(long id) {
		Contact contact = null;
		try {
	      contact = contactRepository.findOne(id);
	      System.out.println("Find one contact " + contact.getId());
	    } catch (Exception ex) {
	    	System.out.println("Error while fetching a contact: "+ contact.getId() +" : "+ ex.toString());
	    }
	    return contact;
	}

	@Override
	public String delete(long id) {
		try {
	      Contact contact = contactRepository.findOne(id);
	      contactRepository.delete(contact);
	      System.out.println("Deleted contact: " + contact.getId());
	    } catch (Exception ex) {
	    	System.out.println("Error while deleting a contact: "+ id +" : "+ ex.toString());
	      return "Error while deleting a contact: " + ex.toString();
	    }
		return "Contact successfully deleted!";
	}

	@Override
	public Contact update(long id, Contact contact) {
		Contact updatedContact = null;
		try {
			updatedContact = contactRepository.findOne(id);
		  System.out.println("Contact before updated: " + contact);
	      if (!StringUtils.isNullOrEmpty(contact.getName()))
	    	  updatedContact.setName(contact.getName());
	      if (!StringUtils.isNullOrEmpty(contact.getEmail()))
	    	  updatedContact.setEmail(contact.getEmail());
	      if (!StringUtils.isNullOrEmpty(contact.getProfession())) 
	    	  updatedContact.setProfession(contact.getProfession());
	      
	      System.out.println("Contact before updated: " + updatedContact);
	      contactRepository.save(updatedContact);
	      System.out.println("Updated contact: " + updatedContact.getId());
	    } catch (Exception ex) {
	    	System.out.println("Error while updating the contact : " + contact.getId()+ " : "+ex.toString());	
	    }
	    return updatedContact;
	}

	@Override
	public List<Contact> findAll(int page, int size, boolean isAsc) {
		PageRequest pageRequest = null;
		if (isAsc) { 
			pageRequest = new PageRequest(page, size, new Sort(new Order(Direction.ASC,  "name")));
		} else { 
			pageRequest = new PageRequest(page, size, new Sort(new Order(Direction.DESC, "name")));			
		}
		Page<Contact> contacts = contactRepository.findAll(pageRequest);
		return contacts.getContent();
	}

	@Override
	public List<Contact> findByProfession(int page, int size, boolean isAsc, String profession) {
		PageRequest pageRequest = null;
		if (isAsc) { 
			pageRequest = new PageRequest(page, size, new Sort(new Order(Direction.ASC,  "name")));
		} else { 
			pageRequest = new PageRequest(page, size, new Sort(new Order(Direction.DESC, "name")));			
		}
		Page<Contact> contacts = contactRepository.findByProfession(pageRequest, profession);
		return contacts.getContent();
	}
	
}