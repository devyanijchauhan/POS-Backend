package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Invoice.InvoiceRequestDTO;
import org.pgs.posback.DTO.Invoice.InvoiceResponseDTO;
import org.pgs.posback.mapper.InvoiceMapper;
import org.pgs.posback.model.InvoiceModel;
import org.pgs.posback.repository.InvoiceRepository;
import org.pgs.posback.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<InvoiceResponseDTO> getAllInvoices() {
        List<InvoiceModel> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(InvoiceMapper.INSTANCE::invoiceModelToInvoiceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceResponseDTO getInvoiceById(Long id) {
        Optional<InvoiceModel> optionalInvoice = invoiceRepository.findById(id);
        return optionalInvoice.map(InvoiceMapper.INSTANCE::invoiceModelToInvoiceResponseDTO).orElse(null);
    }

    @Override
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        InvoiceModel invoiceModel = InvoiceMapper.INSTANCE.invoiceRequestDTOToInvoiceModel(invoiceRequestDTO);
        InvoiceModel savedInvoice = invoiceRepository.save(invoiceModel);
        return InvoiceMapper.INSTANCE.invoiceModelToInvoiceResponseDTO(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO updateInvoice(Long invoiceId, InvoiceRequestDTO invoiceRequestDTO) {
        Optional<InvoiceModel> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            InvoiceModel invoice = optionalInvoice.get();
            InvoiceModel updatedInvoice = invoiceRepository.save(invoice);
            return InvoiceMapper.INSTANCE.invoiceModelToInvoiceResponseDTO(updatedInvoice);
        } else {
            return null; // Or throw an exception
        }
    }

    @Override
    public void deleteInvoice(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }
}
