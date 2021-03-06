package lesson2.bean;

import org.springframework.stereotype.Component;
import lesson2.pojo.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart  implements Shop {

    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    @Override
    public void showAllProduct() {
        if (this.products.size() != 0)
            this.products.forEach(product -> System.out.println(product.toString()));
        else System.out.println("Nothing");
    }

    @Override
    public Product getProductById(int id) {
        return this.products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public void removeProduct(int id) {
        this.products.removeIf(product -> product.getId() == id);
    }
}
