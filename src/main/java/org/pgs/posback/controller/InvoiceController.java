package org.pgs.posback.controller;

import org.pgs.posback.model.InvoiceModel;
import org.pgs.posback.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/all")
    public List<InvoiceModel> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceModel> getInvoiceById(@PathVariable("id") Long id) {
        Optional<InvoiceModel> invoiceData = invoiceRepository.findById(id);
        return invoiceData.map(invoiceModel -> new ResponseEntity<>(invoiceModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<InvoiceModel> createInvoice(@RequestBody InvoiceModel invoiceModel) {
        try {
            InvoiceModel createdInvoice = invoiceRepository.save(invoiceModel);
            return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{invoiceid}")
    public ResponseEntity<InvoiceModel> updateInvoice(@PathVariable("invoiceid") Long invoiceid, @RequestBody InvoiceModel invoiceModel) {
        Optional<InvoiceModel> invoiceData = invoiceRepository.findById(invoiceid);

        if (invoiceData.isPresent()) {
            InvoiceModel updatedInvoice = invoiceData.get();
            updatedInvoice.setInvoiceNumber(invoiceModel.getInvoiceNumber());
            updatedInvoice.setDateTime(invoiceModel.getDateTime());
            updatedInvoice.setInvoicePrice(invoiceModel.getInvoicePrice());
            updatedInvoice.setTotalAmount(invoiceModel.getTotalAmount());
            updatedInvoice.setTaxAmount(invoiceModel.getTaxAmount());
            updatedInvoice.setDiscountAmount(invoiceModel.getDiscountAmount());
            updatedInvoice.setPaymentMethod(invoiceModel.getPaymentMethod());
            updatedInvoice.setCustomer(invoiceModel.getCustomer());
            updatedInvoice.setStore(invoiceModel.getStore());
            updatedInvoice.setUpdatedAt(new Date());


            return new ResponseEntity<>(invoiceRepository.save(updatedInvoice), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idi}")
    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("idi") Long idi) {
        try {
            invoiceRepository.deleteById(idi);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method to calculate invoice price
    private void calculateInvoicePrice(InvoiceModel invoiceModel) {
        // Calculate invoice price logic based on totalAmount, taxAmount, and discountAmount
        BigDecimal totalAmount = invoiceModel.getTotalAmount();
        BigDecimal taxAmount = invoiceModel.getTaxAmount();
        BigDecimal discountAmount = invoiceModel.getDiscountAmount();

        // Calculate invoice price = totalAmount + taxAmount - discountAmount
        BigDecimal invoicePrice = totalAmount.add(taxAmount).subtract(discountAmount);

        // Set calculated invoice price
        invoiceModel.setInvoicePrice(invoicePrice);
    }

}
