package com.gspi.task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


class ProjectTaskServiceTest {

    @Test
    void numberOfUniqueEmployees() throws JsonProcessingException {

        String jsonRequest = "\n" +
                "[{\n" +
                "    \"task_id\": \"1\",\n" +
                "    \"assignee_id\": \"001\",\n" +
                "    \"task_state\": \"active\"\n" +
                "},\n" +
                "    {\n" +
                "      \"task_id\": \"2\",\n" +
                "      \"assignee_id\": \"002\",\n" +
                "      \"task_state\": \"active\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"task_id\": \"3\",\n" +
                "      \"assignee_id\": \"001\",\n" +
                "      \"task_state\": \"active\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"task_id\": \"4\",\n" +
                "      \"assignee_id\": \"007\",\n" +
                "      \"task_state\": \"disabled\"\n" +
                "}]";

        List<HashMap<String,String>> list = Arrays.asList(new ObjectMapper().readValue(jsonRequest, HashMap[].class));
        /* Тестируем вариант 1*/
        Assertions.assertEquals(2L, new ProjectTaskService().numberOfUniqueEmployees(list));
        /* Тестируем вариант 2*/
        Assertions.assertEquals(2, new ProjectTaskService().numberOfUniqueEmployeesTwo(list));
    }
}