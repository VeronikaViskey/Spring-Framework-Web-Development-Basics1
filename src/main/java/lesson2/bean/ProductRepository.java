package lesson2.bean;

import org.springframework.stereotype.Component;
import lesson2.pojo.Product;

import java.util.Comparator;
import java.util.List;

@Component
public class ProductRepository implements Shop {
    private List<Product> products;

    public ProductRepository() {
        this.products = List.of(
                new Product(1, "boots", 40.0),
                new Product(2, "bag", 25.0),
                new Product(3, "jaсket", 50.0),
                new Product(4, "jeans", 30.0),
                new Product(5, "t-shirt", 15.0));
    }

    @Override
    public Product getProductById(int id){
        return this.products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        int id = this.products
                .stream()
                .max(Comparator.comparingInt(Product::getId))
                .orElse(new Product(1, null, 0)).getId();
        product.setId(id);
        this.products.add(product);
    }

    @Override
    public void removeProduct(int id) {
        this.products.removeIf(product -> product.getId() == id);
    }

    @Override
    public void showAllProduct(){
        this.products.forEach(product -> System.out.println(product.toString()));
    }


    @Override
    public String toString() {
        return "ProductRepository{" +
                "products=" + products +
                '}';
    }
}
