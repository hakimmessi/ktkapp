package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.component.component;
import com.ktk.ktkapp.model.component.componentModel;
import com.ktk.ktkapp.repos.component.componentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class componentService {

    @Autowired
    private componentRepo componentRepo;

    public component createComponent(component componentDto) {
        componentModel model = new componentModel(
            null,
            componentDto.getName(),
            componentDto.getSupplierId(),
            componentDto.getSerialNumber(),
            componentDto.getModel(),
            componentDto.getCost(),
            componentDto.getCreatedAt()
        );
        componentModel saved = componentRepo.save(model);
        return toDto(saved);
    }

    public List<component> getAllComponents() {
        return componentRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private component toDto(componentModel model) {
        return new component(
            model.getComponentId(),
            model.getName(),
            model.getSupplierId(),
            model.getSerialNumber(),
            model.getModel(),
            model.getCost(),
            model.getCreatedAt()
        );
    }
}
