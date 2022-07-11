package com.gspi.task.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gspi.task.exception.InvalidInputException;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@JsonAutoDetect
@NoArgsConstructor
public class ProjectTask {

    private static final String TASK_STAGE_ACTIVE = "active";
    private static final String TASK_STAGE_DISABLE = "disabled";

    private String taskId;
    private String assingneeId;
    private String taskStage;

    public ProjectTask(@JsonProperty(value = "task_id") @NonNull String taskId,
                       @JsonProperty(value = "assignee_id") String assingneeId,
                       @JsonProperty(value = "task_state") @NotNull String taskStage) {
        if (taskId.equals("")) {
            try {
                throw new InvalidInputException("Поле taskId не может быть пустым");
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
        } else {
            this.taskId = taskId;
        }

        this.assingneeId = assingneeId;


        if (taskStage.equals(TASK_STAGE_ACTIVE) || taskStage.equals(TASK_STAGE_DISABLE)) {
            this.taskStage = taskStage;
        } else {
            try {
                throw new InvalidInputException("Поле taskStage может иметь значения только active/disabled");
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
        }
    }
}
