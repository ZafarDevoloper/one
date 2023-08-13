package com.example.one.controller;
import com.example.one.entity.Worker;
import com.example.one.payload.ApiResponse;
import com.example.one.payload.WorkerDto;
import com.example.one.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService service;

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody WorkerDto workerDto)
    {
        ApiResponse apiResponse = service.add(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);

    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id,@RequestBody WorkerDto workerDto)
    {
        ApiResponse edit = service.edit(id,workerDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.NOT_MODIFIED).body(edit);
    }

    @GetMapping
    public ResponseEntity<List<Worker>> get()
    {
        List<Worker> departments = service.get();
        return  ResponseEntity.status(HttpStatus.OK).body(departments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>delete(@PathVariable Integer id)
    {
        ApiResponse delete = service.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(delete);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)//shunday surov bulganda ishlasin
    @ExceptionHandler(MethodArgumentNotValidException.class)//validet olgan class ishlasin
    public Map<String,String>handValidationException(MethodArgumentNotValidException exception)
    {
        Map<String,String>getException=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(my_error -> {
            String fileName=((FieldError)my_error).getField();
            String error_massage=my_error.getDefaultMessage();
            getException.put(fileName,error_massage);
        });
        return getException;
    }




}
