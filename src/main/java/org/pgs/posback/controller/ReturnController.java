package org.pgs.posback.controller;

import org.pgs.posback.model.ReturnModel;
import org.pgs.posback.repository.AdminRepository;
import org.pgs.posback.repository.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/returns")
public class ReturnController {
    private ReturnRepository returnRepository;

    @Autowired
    public ReturnController(ReturnRepository returnRepository){
        this.returnRepository=returnRepository;
    }

    @GetMapping("/all")
    public List<ReturnModel> getAllReturns() {
        return returnRepository.findAll();
    }

    @GetMapping("/{Id}")
    public ReturnModel getReturnById(@PathVariable Long Id) {
        return returnRepository.findById(Id).orElse(null);
    }

    @PostMapping
    public ReturnModel createReturn(@RequestBody ReturnModel returnModel) {
        returnModel.setCreatedAt(new Date());
        returnModel.setUpdatedAt(new Date());
        return returnRepository.save(returnModel);
    }

    @PutMapping("/{returnId}")
    public ReturnModel updateReturn(@PathVariable Long returnId, @RequestBody ReturnModel returnModelDetails) {
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

    @DeleteMapping("/{Idr}")
    public void deleteReturn(@PathVariable Long Idr) {
        ReturnModel returnModel = returnRepository.findById(Idr).orElse(null);
        if (returnModel != null) {
            returnRepository.delete(returnModel);
        }
    }
}
