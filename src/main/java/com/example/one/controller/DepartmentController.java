package com.example.one.controller;
import com.example.one.entity.Department;
import com.example.one.payload.ApiResponse;
import com.example.one.payload.DepartmentDto;
import com.example.one.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody DepartmentDto departmentDto)
    {
        ApiResponse apiResponse = departmentService.add(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id,@RequestBody DepartmentDto departmentDto)
    {
        ApiResponse edit = departmentService.edit(id, departmentDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.NOT_MODIFIED).body(edit);
    }
    @GetMapping
    public ResponseEntity<List<Department>> get()
    {
        List<Department> departments = departmentService.get();
        return  ResponseEntity.status(HttpStatus.OK).body(departments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>delete(@PathVariable Integer id)
    {
        ApiResponse delete = departmentService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(delete);
    }


}
