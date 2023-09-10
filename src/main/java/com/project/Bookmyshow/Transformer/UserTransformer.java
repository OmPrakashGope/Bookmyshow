package com.project.Bookmyshow.Transformer;
import com.project.Bookmyshow.Dto.RequestDtos.UserDto;
import com.project.Bookmyshow.Module.User;

public class UserTransformer {
    public static User userDtoToEntity(UserDto userDto) {
        User userObj = User.builder().age(userDto.getAge()).email(userDto.getEmail()).mobileNumber(userDto.getMobileNumber())
                .name(userDto.getName()).gender(userDto.getGender()).build();
        return userObj;
    }
    public static UserDto userToUserDto(User user)
    {
        UserDto userObj = UserDto.builder().age(user.getAge()).email(user.getEmail()).mobileNumber(user.getMobileNumber())
                .name(user.getName()).build();
        return userObj;
    }
}