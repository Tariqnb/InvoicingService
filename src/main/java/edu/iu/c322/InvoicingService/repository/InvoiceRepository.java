package edu.iu.c322.InvoicingService.repository;

import edu.iu.c322.InvoicingService.model.InvoiceItem;
import edu.iu.c322.InvoicingService.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceRepository {
    private List<InvoiceItem> invoiceItems = new ArrayList<>();
  
    public List<InvoiceItem> findAll() {
        return invoiceItems;
    }

    public int create(InvoiceItem inovice) {
        int id = invoiceItems.size() + 1;
        invoiceItems.add(inovice);
        return id;
    }

    public InvoiceItem findById(Long id) {
        return invoiceItems.stream()
                .filter(invoiceItem -> invoiceItem.getCustomerId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public InvoiceItem getOrderById(Long id) {
        return invoiceItems.stream()
                .filter(invoiceItem -> invoiceItem.getCustomerId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void update(InvoiceItem invoiceItem, long id) {
        InvoiceItem invoiceItem1 = findById(id);
        if (invoiceItem1 != null) {
            invoiceItem1.setStatus(invoiceItem.getStatus());
        } else {
            throw new IllegalStateException("Order ID is not valid");
        }
    }
}
