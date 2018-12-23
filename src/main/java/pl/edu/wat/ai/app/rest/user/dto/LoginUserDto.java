package pl.edu.wat.ai.app.rest.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginUserDto {
    private final String username;
    private final String password;
}
