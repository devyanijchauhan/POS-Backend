package org.pgs.posback.controller;

import org.pgs.posback.DTO.Customer.CustomerRequestDTO;
import org.pgs.posback.DTO.Customer.CustomerResponseDTO;
import org.pgs.posback.model.Customer;
import org.pgs.posback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('NICE')")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
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
            return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{customerid}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long customerid, @RequestBody CustomerRequestDTO customerRequestDTO) {
        try {
            return new ResponseEntity<>(customerService.updateCustomer(customerRequestDTO,customerid), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

