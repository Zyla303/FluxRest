package com.example.demo;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public DatabaseSeeder(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.count()
                .filter(count -> count == 0)
                .flatMapMany(count -> {
                    Book[] books = {
                            new Book("Book 1", "Author 1", 2021, "ISBN-1"),
                            new Book("Book 2", "Author 2", 2022, "ISBN-2"),
                            new Book("Book 3", "Author 3", 2023, "ISBN-3")
                    };
                    return bookRepository.saveAll(Flux.just(books));
                })
                .then(Mono.fromRunnable(() -> System.out.println("Sample data has been added to the database.")))
                .subscribe();
    }
}
