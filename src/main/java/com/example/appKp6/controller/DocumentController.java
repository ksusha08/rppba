package com.example.appKp6.controller;


import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.Supplier;
import com.example.appKp6.entity.User;
import com.example.appKp6.service.map.DocumentServiceImpl;
import com.example.appKp6.service.map.SupplierServiceImpl;
import com.example.appKp6.service.map.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class DocumentController {

    @Autowired
    private final DocumentServiceImpl documentService;

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final SupplierServiceImpl supplierService;

    public DocumentController(DocumentServiceImpl documentService, UserServiceImpl userService, SupplierServiceImpl supplierService) {

        this.documentService = documentService;
        this.userService = userService;
        this.supplierService = supplierService;
    }


    @PostMapping("/document/{userId}/{supplierId}")
    Document newDocument(@RequestBody Document newDocument, @PathVariable Long userId,@PathVariable Long supplierId){

        Supplier supplier = supplierService.findById(supplierId);
        newDocument.setSupplier(supplier);

        User user = userService.findById(userId);
        newDocument.setUser(user);

        return documentService.save(newDocument);
    }

    @GetMapping("/documents")
    List<Document> getAllDocuments(){
        return documentService.findAll();
    }

    @GetMapping("/document/{id}")
    Document getDocumentById(@PathVariable Long id){
        return documentService.findById(id);
    }

    @PutMapping("/document/{id}/{supplierId}")
    Document updateDocument(@RequestBody Document newDocument,@PathVariable Long id,@PathVariable(required = false) Long supplierId){

        return documentService.update(newDocument,id,supplierId);
    }


    @DeleteMapping("/document/{id}")
    String deleteDocument(@PathVariable Long id){
        documentService.deleteById(id);
        return  "Document with id "+id+" has been deleted success";

    }
}


