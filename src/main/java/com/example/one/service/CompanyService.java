package com.example.one.service;

import com.example.one.entity.Address;
import com.example.one.entity.Company;
import com.example.one.payload.ApiResponse;
import com.example.one.payload.CompanyDto;
import com.example.one.repository.AddressRepository;
import com.example.one.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;
    public List<Company> get() {
        return companyRepository.findAll();
    }


    public ApiResponse add(CompanyDto companyDto) {
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHome_number(companyDto.getHome_number());
        addressRepository.save(address);
        Company company = new Company();
        company.setAddress(address);
        company.setCorpName(companyDto.getName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("this company added",true);
    }

    public ApiResponse edit(Integer id,CompanyDto companyDto) {
        Optional<Company> repositoryById = companyRepository.findById(id);
        if (repositoryById.isEmpty())
            return new ApiResponse("not found",false);
        Company company = repositoryById.get();
        Optional<Address> addressRepositoryById = addressRepository.findById(company.getAddress().getId());
        if (addressRepositoryById.isEmpty())
            return new ApiResponse("not found address id",false);
        Address address = addressRepositoryById.get();
        address.setHome_number(companyDto.getHome_number());
        address.setStreet(companyDto.getStreet());
        Address save = addressRepository.save(address);
        company.setAddress(save);
        company.setCorpName(companyDto.getName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("success added",true);
    }

    public ApiResponse delete(Integer id) {
        try {
            companyRepository.deleteById(id);
            return new ApiResponse("success",true);
        }
        catch (Exception e)
        {
            return new ApiResponse("this data not found",false);
        }
    }
}
