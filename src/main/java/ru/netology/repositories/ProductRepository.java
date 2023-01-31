package ru.netology.repositories;

import ru.netology.exceptions.NotFoundException;
import ru.netology.products.Product;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void add(Product product) {
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (id == product.getId()) {
                return product;
            }
        }
        return null;
    }

    public void removeById(int removeId) {
        if (findById(removeId) != null) {
            Product[] tmp = new Product[products.length - 1];
            int index = 0;
            for (Product product : products) {
                if (product.getId() != removeId) {
                    tmp[index] = product;
                    index++;
                }
            }
            products = tmp;
        } else {
            throw new NotFoundException(
                    "Element with id " + removeId + " not found"
            );
        }
    }
}
