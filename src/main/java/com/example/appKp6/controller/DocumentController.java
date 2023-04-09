package com.example.appKp6.controller;


import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.Item;
import com.example.appKp6.exception.DocumentNotFoundException;
import com.example.appKp6.exception.SupplierNotFoundException;
import com.example.appKp6.repo.DocumentRepo;
import com.example.appKp6.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class DocumentController {

    @Autowired
    private DocumentRepo documentRepo;

    @PostMapping("/document")
    Document newDocument(@RequestBody Document newDocument){
        return documentRepo.save(newDocument);
    }

    @GetMapping("/documents")
    List<Document> getAllDocuments(){
        return documentRepo.findAll();
    }

    @GetMapping("/document/{id}")
    Document getDocumentById(@PathVariable Long id){
        return documentRepo.findById(id).orElseThrow(()->new DocumentNotFoundException(id));
    }

    @PutMapping("/document/{id}")
    Document updateDocument(@RequestBody Document newDocument,@PathVariable Long id){

        return documentRepo.findById(id).map(document -> {
            document.setNumber(newDocument.getNumber());
            document.setDate(newDocument.getDate());
            document.setStatus(newDocument.getStatus());
            document.setType(newDocument.getType());
            document.setId_user(newDocument.getId_user());
            document.setId_provider(newDocument.getId_provider());
            return documentRepo.save(document);
        }).orElseThrow(()->new DocumentNotFoundException(id));
    }

    @DeleteMapping("/document/{id}")
    String deleteDocument(@PathVariable Long id){
        if(!documentRepo.existsById(id)){
            throw  new DocumentNotFoundException(id);
        }
        documentRepo.deleteById(id);
        return  "Document with id "+id+" has been deleted success";

    }
}

