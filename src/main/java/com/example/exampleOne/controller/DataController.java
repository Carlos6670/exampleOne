package com.example.exampleOne.controller;

import com.example.exampleOne.bo.DataBo;
import com.example.exampleOne.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }


    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API working correctly");
    }

    @PostMapping("/data")
    public ResponseEntity<DataBo> saveData(@Valid @RequestBody DataBo dataBO) {
        DataBo savedData = dataService.saveData(dataBO);
        return ResponseEntity.ok(savedData);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<DataBo> getData(@PathVariable ("id") Long id) {
        return ResponseEntity.ok(dataService.getData(id).getBody());
    }
}