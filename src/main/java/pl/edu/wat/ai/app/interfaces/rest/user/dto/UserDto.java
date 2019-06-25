package pl.edu.wat.ai.app.interfaces.rest.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String mail;
    private String limit;

}
