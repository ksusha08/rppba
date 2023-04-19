package com.example.appKp6.controller;


import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.Item;
import com.example.appKp6.exception.DocumentNotFoundException;
import com.example.appKp6.exception.SupplierNotFoundException;
import com.example.appKp6.repo.DocumentRepo;
import com.example.appKp6.repo.ItemRepo;
import com.example.appKp6.service.map.DocumentServiceImpl;
import com.example.appKp6.service.map.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class DocumentController {

    private final DocumentServiceImpl documentService;

    public DocumentController(DocumentServiceImpl documentService) {
        this.documentService = documentService;
    }


    @PostMapping("/document")
    Document newDocument(@RequestBody Document newDocument){
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

    @PutMapping("/document/{id}")
    Document updateDocument(@RequestBody Document newDocument,@PathVariable Long id){
        return documentService.update(newDocument,id);
    }

    @DeleteMapping("/document/{id}")
    String deleteDocument(@PathVariable Long id){
        documentService.deleteById(id);
        return  "Document with id "+id+" has been deleted success";

    }
}

