package org.pgs.posback.controller;

import org.pgs.posback.dto.Customer.CustomerRequestDTO;
import org.pgs.posback.dto.Customer.CustomerResponseDTO;
import org.pgs.posback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('NICE')")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomer(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        try {
            return new ResponseEntity<>(customerService.createCustomer(customerRequestDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
       try {
           return new ResponseEntity<>(customerService.getById(id),HttpStatus.OK);
       } catch (Exception e){
           throw new RuntimeException(e);
       }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//
//    @PutMapping("/{customerid}")
//    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerid, @RequestBody Customer customerModel) {
//        Optional<Customer> customerData = customerRepository.findById(customerid);
//
//        if (customerData.isPresent()) {
//            Customer updatedCustomer = customerData.get();
//            updatedCustomer.setName(customerModel.getName());
//            updatedCustomer.setContactInformation(customerModel.getContactInformation());
//            updatedCustomer.setLoyaltyPoints(customerModel.getLoyaltyPoints());
//            updatedCustomer.setDateOfBirth(customerModel.getDateOfBirth());
//            updatedCustomer.setEmail(customerModel.getEmail());
//            updatedCustomer.setAddress(customerModel.getAddress());
//            updatedCustomer.setCreatedAt(customerModel.getCreatedAt());
//            updatedCustomer.setUpdatedAt(customerModel.getUpdatedAt());
//            return new ResponseEntity<>(customerRepository.save(updatedCustomer), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


}
