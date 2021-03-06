package lesson2.bean;

import lesson2.pojo.Product;

public interface Shop {

    void showAllProduct();

    Product getProductById(int id);

    void addProduct(Product product);

    void removeProduct(int id);
}
