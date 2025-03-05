package com.example.exampleOne.service;


import com.example.exampleOne.bo.DataBo;
import com.example.exampleOne.eo.DataEntity;
import com.example.exampleOne.mapper.DataMapper;
import com.example.exampleOne.DataRepository;
import com.example.exampleOne.exeption.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataService {

    private final DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public DataBo saveData(DataBo dataBO) {
        DataEntity entity = DataMapper.INSTANCE.boToEntity(dataBO);
        DataEntity savedEntity = dataRepository.save(entity);
        return DataMapper.INSTANCE.entityToBo(savedEntity);
    }

    public ResponseEntity<DataBo> getData(Long id) {
        DataEntity entity = dataRepository.findById( id)
                .orElseThrow(() -> new ResourceNotFoundException("The resource with ID" + id + "was not found"));
        DataBo dataBo = DataMapper.INSTANCE.entityToBo(entity);
        return ResponseEntity.ok(dataBo);    }
}
