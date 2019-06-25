package pl.edu.wat.ai.app.mappers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.NewUserDto;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.UserDto;
import pl.edu.wat.ai.app.user.User;

public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLimit(user.getUserMonthlyLimit());
        userDto.setMail(user.getMail());

        return userDto;
    }

    @Override
    public User newUserToUser(NewUserDto newUserDto, BCryptPasswordEncoder encoder) {

        User user = new User();
        user.setUsername(newUserDto.getUsername());
        user.setFirstName(newUserDto.getFirstname());
        user.setLastName(newUserDto.getLastname());
        user.setPassword(encoder.encode(newUserDto.getPassword()));
        user.setMail(newUserDto.getMail());
        user.setUserMonthlyLimit("");
        return user;
    }



}
