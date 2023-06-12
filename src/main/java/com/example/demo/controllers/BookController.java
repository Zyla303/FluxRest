package com.example.demo.controllers;

import com.example.demo.models.Book;
import com.example.demo.models.Order;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Flux<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{author}")
    public Flux<Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/title/{title}")
    public Flux<Book> getBooksByTitle(@PathVariable String title) {
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/year/{year}")
    public Flux<Book> getBooksByYear(@PathVariable int year) {
        return bookService.getBooksByPublicationYear(year);
    }

    @PostMapping("/cart/{bookId}")
    public void addToCart(@PathVariable String bookId) {
        bookService.addToCart(bookId);
    }

    @DeleteMapping("/cart/{bookId}")
    public void removeFromCart(@PathVariable String bookId) {
        bookService.removeFromCart(bookId);
    }

    @GetMapping("/cart")
    public List<Book> getCartContents() {
        return bookService.getCartContents();
    }

    @PostMapping("/order/{orderId}")
    public void placeOrder(@PathVariable String orderId) {
        bookService.placeOrder(orderId);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return bookService.getAllOrders();
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable String orderId) {
        return bookService.getOrderById(orderId);
    }
}
