package com.example.appKp6.controller;

import com.example.appKp6.entity.Supplier;
import com.example.appKp6.exception.SupplierNotFoundException;
import com.example.appKp6.repo.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class SupplierController {

    @Autowired
    private SupplierRepo supplierRepo;

    @PostMapping("/supplier")
    Supplier newSupplier(@RequestBody Supplier newSupplier){
        return supplierRepo.save(newSupplier);
    }

    @GetMapping("/suppliers")
    List<Supplier> getAllUsers(){
        return supplierRepo.findAll();
    }

    @GetMapping("/supplier/{id}")
    Supplier getSupplierById(@PathVariable Long id){
        return supplierRepo.findById(id).orElseThrow(()->new SupplierNotFoundException(id));
    }

    @PutMapping("/supplier/{id}")
    Supplier updateSupplier(@RequestBody Supplier newSupplier,@PathVariable Long id){

        return supplierRepo.findById(id).map(supplier -> {
            supplier.setName(newSupplier.getName());
            supplier.setEmail(newSupplier.getEmail());
            supplier.setAddress(newSupplier.getAddress());
            return supplierRepo.save(supplier);
        }).orElseThrow(()->new SupplierNotFoundException(id));
    }

    @DeleteMapping("/supplier/{id}")
    String deleteUser(@PathVariable Long id){
        if(!supplierRepo.existsById(id)){
            throw  new SupplierNotFoundException(id);
        }
        supplierRepo.deleteById(id);
        return  "Supplier with id "+id+" has been deleted success";

    }
}
