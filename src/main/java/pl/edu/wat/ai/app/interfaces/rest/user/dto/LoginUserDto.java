package pl.edu.wat.ai.app.interfaces.rest.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class LoginUserDto {

    @NotNull
    private final String username;

    @NotNull
    private final String password;
}
