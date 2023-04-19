package com.example.appKp6.service.map;

import com.example.appKp6.entity.Item;
import com.example.appKp6.entity.Supplier;
import com.example.appKp6.exception.ItemNotFoundException;
import com.example.appKp6.exception.SupplierNotFoundException;
import com.example.appKp6.repo.ItemRepo;
import com.example.appKp6.repo.SupplierRepo;
import com.example.appKp6.service.ItemService;
import com.example.appKp6.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;


    @Override
    public List<Supplier> findAll() {
        List<Supplier> suppliers = supplierRepo.findAll();
        return suppliers;
    }

    @Override
    public Supplier findById(Long aLong) {
        return supplierRepo.findById(aLong).orElseThrow(()->new SupplierNotFoundException(aLong));
    }

    @Override
    public Supplier save(Supplier object) {
        return supplierRepo.save(object);
    }

    @Override
    public void delete(Supplier object) {
        supplierRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        supplierRepo.deleteById(aLong);
    }


    public Supplier update(Supplier newSupplier, Long id){
        return supplierRepo.findById(id).map(supplier -> {
            supplier.setName(newSupplier.getName());
            supplier.setEmail(newSupplier.getEmail());
            supplier.setAddress(newSupplier.getAddress());
            return supplierRepo.save(supplier);
        }).orElseThrow(()->new SupplierNotFoundException(id));
    }
}
