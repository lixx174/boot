package com.boot.admin.Integration;

import com.boot.admin.api.ResourceController;
import com.boot.admin.application.dto.command.ResourceOfferCommand;
import com.boot.admin.domain.enums.ResourceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.UUID;

/**
 * @author Jinx
 */
@SpringBootTest
public class ResourceTest {

    @Autowired
    private ResourceController controller;
    private final Random random = new Random();


    @Test
    public void script(){
        for (int i = 0; i < 1000; i++) {
            ResourceOfferCommand command = new ResourceOfferCommand();

            command.setPid(random.nextInt(1000));
            command.setName(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8));
            command.setPermission(UUID.randomUUID().toString());
            if(random.nextBoolean()){
                command.setType(ResourceType.ROUTE);
                command.setUri(UUID.randomUUID().toString());
                command.setIcon(UUID.randomUUID().toString());
            }else {
                command.setType(ResourceType.BUTTON);
            }

            controller.offer(command);
        }
    }
}
