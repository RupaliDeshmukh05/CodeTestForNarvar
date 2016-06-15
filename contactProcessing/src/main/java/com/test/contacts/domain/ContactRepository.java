package com.test.contacts.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

	Page<Contact> findAll(Pageable pageable);

	Page<Contact> findByProfession(Pageable pageable, String profession);
	
}
