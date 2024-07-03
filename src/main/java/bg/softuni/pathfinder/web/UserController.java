package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dtos.UserLoginDTO;
import bg.softuni.pathfinder.model.dtos.UserRegisterDTO;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserLoginDTO userLoginDTO(){
        return new UserLoginDTO();
    }

    @GetMapping("/users/register")
    public String viewRegister(Model model){
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        //или горното може да се замени с @ModelAttribute, закоментирано по-долу
        model.addAttribute("levels", Level.values());
        return "register";
    }

//    @ModelAttribute("registerData")
//    public UserRegisterDTO initUserRegisterDTO(){
//        return new UserRegisterDTO();
//    }

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterDTO", bindingResult);
            return "register";
        }

        this.userService.register(userRegisterDTO);
        return "redirect:/users/login ";
    }

    @GetMapping("/users/login")
    public String viewLogin(Model model){
        model.addAttribute("userLoginDTO", new UserLoginDTO());
        return "login";
    }

    @GetMapping("users/profile")
    public String profile(Model model){
        model.addAttribute("userProfile",  userService.getProfileData());
        return "profile";
    }
}
