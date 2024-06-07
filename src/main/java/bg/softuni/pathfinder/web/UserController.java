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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String viewRegister(Model model){
        model.addAttribute("registerData", new UserRegisterDTO());
        //или горното може да се замени с @ModelAttribute, закоментирано по-долу
        model.addAttribute("levels", Level.values());
        return "register";
    }

//    @ModelAttribute("registerData")
//    public UserRegisterDTO initUserRegisterDTO(){
//        return new UserRegisterDTO();
//    }

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterDTO", bindingResult);
            return "redirect:/users/register";
        }

        this.userService.register(data);
        return "redirect:/users/login ";
    }

    @GetMapping("/users/login")
    public String viewLogin(){
        return "login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginDTO userLoginDTO){
        return "login";
    }
}
