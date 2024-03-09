package org.pgs.posback.controller;

import org.pgs.posback.model.ReturnModel;
import org.pgs.posback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returns")
public class ReturnController {

    private final ReturnService returnService;

    @Autowired
    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @GetMapping("/all")
    public List<ReturnModel> getAllReturns() {
        return returnService.getAllReturns();
    }

    @GetMapping("/{id}")
    public ReturnModel getReturnById(@PathVariable Long id) {
        return returnService.getReturnById(id);
    }

    @PostMapping
    public ReturnModel createReturn(@RequestBody ReturnModel returnModel) {
        return returnService.createReturn(returnModel);
    }

    @PutMapping("/{returnId}")
    public ReturnModel updateReturn(@PathVariable Long returnId, @RequestBody ReturnModel returnModelDetails) {
        return returnService.updateReturn(returnId, returnModelDetails);
    }

    @DeleteMapping("/{Idr}")
    public void deleteReturn(@PathVariable Long Idr) {
        returnService.deleteReturn(Idr);
    }
}
