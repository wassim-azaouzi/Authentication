package com.codingdojo.authentication.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.authentication.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
