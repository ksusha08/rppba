package com.example.appKp6.controller;


import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.Supplier;
import com.example.appKp6.entity.User;
import com.example.appKp6.service.map.DocumentInfoServiceImpl;
import com.example.appKp6.service.map.DocumentServiceImpl;
import com.example.appKp6.service.map.SupplierServiceImpl;
import com.example.appKp6.service.map.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class DocumentController {

    @Autowired
    private final DocumentServiceImpl documentService;

    @Autowired
    private final DocumentInfoServiceImpl documentInfoService;

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final SupplierServiceImpl supplierService;

    public DocumentController(DocumentServiceImpl documentService, DocumentInfoServiceImpl documentInfoService, UserServiceImpl userService, SupplierServiceImpl supplierService) {

        this.documentService = documentService;
        this.documentInfoService = documentInfoService;
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

    @GetMapping("/search_document/{number}")
    List<Document> getDocumentsByNumber(@PathVariable String number){

        return documentService.findByNumber(number);
    }

    @GetMapping("/filter_document/{start}/{end}")
    List<Document> getDocumentsByDate(@PathVariable Date start,
                                      @PathVariable Date end){

        return documentService.findByDateBetween(start,end);
    }

    @PutMapping("/document/{id}/{supplierId}")
    Document updateDocument(@RequestBody Document newDocument,@PathVariable Long id,@PathVariable(required = false) Long supplierId){

        Document updatedDocument = documentService.update(newDocument,id,supplierId);
        documentInfoService.reUpdatePrices(id);

        return updatedDocument;
    }


    @DeleteMapping("/document/{id}")
    String deleteDocument(@PathVariable Long id){
        documentService.deleteById(id);
        return  "Document with id "+id+" has been deleted success";

    }
}


