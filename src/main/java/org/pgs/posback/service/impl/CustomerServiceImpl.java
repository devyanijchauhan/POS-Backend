package org.pgs.posback.service.impl;

import jakarta.transaction.Transactional;
import org.pgs.posback.DTO.Customer.CustomerRequestDTO;
import org.pgs.posback.DTO.Customer.CustomerResponseDTO;
import org.pgs.posback.mapper.CustomerMapper;
import org.pgs.posback.model.Customer;
import org.pgs.posback.repository.CustomerRepository;
import org.pgs.posback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customer= CustomerMapper.INSTANCE.CUSTOMER_REQ_DTO_TO_CUSTOMER(customerRequestDTO);
        return CustomerMapper.INSTANCE.CUSTOMER_TO_CUSTOMER_RES_DTO(customerRepository.save(customer));

    }

    @Override
    public List<CustomerResponseDTO> getAllCustomer() {

        List<Customer> customerList=customerRepository.findAll();
        if(customerList != null){
            return CustomerMapper.INSTANCE.CUSTOMERS_TO_CUSTOMER_RES_LIST_DTO(customerList);
        } else {
            throw new RuntimeException("List is empty");
        }

    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            return CustomerMapper.INSTANCE.CUSTOMER_TO_CUSTOMER_RES_DTO(optionalCustomer.get());
        } else {
            throw new RuntimeException("customer with id "+id+" is not exist");
        }
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerRequestDTO customerRequestDTO, Long id) {


        Optional<Customer> updatedCustomer = customerRepository.findById(id);

        if (updatedCustomer.isPresent()) {
            if(customerRequestDTO.getName()!= null) {
                updatedCustomer.get().setName(customerRequestDTO.getName());
            }

            if (customerRequestDTO.getContactNumber() != null) {
                updatedCustomer.get().setContactNumber(customerRequestDTO.getContactNumber());
            }

            if (customerRequestDTO.getDateOfBirth() != null) {
                updatedCustomer.get().setDateOfBirth(customerRequestDTO.getDateOfBirth());
            }
            if (customerRequestDTO.getEmail() != null) {
                updatedCustomer.get().setEmail(customerRequestDTO.getEmail());
            }
            if (customerRequestDTO.getAddress() != null) {
                updatedCustomer.get().setAddress(customerRequestDTO.getAddress());
            }

            if (customerRequestDTO.getGender() != null) {
                updatedCustomer.get().setGender(customerRequestDTO.getGender());
            }


            if (customerRequestDTO.getCreatedAt() != null) {
                updatedCustomer.get().setCreatedAt(customerRequestDTO.getCreatedAt());
            }

            if (customerRequestDTO.getUpdatedAt() != null) {
                updatedCustomer.get().setUpdatedAt(customerRequestDTO.getCreatedAt());
            }


//            Customer updatedCustomer = customerData.get();
//            updatedCustomer.setName(customerModel.getName());
//            updatedCustomer.setContactNumber(customerModel.getContactNumber());
//            updatedCustomer.setLoyaltyPoints(customerModel.getLoyaltyPoints());
//            updatedCustomer.setDateOfBirth(customerModel.getDateOfBirth());
//            updatedCustomer.setEmail(customerModel.getEmail());
//            updatedCustomer.setAddress(customerModel.getAddress());
//            updatedCustomer.setCreatedAt(customerModel.getCreatedAt());
//            updatedCustomer.setUpdatedAt(customerModel.getUpdatedAt());
            return CustomerMapper.INSTANCE.CUSTOMER_TO_CUSTOMER_RES_DTO(customerRepository.save(updatedCustomer.get()));
        } else {
            throw new RuntimeException("customer with id "+" is not exist");
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> optionalCustomer= customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
            customerRepository.delete(optionalCustomer.get());
        } else {
            throw new RuntimeException("Error in deleting customer");
        }
    }

}



