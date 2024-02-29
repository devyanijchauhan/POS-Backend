package org.pgs.posback.controller;

import org.pgs.posback.model.CustomerModel;
import org.pgs.posback.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/all")
    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable Long id) {
        Optional<CustomerModel> customerData = customerRepository.findById(id);
        return customerData.map(customerModel -> new ResponseEntity<>(customerModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customerModel) {
        try {
            CustomerModel createdCustomer = customerRepository.save(customerModel);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{customerid}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable Long customerid, @RequestBody CustomerModel customerModel) {
        Optional<CustomerModel> customerData = customerRepository.findById(customerid);

        if (customerData.isPresent()) {
            CustomerModel updatedCustomer = customerData.get();
            updatedCustomer.setName(customerModel.getName());
            updatedCustomer.setContactInformation(customerModel.getContactInformation());
            updatedCustomer.setLoyaltyPoints(customerModel.getLoyaltyPoints());
            updatedCustomer.setDateOfBirth(customerModel.getDateOfBirth());
            updatedCustomer.setEmail(customerModel.getEmail());
            updatedCustomer.setAddress(customerModel.getAddress());
            updatedCustomer.setCreatedAt(customerModel.getCreatedAt());
            updatedCustomer.setUpdatedAt(customerModel.getUpdatedAt());
            return new ResponseEntity<>(customerRepository.save(updatedCustomer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long ids) {
        try {
            customerRepository.deleteById(ids);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
