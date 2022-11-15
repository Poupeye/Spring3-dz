package mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    @Autowired
    private ProductRepository productRepository;

    List<Product> productCart;

    @PostConstruct
    public void init() {
        productCart = new ArrayList<>();
    }

    public List<Product> getProductCart() {
        return productCart;
    }

    public void add(int id) {
        productCart.add(productRepository.getById(id));
    }

    public void delete(int id) {
        int a = -1;
        for (Product p : productCart) {
            if (p.getId() == id) {
                a = productCart.indexOf(p);
            }
        }
        if (a == -1) {
            return;
        }
        productCart.remove(a);
    }
}
