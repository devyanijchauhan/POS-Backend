package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Store.StoreRequestDTO;
import org.pgs.posback.DTO.Store.StoreResponseDTO;
import org.pgs.posback.mapper.StoreMapper;
import org.pgs.posback.model.StoreModel;
import org.pgs.posback.repository.StoreRepository;
import org.pgs.posback.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<StoreResponseDTO> getAllStores() {
        List<StoreModel> stores = storeRepository.findAll();
        return stores.stream()
                .map(StoreMapper.INSTANCE::storeModelToStoreResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StoreResponseDTO getStoreById(Long id) {
        Optional<StoreModel> optionalStore = storeRepository.findById(id);
        return optionalStore.map(StoreMapper.INSTANCE::storeModelToStoreResponseDTO).orElse(null);
    }

    @Override
    public StoreResponseDTO createStore(StoreRequestDTO storeRequestDTO) {
        StoreModel store = StoreMapper.INSTANCE.storeRequestDTOToStoreModel(storeRequestDTO);
        StoreModel savedStore = storeRepository.save(store);
        return StoreMapper.INSTANCE.storeModelToStoreResponseDTO(savedStore);
    }

    @Override
    public StoreResponseDTO updateStore(Long storeId, StoreRequestDTO storeRequestDTO) {
        Optional<StoreModel> optionalStore = storeRepository.findById(storeId);
        if (optionalStore.isPresent()) {
            StoreModel store = optionalStore.get();
            store.setName(storeRequestDTO.getName());
            store.setAddress(storeRequestDTO.getAddress());
            store.setContactNumber(storeRequestDTO.getContactNumber());
            store.setOpeningHours(storeRequestDTO.getOpeningHours());
            StoreModel updatedStore = storeRepository.save(store);
            return StoreMapper.INSTANCE.storeModelToStoreResponseDTO(updatedStore);
        } else {
            return null; // or throw an exception
        }
    }

    @Override
    public void deleteStore(Long storeId) {
        storeRepository.deleteById(storeId);
    }
}
