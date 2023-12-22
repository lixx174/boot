package com.boot.authorization.api;

import com.boot.authorization.application.HomeEvent;
import com.boot.authorization.application.view.Views;
import com.boot.authorization.infra.config.JacksonConfig;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
public class IndexController {

    private final ObjectMapper objectMapper;

    @PostMapping("test")
    @SneakyThrows
    @JsonView(Views.Detail.class)
    public ResponseEntity<String> index(@RequestBody HomeEvent event) {
        return ResponseEntity.ok(objectMapper.writeValueAsString(event));
    }
}
