package simsek.ali.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import simsek.ali.VeterinaryManagementProject.dto.request.CustomerRequestDto;
import simsek.ali.VeterinaryManagementProject.entity.Customer;
import simsek.ali.VeterinaryManagementProject.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    public final CustomerService customerService;

    @GetMapping
    public List<Customer> findAllCustomers (){
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer findCustomerById (@PathVariable Long id){
        return customerService.findCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer (@RequestBody CustomerRequestDto customerRequestDto){
        return customerService.createCustomer(customerRequestDto);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer (@PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto){
        return customerService.updateCustomer(id,customerRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }

}
