package com.example.exampleOne.mapper;


import com.example.exampleOne.bo.DataBo;
import com.example.exampleOne.eo.DataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Mapper (componentModel = "spring")
@Component
public interface DataMapper {
    DataMapper INSTANCE = Mappers.getMapper(DataMapper.class);


    DataBo entityToBo(DataEntity entity);
    DataEntity boToEntity(DataBo bo);
}