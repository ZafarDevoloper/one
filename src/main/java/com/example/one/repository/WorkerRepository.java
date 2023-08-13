package com.example.one.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<com.example.one.entity.Worker,Integer> {
boolean existsByPhone_numberaAndIdNot(String phone,Integer id);
}
