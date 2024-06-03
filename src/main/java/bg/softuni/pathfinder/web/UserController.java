package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dtos.UserRegisterDTO;
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

    @GetMapping("/users/register")
    public String viewRegister(Model model){
        model.addAttribute("registerData", new UserRegisterDTO());
        //или горното може да се замени с @ModelAttribute, закоментирано по-долу
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
            return "regiser";
        }

        return "redirect:/users/login ";
    }

    @GetMapping("/users/login")
    public String viewLogin(){
        return "login";
    }
}
