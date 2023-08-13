package com.example.one.service;

import com.example.one.entity.Address;
import com.example.one.entity.Company;
import com.example.one.entity.Department;
import com.example.one.payload.ApiResponse;
import com.example.one.payload.DepartmentDto;
import com.example.one.repository.AddressRepository;
import com.example.one.repository.CompanyRepository;
import com.example.one.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Department> get() {
        return departmentRepository.findAll();
    }


    public ApiResponse add(DepartmentDto departmentDto) {
        Optional<Company> companyRepositoryById = companyRepository.findById(departmentDto.getCompany_id());
        if (companyRepositoryById.isEmpty()) {
            return new ApiResponse("not found company_id",false);
        }
        Company company = companyRepositoryById.get();
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(company);
    departmentRepository.save(department);
    return new ApiResponse("success full added",true);
    }

    public ApiResponse edit(Integer id,DepartmentDto departmentDto) {
        Optional<Department> departmentRepositoryById = departmentRepository.findById(id);
        if (departmentRepositoryById.isEmpty()) {
            return new ApiResponse("department not found",false);
        }
        Optional<Company> companyRepositoryById = companyRepository.findById(departmentDto.getCompany_id());
        if (companyRepositoryById.isEmpty()) {
            return new ApiResponse("company not found",false);
        }
        Company company = companyRepositoryById.get();
        Department department = departmentRepositoryById.get();
        department.setCompany(company);
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("data edited",true);
    }
    public ApiResponse delete(Integer id) {
        try {
            departmentRepository.deleteById(id);
            return new ApiResponse("success",true);
        }
        catch (Exception e)
        {
            return new ApiResponse("this data not found",false);
        }
    }
}
