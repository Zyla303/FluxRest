package com.example.demo.repositories;

import com.example.demo.models.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    Flux<Book> findByAuthor(String author);
    Flux<Book> findByTitleContaining(String keyword);
    Flux<Book> findByPublicationYear(int year);
    Flux<Book> findByTitle(String title);
}
