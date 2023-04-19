package com.example.appKp6.controller;

import com.example.appKp6.entity.Item;
import com.example.appKp6.exception.SupplierNotFoundException;
import com.example.appKp6.repo.ItemRepo;
import com.example.appKp6.service.map.FileUploadUtil;
import com.example.appKp6.service.map.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ItemController {

    private final ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }


    @PostMapping(value = "/item", consumes = { "multipart/form-data" })
    public Item newItem(@RequestParam("photos") MultipartFile file,
                        @RequestParam("name") String name,
                        @RequestParam("vendoreCode") String vendoreCode,
                        @RequestParam("description") String description,
                        @RequestParam("discountPrice") Double discountPrice) throws IOException {

        Item newItem = new Item();
        newItem.setName(name);
        newItem.setVendoreCode(vendoreCode);
        newItem.setDescription(description);
        newItem.setDiscountPrice(discountPrice);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        newItem.setPhotos(fileName);

        Item savedItem = itemService.save(newItem);

        String uploadDir = "user-photos/" + savedItem.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return savedItem;
    }

    @GetMapping("/items")
    List<Item> getAllItems(){
        return itemService.findAll();
    }

    @GetMapping("/item/{id}")
    Item getItemById(@PathVariable Long id){
        return itemService.findById(id);
    }

    @PutMapping(value = "/item/{id}", consumes = {"multipart/form-data"})
    public Item updateItem(@PathVariable Long id,
                           @RequestParam(name = "photos", required = false) MultipartFile file,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "vendoreCode") String vendoreCode,
                           @RequestParam(name = "description") String description,
                           @RequestParam(name = "discountPrice") Double discountPrice) throws IOException {

        Item itemToUpdate = itemService.findById(id);

        itemToUpdate.setName(name);
        itemToUpdate.setVendoreCode(vendoreCode);
        itemToUpdate.setDescription(description);
        itemToUpdate.setDiscountPrice(discountPrice);

        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            itemToUpdate.setPhotos(fileName);
            String uploadDir = "user-photos/" + id;
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }

        Item updatedItem = itemService.update(itemToUpdate, id);

        return updatedItem;
    }

    @DeleteMapping("/item/{id}")
    String deleteItem(@PathVariable Long id){

        itemService.deleteById(id);
        return  "Item with id "+id+" has been deleted success";

    }
}
