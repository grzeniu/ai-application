package pl.edu.wat.ai.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.NewUserDto;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.UserDto;
import pl.edu.wat.ai.app.user.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "limit", source = "user.userMonthlyLimit")
    UserDto userToDto(User user);

    @Mapping(target = "limit", source = "user.userMonthlyLimit")
    User newUserToUser(NewUserDto newUserDto, BCryptPasswordEncoder encoder);

}
