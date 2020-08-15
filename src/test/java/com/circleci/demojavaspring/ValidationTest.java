package com.circleci.demojavaspring;

import com.circleci.demojavaspring.service.ValidationService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ValidationTest {

    private ValidationService validationService;

    @Before
    public void setUp() {
        validationService = new ValidationService();
    }

    @Test
    public void Listの要素が１つ() {
        List<String> list = Arrays.asList("1");
        assertFalse(validationService.hasTwoOrThree(list));
    }

    @Test
    public void Listの要素が２つ() {
        List<String> list = Arrays.asList("1", "2");
        assertTrue(validationService.hasTwoOrThree(list));
    }

    @Test
    public void Listの要素が３つ() {
        List<String> list = Arrays.asList("1", "2", "3");
        assertTrue(validationService.hasTwoOrThree(list));
    }



}
