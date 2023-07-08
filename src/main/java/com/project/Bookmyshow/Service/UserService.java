package com.project.Bookmyshow.Service;
import com.project.Bookmyshow.ExceptionHandling.EmailAlreadyExistException;

import com.project.Bookmyshow.Dto.UserDto;
import com.project.Bookmyshow.Repository.UserRepository;
import com.project.Bookmyshow.Transformer.UserTransformer;
import com.project.Bookmyshow.Module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void addUser(UserDto userDto) throws EmailAlreadyExistException{
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        if(userOptional.isPresent())
        {
            throw new EmailAlreadyExistException("Email Id already exist");
        }
        User user = UserTransformer.userDtoToEntity(userDto);
        userRepository.save(user);
    }
}
