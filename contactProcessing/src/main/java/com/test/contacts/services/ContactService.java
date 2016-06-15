package com.test.contacts.services;

import java.util.List;

import com.test.contacts.domain.Contact;

public interface ContactService {
	public Contact save(Contact contact);
	public Contact findOne(long id);
	public String  delete(long id);
	public Contact update(long id, Contact contact);
	public List<Contact> findAll(int page, int size, boolean isAsc);
	public List<Contact> findByProfession(int page, int size, boolean isAsc, String profession);
}
