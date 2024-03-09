package org.pgs.posback.controller;

import org.pgs.posback.DTO.Invoice.InvoiceRequestDTO;
import org.pgs.posback.DTO.Invoice.InvoiceResponseDTO;
import org.pgs.posback.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoices() {
        List<InvoiceResponseDTO> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(@PathVariable Long id) {
        InvoiceResponseDTO invoice = invoiceService.getInvoiceById(id);
        return invoice != null ? ResponseEntity.ok(invoice) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        InvoiceResponseDTO createdInvoice = invoiceService.createInvoice(invoiceRequestDTO);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<InvoiceResponseDTO> updateInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        InvoiceResponseDTO updatedInvoice = invoiceService.updateInvoice(invoiceId, invoiceRequestDTO);
        return updatedInvoice != null ? ResponseEntity.ok(updatedInvoice) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return ResponseEntity.noContent().build();
    }
}
