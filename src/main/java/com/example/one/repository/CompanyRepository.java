package com.example.one.repository;

import com.example.one.entity.Address;
import com.example.one.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
