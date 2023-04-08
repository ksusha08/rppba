package com.example.appKp6.controller;

import com.example.appKp6.entity.Item;
import com.example.appKp6.entity.Supplier;
import com.example.appKp6.exception.SupplierNotFoundException;
import com.example.appKp6.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ItemController {

    @Autowired
    private ItemRepo itemRepo;

    @PostMapping("/item")
    Item newItem(@RequestBody Item newItem){
        return itemRepo.save(newItem);
    }

    @GetMapping("/items")
    List<Item> getAllItems(){
        return itemRepo.findAll();
    }

    @GetMapping("/item/{id}")
    Item getItemById(@PathVariable Long id){
        return itemRepo.findById(id).orElseThrow(()->new SupplierNotFoundException(id));
    }

    @PutMapping("/item/{id}")
    Item updateItem(@RequestBody Item newItem,@PathVariable Long id){

        return itemRepo.findById(id).map(item -> {
            item.setName(newItem.getName());
            item.setVendoreCode(newItem.getVendoreCode());
            item.setDescription(newItem.getDescription());
            item.setDiscountPrice(newItem.getDiscountPrice());
            return itemRepo.save(item);
        }).orElseThrow(()->new SupplierNotFoundException(id));
    }

    @DeleteMapping("/item/{id}")
    String deleteItem(@PathVariable Long id){
        if(!itemRepo.existsById(id)){
            throw  new SupplierNotFoundException(id);
        }
        itemRepo.deleteById(id);
        return  "Item with id "+id+" has been deleted success";

    }
}
