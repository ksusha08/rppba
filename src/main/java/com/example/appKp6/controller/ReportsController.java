package com.example.appKp6.controller;

import com.example.appKp6.dto.BalanceReport;
import com.example.appKp6.dto.Report;
import com.example.appKp6.entity.DocumentInfo;
import com.example.appKp6.service.map.DocumentInfoServiceImpl;
import com.example.appKp6.service.map.ReportsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
public class ReportsController {

    private final ReportsService reportsService;
    private final DocumentInfoServiceImpl documentInfoService;

    public ReportsController(ReportsService reportsService, DocumentInfoServiceImpl documentInfoService) {
        this.reportsService = reportsService;
        this.documentInfoService = documentInfoService;
    }


    @GetMapping("/report/{start}/{end}")
    List<Report> getDocumentsByDate(@PathVariable Date start,
                                    @PathVariable Date end){

        return documentInfoService.findDocInfoForReports(start,end);
    }

    @GetMapping("/balance_report/{start}/{end}")
    List<BalanceReport> getBalanceReport(@PathVariable Date start,
                                         @PathVariable Date end){

        return documentInfoService.generateBalanceReport(start,end);
    }
}