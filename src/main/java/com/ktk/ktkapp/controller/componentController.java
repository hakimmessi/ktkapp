package com.ktk.ktkapp.controller;

import com.ktk.ktkapp.dto.component.component;
import com.ktk.ktkapp.dto.component.responses.componentResponse;
import com.ktk.ktkapp.service.componentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/components")
public class componentController {

    @Autowired
    private componentService componentService;

    @PostMapping("/add")
    public componentResponse addComponent(@RequestBody component componentDto) {
        return componentService.createComponent(componentDto);
    }

    @GetMapping
    public List<componentResponse> getAllComponents() {
        return componentService.getAllComponents();
    }
}
