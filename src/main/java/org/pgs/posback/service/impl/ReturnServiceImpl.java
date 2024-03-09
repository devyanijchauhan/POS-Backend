package org.pgs.posback.service.impl;

import org.pgs.posback.model.ReturnModel;
import org.pgs.posback.repository.ReturnRepository;
import org.pgs.posback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {

    private final ReturnRepository returnRepository;

    @Autowired
    public ReturnServiceImpl(ReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    @Override
    public List<ReturnModel> getAllReturns() {
        return returnRepository.findAll();
    }

    @Override
    public ReturnModel getReturnById(Long id) {
        return returnRepository.findById(id).orElse(null);
    }

    @Override
    public ReturnModel createReturn(ReturnModel returnModel) {
        returnModel.setCreatedAt(new Date());
        returnModel.setUpdatedAt(new Date());
        return returnRepository.save(returnModel);
    }

    @Override
    public ReturnModel updateReturn(Long returnId, ReturnModel returnModelDetails) {
        ReturnModel returnModel = returnRepository.findById(returnId).orElse(null);
        if (returnModel != null) {
            returnModel.setDate(returnModelDetails.getDate());
            returnModel.setReason(returnModelDetails.getReason());
            returnModel.setRefundedAmount(returnModelDetails.getRefundedAmount());
            returnModel.setSale(returnModelDetails.getSale());
            returnModel.setInvoice(returnModelDetails.getInvoice());
            returnModel.setUpdatedAt(new Date());
            return returnRepository.save(returnModel);
        } else {
            return null;
        }
    }

    @Override
    public void deleteReturn(Long returnId) {
        ReturnModel returnModel = returnRepository.findById(returnId).orElse(null);
        if (returnModel != null) {
            returnRepository.delete(returnModel);
        }
    }
}
