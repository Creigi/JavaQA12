package ru.netology.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.exceptions.NotFoundException;
import ru.netology.manager.ProductManager;
import ru.netology.products.Book;
import ru.netology.products.Product;
import ru.netology.products.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {

    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product book1 = new Book(1, "Book1", 1000, "aaaaaBom", "FFFFF");
    Product book2 = new Book(5, "Book5", 1800, "ababa", "FFFFFbom");
    Product book3 = new Book(77, "Book77bom", 500, "ssaass", "SSSSS");

    @Test
    public void removeByIdTest() {
        manager.add(book1); // id = 1
        manager.add(book2); // id = 5
        manager.add(book3); // id = 77

        manager.removeById(5);

        Product[] expected = {book1, book3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void exceptionTest() {
        manager.add(book1); // id = 1
        manager.add(book2); // id = 5
        manager.add(book3); // id = 77

        Assertions.assertThrows(NotFoundException.class, () -> {
            manager.removeById(20);
        });
    }
}