package pl.edu.wat.ai.app.interfaces.rest.user.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class NewUserDto {

    @NotNull
    private final String username;

    @NotNull
    private final String password;

    @NotNull
    private final String firstname;

    @NotNull
    private final String lastname;

    @NotNull
    private final String mail;
}
