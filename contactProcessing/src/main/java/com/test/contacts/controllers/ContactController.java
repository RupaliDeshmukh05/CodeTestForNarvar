package com.test.contacts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.contacts.domain.Contact;
import com.test.contacts.services.ContactService;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {

	@Autowired
  	private ContactService contactService;
	
  	@RequestMapping(value = "/", method=RequestMethod.POST, headers="Accept=application/json")
  	public Contact addContact(@RequestParam(value = "name", required = false) String name,
            @RequestParam( value = "email", required = false) String email,
            @RequestParam(value = "profession", required = false) String profession) {
  		System.out.println("Add Contact");
  		Contact newContact = new Contact(name, email, profession);
		return contactService.save(newContact);
	}

  	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
  	public String deleteContact(@PathVariable long id) {
  		System.out.println("Delete Contact");
  		return contactService.delete(id);
  	}

  	@RequestMapping(value="{id}", method=RequestMethod.PUT, headers="Accept=application/json")
  	public Contact update(@PathVariable long id, @RequestParam(value = "name", required = false) String name,
            @RequestParam( value = "email", required = false) String email,
            @RequestParam(value = "profession", required = false) String profession) {
  		System.out.println("Update contact");
  		Contact contact = new Contact(name, email, profession);
  		return contactService.update(id, contact); 
  	}
  	
  	@RequestMapping(value = "/list", method=RequestMethod.GET)
  	public List<Contact> listContacts(
  			@RequestParam(value="page", defaultValue = "0") int page, 
  			@RequestParam(value="size", defaultValue = "100") int size, 
  			@RequestParam(value="isAsc", defaultValue = "1") boolean isAsc) {
  		System.out.println("Get the list of 100 contacts");
  		return contactService.findAll(page, size, isAsc);
	}

  	@RequestMapping(value = "/list-unemployee", method=RequestMethod.GET)
  	public List<Contact> searchEmployeeUnemployee(
  			@RequestParam(value="page", defaultValue = "0") int page, 
  			@RequestParam(value="size", defaultValue = "5") int size, 
  			@RequestParam(value="isAsc", defaultValue = "1") boolean isAsc, 
  			@RequestParam(value="profession", defaultValue = "unemployee") String profession) {
  		System.out.println("Searching for last 5 unemployed contacts");
  		return contactService.findByProfession(page, size, isAsc, profession);
	}

}