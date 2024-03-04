package org.pgs.posback.controller;

import org.pgs.posback.model.StoreModel;
import org.pgs.posback.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private StoreRepository storeRepository;

    @Autowired
    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @GetMapping("/all")
    public List<StoreModel> getAllStores() {
        return storeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<StoreModel> getStoreById(@PathVariable Long id) {
        return storeRepository.findById(id);
    }

    @PostMapping
    public StoreModel createStore(@RequestBody StoreModel storeModel) {
        return storeRepository.save(storeModel);
    }

    @PutMapping("/{storeid}")
    public StoreModel updateStore(@PathVariable Long storeid, @RequestBody StoreModel storeModel) {
        return storeRepository.findById(storeid)
                .map(existingStore -> {
                    existingStore.setName(storeModel.getName());
                    existingStore.setAddress(storeModel.getAddress());
                    existingStore.setContactNumber(storeModel.getContactNumber());
                    existingStore.setOpeningHours(storeModel.getOpeningHours());
                    return storeRepository.save(existingStore);
                })
                .orElse(null);
    }

    @DeleteMapping("/{ids}")
    public void deleteStore(@PathVariable Long ids) {
        storeRepository.deleteById(ids);
    }
}
