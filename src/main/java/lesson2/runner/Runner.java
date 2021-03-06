package lesson2.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lesson2.bean.Shop;
import lesson2.pojo.Product;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private Shop productRepository;

    @Autowired
    private Shop cart;

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean loopFlag = true;
            printInfo();
            while (loopFlag) {
                System.out.println("Choose option : ");
                int command = -1;
                if (scanner.hasNextInt()) {
                    command = scanner.nextInt();
                } else {
                    System.out.println("Not found Int value :"
                            + scanner.next());
                    continue;
                }
                switch (command) {
                    case 1:
                        productRepository.showAllProduct();
                        break;
                    case 2:
                        cart.showAllProduct();
                        break;
                    case 3: {
                        System.out.println("Adding in the cart...");
                        int id = getProductId(scanner);
                        if (id == -1) {
                            System.out.println("Wrong number");
                            break;
                        }
                        Product productById = productRepository.getProductById(id);
                        if (productById != null) cart.addProduct(productById);
                        System.out.println("Added");
                        break;
                    }
                    case 4:
                        System.out.println("Removing from cart...");
                        int id = getProductId(scanner);
                        if (id == -1) {
                            System.out.println("wrong number");
                            break;
                        }
                        cart.removeProduct(id);
                        System.out.println("Removed");

                        break;
                    case 5:
                        loopFlag = false;
                        break;
                }
            }
        }
        System.out.println("Bye bye");
    }

    private void printInfo() {
        System.out.println("1. See all products\n" +
                "2. See products in cart\n" +
                "3. Add product  in cart \n" +
                "4. Remove product from cart\n" +
                "5. Leave the shop\n");
    }

    private int getProductId(Scanner scanner) {
        boolean loopBreaker = true;
        int number = -1;
        while (loopBreaker) {
            System.out.println("Enter the number : ");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                loopBreaker = false;
            } else {
                String str = scanner.next();
                if ("exit".equals(str)) {
                    loopBreaker = false;
                    continue;
                }
                System.out.println("Not found Int value (exit for cancel):"
                        + str);
            }
        }
        return number;
    }
}

