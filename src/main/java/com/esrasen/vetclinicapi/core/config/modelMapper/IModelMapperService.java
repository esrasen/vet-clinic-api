package com.esrasen.vetclinicapi.core.config.modelMapper;

import org.modelmapper.ModelMapper;

import java.util.List;

public interface IModelMapperService {

    ModelMapper forRequest();

    ModelMapper forResponse();

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass);
}
