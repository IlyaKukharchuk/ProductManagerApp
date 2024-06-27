package com.manager.managerApp.services;

import com.manager.managerApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultProductServiceImpl {
    private final ProductRepository ProductRepository;


}
