package ru.netology.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.manager.ProductManager;
import ru.netology.products.Book;
import ru.netology.products.Product;
import ru.netology.products.Smartphone;
import ru.netology.repositories.ProductRepository;

public class ProductTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product book1 = new Book(1, "Book1", 1000, "aaaaaBom", "FFFFF");
    Product book2 = new Book(5, "Book5", 1800, "ababa", "FFFFFbom");
    Product book3 = new Book(77, "Book77bom", 500, "ssaass", "SSSSS");
    Product book4 = new Book(36, "Book36", 400, "bbbaaa", "QQQQQbom");
    Product book5 = new Book(44, "Book44", 100, "fffffbom", "WWWWW");
    Product smartphone1 = new Smartphone(22, "Smart22bom", 10_000, "aaaaa", "AAAAA");
    Product smartphone2 = new Smartphone(45, "Smart45", 15_000, "bbbbb", "AAAAAbom");
    Product smartphone3 = new Smartphone(2, "Smart2", 12_000, "aaabbb", "XXXXXBOM");
    Product smartphone4 = new Smartphone(96, "Smart96", 12_000, "ssssss", "VVVVV");
    Product smartphone5 = new Smartphone(100, "Smart100b om", 18_000, "ssssaabom", "VVVVV");

    @Test
    public void testManagerRepoAddingProducts() {
        manager.add(book1);
        manager.add(smartphone1);

        Product[] expected = {book1, smartphone1};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testRepoRemoveById() {
        manager.add(book1); //id 1
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);
        manager.add(book3); //id 77
        manager.add(smartphone3);
        manager.add(book4);
        manager.add(smartphone4); //id 96
        manager.add(book5);
        manager.add(smartphone5);

        repo.removeById(96);
        repo.removeById(77);
        repo.removeById(1);

        Product[] expected = {smartphone1, book2, smartphone2, smartphone3, book4, book5, smartphone5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testManagerSearchFewMatches() {
        manager.add(book1); //id 1
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);
        manager.add(book3); //id 77
        manager.add(smartphone3);
        manager.add(book4);
        manager.add(smartphone4); //id 96
        manager.add(book5);
        manager.add(smartphone5);

        Product[] expected = {smartphone1, book2, smartphone2, book3, book4};
        Product[] actual = manager.searchBy("bom");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testManagerSearchNoMatches() {
        manager.add(book1); //id 1
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);
        manager.add(book3); //id 77
        manager.add(smartphone3);
        manager.add(book4);
        manager.add(smartphone4); //id 96
        manager.add(book5);
        manager.add(smartphone5);

        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("haha");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void unitTestHasMatchesProduct() {
        Product product = new Product(1, "qwerty", 1_000);

        boolean expected = true;
        boolean actual = product.matches("qwe");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestHasMatchesBook() {
        Product product = new Book(1, "book1", 100, "My book", "I am");

        boolean expected = true;
        boolean actual = product.matches("am");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestHasMatchesSmartphone() {
        Product product = new Smartphone(1, "smart1", 100, "Smart", "Maker");

        boolean expected = true;
        boolean actual = product.matches("ake");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestHasNoMatchesProduct() {
        Product product = new Product(1, "qwerty", 1_000);

        boolean expected = false;
        boolean actual = product.matches("zzz");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestHasNoMatchesBook() {
        Product product = new Book(1, "book1", 100, "My book", "I am");

        boolean expected = false;
        boolean actual = product.matches("My");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unitTestHasNoMatchesSmartphone() {
        Product product = new Smartphone(1, "smart1", 100, "Smart", "Maker");

        boolean expected = false;
        boolean actual = product.matches("Smart");

        Assertions.assertEquals(expected, actual);
    }
}