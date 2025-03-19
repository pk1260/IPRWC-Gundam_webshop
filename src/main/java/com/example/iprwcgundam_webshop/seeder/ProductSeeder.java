package com.example.iprwcgundam_webshop.seeder;

import com.example.iprwcgundam_webshop.dao.ProductDAO;
import com.example.iprwcgundam_webshop.model.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSeeder {
    private final ProductDAO productDAO;
    private final Logger logger = LoggerFactory.getLogger(ProductSeeder.class);

    @PostConstruct
    public void seed() {
        if (productDAO.findAll().isEmpty()) {
            List<Product> products = Arrays.asList(
                    new Product(null, "Gundam RX-78-2", "A high-grade model of the RX-78-2 Gundam.", "HIGH_GRADE", "https://m.media-amazon.com/images/I/61Ti3VHNURL._AC_UF1000,1000_QL80_.jpg", 24, 29.99f),
                    new Product(null, "Gundam Exia", "A master-grade model of the Gundam Exia.", "MASTER_GRADE", "https://m.media-amazon.com/images/I/71LZrtPMcHL.jpg", 24, 49.99f),
                    new Product(null, "MSN-06S Sinanju", "A Real-grade model of the MSN-06S Sinanju.", "REAL_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/4f6679c643b36bb252bacfd924f2282b2f67e8c4.jpg?20250319115045", 24, 49.99f),
                    new Product(null, "Gundam Gremory", "A Real-grade model of the Gundam Gremory.", "HIGH_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/b23e205dfe34ae3c51c29bae8da5ceff2d432366.jpg?20250319115045", 24, 244.99f),
                    new Product(null, "Gundam RX-78-2", "A high-grade model of the RX-78-2 Gundam.", "HIGH_GRADE", "https://m.media-amazon.com/images/I/61Ti3VHNURL._AC_UF1000,1000_QL80_.jpg", 24, 29.99f),
                    new Product(null, "Gundam Exia", "A master-grade model of the Gundam Exia.", "MASTER_GRADE", "https://m.media-amazon.com/images/I/71LZrtPMcHL.jpg", 24, 49.99f),
                    new Product(null, "MSN-06S Sinanju", "A Real-grade model of the MSN-06S Sinanju.", "REAL_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/4f6679c643b36bb252bacfd924f2282b2f67e8c4.jpg?20250319115045", 24, 49.99f),
                    new Product(null, "Gundam Gremory", "A Real-grade model of the Gundam Gremory.", "HIGH_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/b23e205dfe34ae3c51c29bae8da5ceff2d432366.jpg?20250319115045", 24, 244.99f)
            );

            products.forEach(product -> {
                try {
                    productDAO.save(product);
                } catch (Exception e) {
                    logger.error("Failed to seed product: " + product.getName(), e);
                }
            });
        } else {
            logger.info("Products already exist in the database. Skipping seeding.");
        }
    }
}
