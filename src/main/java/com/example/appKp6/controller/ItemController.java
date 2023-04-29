package com.example.appKp6.controller;

import com.example.appKp6.entity.Category;
import com.example.appKp6.entity.Item;
import com.example.appKp6.service.map.CategoryServiceImpl;
import com.example.appKp6.service.map.FileUploadUtil;
import com.example.appKp6.service.map.ItemServiceImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ItemController {

    private final ItemServiceImpl itemService;
    private final CategoryServiceImpl categoryService;

    public ItemController(ItemServiceImpl itemService, CategoryServiceImpl categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }


    @PostMapping(value = "/item/{categoryId}", consumes = { "multipart/form-data" })
    public Item newItem(@PathVariable Long categoryId,
                        @RequestPart("photos") MultipartFile file,
                        @RequestPart("item") Item item) throws IOException {

        Category category = categoryService.findById(categoryId);
        item.setCategory(category);


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
