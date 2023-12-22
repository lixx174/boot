package com.boot.authorization.application;

import com.boot.authorization.application.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Jinx
 */
@Getter
@Setter
public class HomeDto {

    private String id = UUID.randomUUID().toString();

    private String name;

    @JsonView(Views.Detail.class)
    private Integer age;

    private LocalDateTime createAt = LocalDateTime.now();
}
