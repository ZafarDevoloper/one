package com.example.one.controller;
import com.example.one.entity.Company;
import com.example.one.payload.ApiResponse;
import com.example.one.payload.CompanyDto;
import com.example.one.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody CompanyDto companyDto)
    {
        ApiResponse add = companyService.add(companyDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id,@RequestBody CompanyDto companydto)
    {
        ApiResponse update = companyService.edit(id,companydto);
        return ResponseEntity.status(update.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(update);


    }
    @GetMapping
    public ResponseEntity<List<Company>> get()
    {
        List<Company> companies = companyService.get();
        return ResponseEntity.ok(companies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>delete(@PathVariable Integer id)
    {
        ApiResponse delete = companyService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(delete);
    }


}
