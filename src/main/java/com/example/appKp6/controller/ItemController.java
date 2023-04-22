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
    public Item newItem(@RequestPart("photos") MultipartFile file,
                        @RequestPart("item") Item item) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        item.setPhotos(fileName);

        Item savedItem = itemService.save(item);

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

    @PutMapping(value = "/item/{id}", consumes = { "multipart/form-data" })
    public Item updateItem(@PathVariable Long id,
                        @RequestPart(name = "photos", required = false) MultipartFile file,
                        @RequestPart("item") Item item) throws IOException {

        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            item.setPhotos(fileName);
            System.out.println("фото");
            String uploadDir = "user-photos/" + id;
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }

        Item updatedItem = itemService.update(item, id);

        return updatedItem;
    }

    @DeleteMapping("/item/{id}")
    String deleteItem(@PathVariable Long id){

        itemService.deleteById(id);
        return  "Item with id "+id+" has been deleted success";

    }
}
