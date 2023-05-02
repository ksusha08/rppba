package com.example.appKp6.service.map;

import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.Item;
import com.example.appKp6.exception.ItemNotFoundException;
import com.example.appKp6.exception.SupplierNotFoundException;
import com.example.appKp6.repo.ItemRepo;
import com.example.appKp6.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;


    @Override
    public List<Item> findAll() {
        List<Item> items = itemRepo.findAll();
        return items;
    }

    @Override
    public Item findById(Long aLong) {
        return itemRepo.findById(aLong).orElseThrow(()->new ItemNotFoundException(aLong));
    }


    @Override
    public Item save(Item object) {
        return itemRepo.save(object);
    }

    @Override
    public void delete(Item object) {
        itemRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        itemRepo.deleteById(aLong);
    }


    public Item update(Item newItem, Long id){

        return itemRepo.findById(id).map(item -> {
            item.setName(newItem.getName());
            item.setVendoreCode(newItem.getVendoreCode());
            item.setDiscountPrice(newItem.getDiscountPrice());
            item.setNumber(newItem.getNumber());
            item.setPhotos(newItem.getPhotos());

            return itemRepo.save(item);
        }).orElseThrow(()->new ItemNotFoundException(id));
    }

    public List<Item> findByName(String name) {
        List<Item> items = itemRepo.findByNameContainingIgnoreCase(name);
        return items;
    }
}
