package pl.edu.wat.ai.app.rest.dto;

import pl.edu.wat.ai.app.user.User;

public class UserDtoMapper {

    public  static UserDto mapUserToDto(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail(user.getMail())
                .build();
    }
}
