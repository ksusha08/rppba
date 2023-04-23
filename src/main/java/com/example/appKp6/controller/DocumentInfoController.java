package com.example.appKp6.controller;

import com.example.appKp6.entity.*;
import com.example.appKp6.service.map.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class DocumentInfoController {
    @Autowired
    private final DocumentInfoServiceImpl documentInfoService;


    @Autowired
    private final ItemServiceImpl itemService;

    @Autowired
    private final DocumentServiceImpl documentService;

    public DocumentInfoController(DocumentInfoServiceImpl documentInfoService, ItemServiceImpl itemService, DocumentServiceImpl documentService) {
        this.documentInfoService = documentInfoService;
        this.itemService = itemService;
        this.documentService = documentService;
    }


    @PostMapping("/documentInfo/{id}/{itemId}/")
    DocumentInfo newDocumentInfo(@RequestBody DocumentInfo newDocumentInfo, @PathVariable Long id, @PathVariable Long itemId){

        Document document = documentService.findById(id);
        newDocumentInfo.setDocument(document);

        Item item = itemService.findById(itemId);
        newDocumentInfo.setItem(item);

        return documentInfoService.save(newDocumentInfo);
    }


    @GetMapping("/documentInfo")
    List<DocumentInfo> getAllDocuments(){
        return documentInfoService.findAll();
    }

    @GetMapping("/documentInfo/findByDocId/{id}")
    List<DocumentInfo> getDocumentByDocId(@PathVariable Long id){
        return documentInfoService.findByDocumentId(id);
    }

    @GetMapping("/documentInfo/{id}")
    DocumentInfo getDocumentById(@PathVariable Long id){
        return documentInfoService.findById(id);
    }

    @PutMapping("/documentInfo/{id}/{itemId}")
    DocumentInfo updateDocument(@RequestBody DocumentInfo newDocumentInfo,@PathVariable Long id,@PathVariable(required = false) Long itemId){

        return documentInfoService.update(newDocumentInfo,id,itemId);
    }


    @DeleteMapping("/documentInfo/{id}")
    String deleteDocument(@PathVariable Long id){
        documentInfoService.deleteById(id);
        return  "DocumentInfo with id "+id+" has been deleted success";

    }

}
