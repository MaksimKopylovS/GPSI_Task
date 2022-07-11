package com.gspi.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gspi.task.service.ProjectTaskService;

import java.util.*;

/* Добрый день уважаемые разработчики РОСАТОМа
 *  подготовил для Вас решение тестового задания.
 *
 *  Если вас не затруднит, опишите, пожалуйста, допущенные мною ошибки,
 *  буду вам очень признателен.
 * */

public class Application {

    public static void main(String[] args) throws JsonProcessingException {

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

        new ProjectTaskService().creatListProjectTaskModel(jsonRequest).forEach(System.out::println);

        List<HashMap<String, String>> list = Arrays.asList(new ObjectMapper().readValue(jsonRequest, HashMap[].class));
        System.out.println("Метод 1.\nУникальных пользователей - " + new ProjectTaskService().numberOfUniqueEmployees(list));
        System.out.println("Метод 2.\nУникальных пользователей - " + new ProjectTaskService().numberOfUniqueEmployeesTwo(list));
    }
}
