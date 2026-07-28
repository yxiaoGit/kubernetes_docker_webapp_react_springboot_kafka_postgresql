package com.example.worker;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class WorkerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkerApplication.class, args);
    }
}

@Entity
@Table(name = "products")
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    // change from Long , to match PostgreSQL SERIL type
    @Column(name = "hash_code", unique = true, nullable = false)
    private String hashCode;
    @Column(nullable = false)
    private String name;

    public Product() {}
    public Product(String hashCode, String name) {
        this.hashCode = hashCode;
        this.name = name;
    }
    public Integer getId() { return id; }
    public String getHashCode() { return hashCode; }
    public String getName() { return name; }
}

//interface ProductRepository extends JpaRepository<Product, Long> {}
interface ProductRepository extends JpaRepository<Product, Integer> {}

@Service
class ProductWorker {
    private final ProductRepository repository;

    public ProductWorker(ProductRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "product-creation-topic")
    public void consumeProductJob(String productName) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashBytes = digest.digest(productName.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            String hash = sb.toString();

            Product product = new Product(hash, productName);
            repository.save(product);
            System.out.println("Processing Success: " + productName + " -> " + hash);
        } catch (Exception e) {
            System.err.println("Worker execution processing error: " + e.getMessage());
        }
    }
}

