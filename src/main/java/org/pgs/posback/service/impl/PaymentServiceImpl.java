package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Payment.PaymentRequestDTO;
import org.pgs.posback.DTO.Payment.PaymentResponseDTO;
import org.pgs.posback.mapper.PaymentMapper;
import org.pgs.posback.model.PaymentModel;
import org.pgs.posback.repository.PaymentRepository;
import org.pgs.posback.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        List<PaymentModel> payments = paymentRepository.findAll();
        return payments.stream()
                .map(PaymentMapper.INSTANCE::paymentModelToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Optional<PaymentModel> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.map(PaymentMapper.INSTANCE::paymentModelToResponseDTO).orElse(null);
    }

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        PaymentModel paymentModel = PaymentMapper.INSTANCE.paymentRequestDTOToModel(paymentRequestDTO);
        paymentModel.setCreatedAt(new Date());
        paymentModel.setUpdatedAt(new Date());
        PaymentModel savedPayment = paymentRepository.save(paymentModel);
        return PaymentMapper.INSTANCE.paymentModelToResponseDTO(savedPayment);
    }

    @Override
    public PaymentResponseDTO updatePayment(Long paymentId, PaymentRequestDTO paymentRequestDTO) {
        Optional<PaymentModel> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            PaymentModel paymentModel = optionalPayment.get();
            PaymentMapper.INSTANCE.updatePaymentFromRequestDTO(paymentRequestDTO, paymentModel);
            paymentModel.setUpdatedAt(new Date());
            PaymentModel updatedPayment = paymentRepository.save(paymentModel);
            return PaymentMapper.INSTANCE.paymentModelToResponseDTO(updatedPayment);
        }
        return null;
    }

    @Override
    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}