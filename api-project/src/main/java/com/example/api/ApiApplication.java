package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

class ProductPayload {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

@RestController
@RequestMapping("/products")    //map from :9000/api
class ProductController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProductController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String createProductJob(@RequestBody ProductPayload payload) {
        kafkaTemplate.send("product-creation-topic", payload.getName());
        return "Product creation job queued successfully!";
    }
}
