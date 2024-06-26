package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.Category;
import bg.softuni.pathfinder.model.dtos.AddRouteDTO;
import bg.softuni.pathfinder.model.dtos.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
//j
@Controller
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ModelAttribute
    public AddRouteDTO addRouteDTO(){
        return new AddRouteDTO();
    }

    @GetMapping("/routes")
    public String routes(Model model){

        List<RouteShortInfoDTO> routes = routeService.getAll();
        model.addAttribute("allRoutes", routes);

        return "routes";
    }

    @GetMapping("/add-route")
    public String addRoute(Model model){
        model.addAttribute("levels", Level.values());
        model.addAttribute("categoryTypes", CategoryType.values());
        model.addAttribute("route", new RouteShortInfoDTO());
        return "add-route";
    }

    @PostMapping("/add-route")
    public String doAddRoute(
            @Valid AddRouteDTO addRouteDTO,
            @RequestParam("gpxCoordinates") MultipartFile file,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws IOException {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addRouteDTO", addRouteDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addRouteDTO", bindingResult);
            return "redirect:/add-route";
        }

        routeService.add(addRouteDTO, file);

        return "redirect:/routes";
    }

    @GetMapping("/bicycle")
    private String bicycle(){
        return "bicycle";
    }
}
