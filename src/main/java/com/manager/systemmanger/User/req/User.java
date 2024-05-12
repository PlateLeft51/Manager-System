package com.manager.systemmanger.User.req;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    String userId;

    String accountName;

    String role;

    public static User decode(String base64Encoded) {
        try {
            String json = new String(Base64.getDecoder().decode(base64Encoded), StandardCharsets.UTF_8);
            return new ObjectMapper().readValue(json, User.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decode header", e);
        }
    }
}
