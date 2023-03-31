package edu.iu.c322.InvoicingService;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iu.c322.InvoicingService.model.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderDeserializer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Order deserialize(String json) throws IOException {
        return objectMapper.readValue(json, Order.class);
    }
}