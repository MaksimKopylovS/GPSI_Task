package com.gspi.task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gspi.task.model.ProjectTask;

import java.util.*;

public class ProjectTaskService {

    private static final String TASK_STAGE_ACTIVE = "active";
    private static final String TASK_STAGE = "task_state";
    private static final String ASSIGNEE_ID = "assignee_id";

    /*  Метод принимает на вход список HashMap<String, String>
     *   Возвращает количество уникальных сотрудников назначенных на активные задачи
     */

    /* Вариант один StreamApi*/
    public long numberOfUniqueEmployees(List<HashMap<String, String>> argument) {
        /* Создаём Set для фильтра уникальных пользователей */
        Set<String> set = new HashSet<>();
        return argument.stream()
                /* Фильтруем активных пользователей */
                .filter(map1 -> Objects.equals(TASK_STAGE_ACTIVE, map1.get(TASK_STAGE)))
                /* Фильтруем уникальных пользователей */
                .filter(map1 -> set.add(map1.get(ASSIGNEE_ID)))
                /* Возвращаем количество*/
                .count();
    }

    /* Вариант два, до Java 8*/
    public int numberOfUniqueEmployeesTwo(List<HashMap<String, String>> argument) {
        /* Создаём список которых будет хранить в себе только сотрудников с активными задачами */
        List<Map<String, String>> mapList = new ArrayList<>();

        for (HashMap<String, String> map : argument) {
            /* Если поле task_state в состоянии активно, добавляем map в список mapList */
            if (map.get(TASK_STAGE).equals(TASK_STAGE_ACTIVE)) {
                mapList.add(map);
            }
        }

        /* Создаём список которых будет хранить в себе только уникальных сотрудников*/
        List<Map<String, String>> mapListTry = new ArrayList<>();

        /* Флаг для проверки уникальности сотрудника*/
        boolean flag = true;

        /* Проходим по списку (mapList) с активными задачами*/
        for (Map<String, String> map : mapList) {

            /* Проходим по списку который (mapListTry) будет хранить только уникальные задачи */
            for (Map<String, String> mapE : mapListTry) {

                /* Проверяем если в mapListTry содержится пользователь с
                 * таким же ID соответственно он не уникальный устанавливаем флаг в false */
                if (mapE.get(ASSIGNEE_ID).equals(map.get(ASSIGNEE_ID))) {
                    flag = false;
                    break;
                }
            }
            /* Если пользователь уникальный flag останется в try, добавляем такую MAP в список mapListTry */
            if (flag) {
                mapListTry.add(map);
            }
            flag = true;
        }
        return mapListTry.size();
    }

    public List<ProjectTask> creatListProjectTaskModel(String json) throws JsonProcessingException {
        return Arrays
                .asList(new ObjectMapper()
                        .readValue(json, ProjectTask[].class));
    }
}
