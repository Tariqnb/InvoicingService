package edu.iu.c322.InvoicingService.controller;

import edu.iu.c322.InvoicingService.OrderDeserializer;
import edu.iu.c322.InvoicingService.model.*;
import edu.iu.c322.InvoicingService.repository.InvoiceRepository;
import edu.iu.c322.InvoicingService.repository.InMemoryOrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final InMemoryOrderRepository orderRepository;
    private final OrderDeserializer orderDeserializer;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public OrderController(InMemoryOrderRepository orderRepository, InvoiceRepository invoiceRepository,
                           OrderDeserializer orderDeserializer) {
        this.orderRepository = orderRepository;
        this.orderDeserializer = orderDeserializer;
        this.invoiceRepository = invoiceRepository;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody @Valid String json) throws IOException {
        Order order = orderDeserializer.deserialize(json);

        if(validateOrder(order)){
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setCustomerId(order.getCustomerId());
            invoiceItem.setStatus("Ready to ship");
            invoiceItem.setOn(new Date());
//            invoiceItem.setAddress(order.getShippingAddress());
//            invoiceItem.setItems(order.getItems());
            order.setInvoiceItem(invoiceItem);
            invoiceRepository.create(invoiceItem);
            long id = orderRepository.create(order);
            return ResponseEntity.ok(" " + id);
        } else {
          return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    private boolean validateOrder(Order order) {
        Address address = order.getShippingAddress();
        Payment pay = order.getPayment();
        String state = address.getState().trim();
        String city = address.getCity().trim();
        if(state.isEmpty() && city.isEmpty()){
            return false;
        } else {
            return true;
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        Order order = orderRepository.getOrderById(id);
        if(order!=null){
            orderRepository.delete(Math.toIntExact(id));
            return new ResponseEntity<>("Order item deleted successfully", HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().body("Invalid order id");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderRepository.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PutMapping("/return")
    public ResponseEntity<String> cancelOrderItem(@RequestBody OrderCancellationRequest request) {
        long orderId = request.getOrderId();
        Order order = orderRepository.getOrderById(orderId);
        if(order!=null){
            return new ResponseEntity<>("Order item cancelled successfully", HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().body("Invalid order id");
        }
    }
}