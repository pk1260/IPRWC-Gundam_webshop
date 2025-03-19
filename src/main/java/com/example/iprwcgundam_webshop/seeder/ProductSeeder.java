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
                    new Product(null, "HG Guncannon", "A Real-grade model of the HG Guncannon.", "HIGH_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/f51dcd51641ddac1d35fbb38070289171c5945db.jpg?20250319115045", 24, 44.99f),
                    new Product(null, "Γ Gundam", "A Real-grade model of the Γ Gundam.", "HIGH_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/6874a1e9dbe46fd4983116c45db9cb4686a6a719.jpg?20250319115045", 24, 54.99f),
                    new Product(null, "Baund Docm", "A Real-grade model of the Baund Doc.", "HIGH_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/1dae3ecb456d216bc5ebff26a1c1ad1f8f1b451e.jpg?20250319115045", 24, 99.99f),
                    new Product(null, "Gundam Blazing", "A Real-grade model of the Gundam Blazing.", "HIGH_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/c465336352eef9b9a6866088cd4d26c98c828168.jpg?20250319115045", 24, 22.99f),
                    new Product(null, "RX-0 Full Armor Unicorn Gundam", "A Real-grade model of the RX-0 Full Armor Unicorn Gundam.", "REAL_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/11281bc95d2e5aeaba4b19127f4fc9ff49dfea1b.jpg?20250319115045", 24, 64.99f),
                    new Product(null, "00 Gundam Seven Sword/G", "A Real-grade model of the 00 Gundam Seven Sword/G.", "PERFECT_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/6a20498c4780ee67915f7a4aaa5afb3ae6906b52.jpg?20250319115045", 24, 254.99f),
                    new Product(null, "Aile Strike Rouge", "A Real-grade model of the Aile Strike Rouge.", "PERFECT_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/781dc78c188717ade70f092ca90aa148e950eaa6.jpg?20250319115045", 24, 209.99f),
                    new Product(null, "Unicorn Gundam 2 Banshee Norn", "A Real-grade model of the Unicorn Gundam 2 Banshee Norn.", "PERFECT_GRADE", "https://cdn.myonlinestore.eu/941eb4b5-6be1-11e9-a722-44a8421b9960/image/cache/full/31a7cf4ffae6ec9d2f93b5aab79d537ea3d03c4f.jpg?20250319115045", 24, 294.99f)
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
