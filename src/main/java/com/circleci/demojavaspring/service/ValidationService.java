package com.circleci.demojavaspring.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    public boolean hasTwoOrThree(List<String> list) {
        return list.size() == 2 || list.size() == 3;
    }
}
