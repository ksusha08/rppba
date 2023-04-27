package com.example.appKp6.service.map;

import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.DocumentInfo;
import com.example.appKp6.entity.Item;
import com.example.appKp6.exception.DocumentNotFoundException;
import com.example.appKp6.repo.DocumentInfoRepo;
import com.example.appKp6.service.DocumentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentInfoServiceImpl implements DocumentInfoService {

    @Autowired
    private DocumentInfoRepo documentInfoRepo;

    @Autowired
    private final ItemServiceImpl itemService;

    @Autowired
    private final DocumentServiceImpl documentService;

    public DocumentInfoServiceImpl(ItemServiceImpl itemService, DocumentServiceImpl documentService) {
        this.itemService = itemService;
        this.documentService = documentService;
    }


    @Override
    public List<DocumentInfo> findAll() {
        List<DocumentInfo> documentInfo = documentInfoRepo.findAll();
        return documentInfo;
    }

    @Override
    public DocumentInfo findById(Long aLong) {
        return documentInfoRepo.findById(aLong).orElseThrow(()->new DocumentNotFoundException(aLong));
    }


    @Override
    public DocumentInfo save(DocumentInfo documentInfo) {

        return documentInfoRepo.save(documentInfo);
    }

    public void saveDocInfo(DocumentInfo documentInfo) {

        Document doc = documentInfo.getDocument();

        Item item = documentInfo.getItem();
        Long id = item.getId();

        int newNumber = item.getNumber() - documentInfo.getAmount();
        item.setNumber(newNumber);
        itemService.update(item,id);

        documentInfoRepo.save(documentInfo);

        List<DocumentInfo> documentInfoList = findByDocumentId(doc.getId());
        documentService.updateSummAndAmount(doc, doc.getId(), documentInfoList);

    }

    @Override
    public void delete(DocumentInfo documentInfo) {

        Item item = documentInfo.getItem();
        Long id = item.getId();

        int newNumber = item.getNumber() + documentInfo.getAmount();
        item.setNumber(newNumber);

        itemService.update(item,id);

        documentInfoRepo.delete(documentInfo);
    }

    @Override
    public void deleteById(Long aLong) {

        DocumentInfo documentInfo = findById(aLong);

        Item item = documentInfo.getItem();
        Long id = item.getId();

        int newNumber = item.getNumber() + documentInfo.getAmount();
        item.setNumber(newNumber);

        itemService.update(item,id);



        documentInfoRepo.deleteById(aLong);
    }


    public DocumentInfo update(DocumentInfo newDocumentInfo, Long id,Long itemId ){

        return documentInfoRepo.findById(id).map(documentInfo -> {

            documentInfo.setAmount(newDocumentInfo.getAmount());
            documentInfo.setItem(newDocumentInfo.getItem());
            documentInfo.setSumm(newDocumentInfo.getSumm());

            if(itemId != null) {
                Item item = itemService.findById(itemId);
                documentInfo.setItem(item);
            }
            return documentInfoRepo.save(documentInfo);
        }).orElseThrow(()->new DocumentNotFoundException(id));
    }

    public List<DocumentInfo> findByDocumentId(Long documentId) {
        return documentInfoRepo.findByDocumentId(documentId);
    }

}
