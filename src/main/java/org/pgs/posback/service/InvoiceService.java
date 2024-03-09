package org.pgs.posback.service;

import org.pgs.posback.DTO.Invoice.InvoiceRequestDTO;
import org.pgs.posback.DTO.Invoice.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {

    List<InvoiceResponseDTO> getAllInvoices();

    InvoiceResponseDTO getInvoiceById(Long id);

    InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO);

    InvoiceResponseDTO updateInvoice(Long invoiceId, InvoiceRequestDTO invoiceRequestDTO);

    void deleteInvoice(Long invoiceId);
}
