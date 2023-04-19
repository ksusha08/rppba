package com.example.appKp6.service.map;

import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.Item;
import com.example.appKp6.exception.DocumentNotFoundException;
import com.example.appKp6.exception.ItemNotFoundException;
import com.example.appKp6.repo.DocumentRepo;
import com.example.appKp6.repo.ItemRepo;
import com.example.appKp6.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepo documentRepo;


    @Override
    public List<Document> findAll() {
        List<Document> documents = documentRepo.findAll();
        return documents;
    }

    @Override
    public Document findById(Long aLong) {
        return documentRepo.findById(aLong).orElseThrow(()->new DocumentNotFoundException(aLong));
    }


    @Override
    public Document save(Document object) {
        return documentRepo.save(object);
    }

    @Override
    public void delete(Document object) {
        documentRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        documentRepo.deleteById(aLong);
    }


    public Document update(Document newDocument, Long id){

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
}
