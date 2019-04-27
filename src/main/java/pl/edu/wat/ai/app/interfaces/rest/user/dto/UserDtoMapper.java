package pl.edu.wat.ai.app.interfaces.rest.user.dto;

import pl.edu.wat.ai.app.user.User;

public class UserDtoMapper {

    public static UserDto mapUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail(user.getMail())
                .limit(user.getUserMonthlyLimit())
                .build();
    }
}
