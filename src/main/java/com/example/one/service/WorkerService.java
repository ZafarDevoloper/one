package com.example.one.service;

import com.example.one.entity.Address;
import com.example.one.entity.Department;
import com.example.one.entity.Worker;
import com.example.one.payload.ApiResponse;
import com.example.one.payload.WorkerDto;
import com.example.one.repository.AddressRepository;
import com.example.one.repository.DepartmentRepository;
import com.example.one.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Worker> get() {
        return workerRepository.findAll();
    }

    public ApiResponse add(WorkerDto workerDto) {
        Optional<Department> departmentRepositoryById = departmentRepository.findById(workerDto.getDepartment_id());
        if (departmentRepositoryById.isEmpty()) {
            return new ApiResponse("bunday department mavjud emas",false);
        }
        Address address = new Address();
        address.setHome_number(workerDto.getHome_number());
        address.setStreet(workerDto.getStreet());
        addressRepository.save(address);
        Worker worker = new Worker();
        worker.setAddress(address);
        worker.setName(workerDto.getName());
        worker.setPhone_number(workerDto.getPhoneNumber());
        worker.setDepartment(departmentRepositoryById.get());
        workerRepository.save(worker);
        return new ApiResponse("success full added",true);
    }

    public ApiResponse edit(Integer id,WorkerDto workerDto) {
        Optional<Worker> workerRepositoryById = workerRepository.findById(id);
        if (workerRepositoryById.isEmpty()) {
            return new ApiResponse("bunday ishchi mavjud emas",false);
        }
        Optional<Department> departmentRepositoryById = departmentRepository.findById(workerDto.getDepartment_id());
        if (departmentRepositoryById.isEmpty()) {
            return new ApiResponse("bunday korxona mavjud emas",false);
        }
        boolean b = workerRepository.existsByPhone_numberaAndIdNot(workerDto.getPhoneNumber(), id);
        if (b) {
return new ApiResponse("bunday raqamli mijoz mavjud",false);
        }
        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHome_number(workerDto.getHome_number());
        Worker worker = workerRepositoryById.get();
        worker.setPhone_number(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());
        worker.setDepartment(departmentRepositoryById.get());
        worker.setAddress(address);

        workerRepository.save(worker);
        return new ApiResponse("success full edited",true);

    }

    public ApiResponse delete(Integer id) {
        try {
            workerRepository.deleteById(id);
            return new ApiResponse("success",true);
        }
        catch (Exception e)
        {
            return new ApiResponse("this data not found",false);
        }
    }
}
