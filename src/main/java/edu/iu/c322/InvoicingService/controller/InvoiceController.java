package edu.iu.c322.InvoicingService.controller;

import edu.iu.c322.InvoicingService.model.InvoiceItem;
import edu.iu.c322.InvoicingService.model.ItemStatus;
import edu.iu.c322.InvoicingService.model.Order;
import edu.iu.c322.InvoicingService.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;


    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping
    public List<InvoiceItem> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItem> getInvoiceById(@PathVariable Long id) {
        InvoiceItem invoice = invoiceRepository.findById(id);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceItem> updateInvoice(@PathVariable Long id, @RequestBody InvoiceItem invoice) {
        InvoiceItem existingInvoice = invoiceRepository.findById(id);
        if (existingInvoice == null) {
            return ResponseEntity.notFound().build();
        } else {
            invoiceRepository.update(invoice,id);
        }
        return ResponseEntity.ok(existingInvoice);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        InvoiceItem existingInvoice = (InvoiceItem) invoiceRepository.findAll();
        if (existingInvoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}