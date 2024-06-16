package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dtos.UserLoginDTO;
import bg.softuni.pathfinder.model.dtos.UserProfileDTO;
import bg.softuni.pathfinder.model.dtos.UserRegisterDTO;
import bg.softuni.pathfinder.repositories.UserRepository;
import bg.softuni.pathfinder.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public void register(UserRegisterDTO userRegisterDTO){
        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(user);
    }


    public void login(UserLoginDTO userLoginDTO) {
        User user = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if (user == null){
            throw new RuntimeException("Username not found!");
        }

        if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()) && !currentUser.isLoggedIn()){
            currentUser.setUser(user);
            return;
        }

        throw new RuntimeException("Password not valid.");
    }

    public void logout() {
        currentUser.setUser(null);
    }

    public UserProfileDTO getProfileData(){
        return modelMapper.map(currentUser.getUser(), UserProfileDTO.class);
    }
}
