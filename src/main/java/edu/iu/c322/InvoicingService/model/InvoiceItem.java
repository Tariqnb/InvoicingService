package edu.iu.c322.InvoicingService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;
@Entity

public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String status;
//    private List<Item> items;
    private Date on;
//    private Address address;
    private Long customerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public InvoiceItem() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public List<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }

    public Date getOn() {
        return on;
    }

    public void setOn(Date on) {
        this.on = on;
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }


//    public ResponseEntity<List<Item>> getInvoiceNumber(@PathVariable String invoiceNumber) {
//
//        InvoiceItem invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber);
//        if (invoice == null) {
//            return ResponseEntity.notFound().build();
//        }
//        List<Item> items = invoice.getItems();
//        return ResponseEntity.ok(items);
//    }
}
