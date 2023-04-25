package com.example.appKp6.controller;

import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.DocumentInfo;
import com.example.appKp6.entity.Income;
import com.example.appKp6.entity.Item;
import com.example.appKp6.service.map.DocumentServiceImpl;
import com.example.appKp6.service.map.IncomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class IncomeController {

    @Autowired
    private final IncomeServiceImpl incomeService;

    @Autowired
    private final DocumentServiceImpl documentService;

    public IncomeController(IncomeServiceImpl incomeService, DocumentServiceImpl documentService) {
        this.incomeService = incomeService;
        this.documentService = documentService;
    }




    @PostMapping("/income/{id}/")
    void newIncome( @PathVariable Long id){

        incomeService.addIncome(id);

    }



    @GetMapping("/income")
    List<Income> getAllDocuments(){
        return incomeService.findAll();
    }


    @GetMapping("/income/{id}")
    Income getIncomeById(@PathVariable Long id){
        return incomeService.findById(id);
    }

    @PutMapping("/income/{id}")
    Income updateIncome(@RequestBody Income newIncome,@PathVariable Long id){

        return incomeService.update(newIncome,id);
    }


    @DeleteMapping("/income/{id}")
    String deleteDocument(@PathVariable Long id){
        incomeService.deleteById(id);
        return  "DocumentInfo with id "+id+" has been deleted success";

    }
}
