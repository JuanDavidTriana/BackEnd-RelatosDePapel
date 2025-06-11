package com.relatosdepapel.books.controller;

import com.relatosdepapel.books.model.Book;
import com.relatosdepapel.books.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @GetMapping
    public ResponseEntity<Page<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Boolean visibility,
            @RequestParam(required = false) String publicationDate,
            @PageableDefault(size = 10) Pageable pageable) {
        Specification<Book> spec = Specification.where(null);
        if (title != null) spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        if (author != null) spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
        if (category != null) spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("category")), "%" + category.toLowerCase() + "%"));
        if (isbn != null) spec = spec.and((root, query, cb) -> cb.equal(root.get("isbn"), isbn));
        if (rating != null) spec = spec.and((root, query, cb) -> cb.equal(root.get("rating"), rating));
        if (visibility != null) spec = spec.and((root, query, cb) -> cb.equal(root.get("visibility"), visibility));
        if (publicationDate != null) spec = spec.and((root, query, cb) -> cb.equal(root.get("publicationDate"), LocalDate.parse(publicationDate)));
        return ResponseEntity.ok(bookRepository.findAll(spec, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        if (!bookRepository.existsById(id)) return ResponseEntity.notFound().build();
        book.setId(id);
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> partialUpdateBook(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "title" -> existingBook.setTitle((String) value);
                            case "author" -> existingBook.setAuthor((String) value);
                            case "publicationDate" -> existingBook.setPublicationDate(LocalDate.parse((String) value));
                            case "category" -> existingBook.setCategory((String) value);
                            case "isbn" -> existingBook.setIsbn((String) value);
                            case "rating" -> existingBook.setRating((Integer) value);
                            case "visibility" -> existingBook.setVisibility((Boolean) value);
                        }
                    });
                    return ResponseEntity.ok(bookRepository.save(existingBook));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) return ResponseEntity.notFound().build();
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 