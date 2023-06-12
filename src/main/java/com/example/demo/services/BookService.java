package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.models.Order;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private List<Book> cart;
    private List<Order> orders;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.cart = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public List<Book> getCart() {
        return cart;
    }

    public void setCart(List<Book> cart) {
        this.cart = cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Mono<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public Flux<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Flux<Book> searchBooksByKeyword(String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }

    public Flux<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Flux<Book> getBooksByPublicationYear(int year) {
        return bookRepository.findByPublicationYear(year);
    }

    public void addToCart(String bookId) {
        getBookById(bookId)
                .doOnNext(book -> {
                    cart.add(book);
                    System.out.println("Book added to cart: " + book.getTitle());
                })
                .subscribe();
    }

    public void removeFromCart(String bookId) {
        getBookById(bookId)
                .doOnNext(book -> {
                    cart.remove(book);
                    System.out.println("Book removed from cart: " + book.getTitle());
                })
                .subscribe();
    }

    public List<Book> getCartContents() {
        return cart;
    }

    public void placeOrder(String orderId) {
        if (!cart.isEmpty()) {
            Order order = new Order(orderId, new ArrayList<>(cart));
            orders.add(order);
            cart.clear();
            System.out.println("Order placed. Total books: " + order.getBooks().size());
        } else {
            System.out.println("Cart is empty. Cannot place order.");
        }
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
}
