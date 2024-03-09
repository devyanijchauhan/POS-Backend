package org.pgs.posback.service;

import org.pgs.posback.model.ReturnModel;

import java.util.List;

public interface ReturnService {

    List<ReturnModel> getAllReturns();

    ReturnModel getReturnById(Long id);

    ReturnModel createReturn(ReturnModel returnModel);

    ReturnModel updateReturn(Long returnId, ReturnModel returnModelDetails);

    void deleteReturn(Long returnId);
}
