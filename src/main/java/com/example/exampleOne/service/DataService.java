package com.example.exampleOne.service;

import com.example.exampleOne.bo.DataBo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
@Validated
public interface DataService {
    DataBo saveData(DataBo dataBo);
    ResponseEntity<DataBo> getData(Long id);
    List<DataBo>getAllData();

}