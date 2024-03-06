package org.pgs.posback.controller;

import org.pgs.posback.DTO.Store.StoreRequestDTO;
import org.pgs.posback.DTO.Store.StoreResponseDTO;
import org.pgs.posback.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StoreResponseDTO>> getAllStores() {
        List<StoreResponseDTO> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> getStoreById(@PathVariable Long id) {
        StoreResponseDTO store = storeService.getStoreById(id);
        return store != null ? ResponseEntity.ok(store) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StoreResponseDTO> createStore(@RequestBody StoreRequestDTO storeRequestDTO) {
        StoreResponseDTO createdStore = storeService.createStore(storeRequestDTO);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponseDTO> updateStore(@PathVariable Long storeId, @RequestBody StoreRequestDTO storeRequestDTO) {
        StoreResponseDTO updatedStore = storeService.updateStore(storeId, storeRequestDTO);
        return updatedStore != null ? ResponseEntity.ok(updatedStore) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{Ids}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long Ids) {
        storeService.deleteStore(Ids);
        return ResponseEntity.noContent().build();
    }
}
