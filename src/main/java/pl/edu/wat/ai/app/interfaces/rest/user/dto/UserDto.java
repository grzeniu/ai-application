package pl.edu.wat.ai.app.interfaces.rest.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String mail;
}
