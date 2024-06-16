package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.Category;
import bg.softuni.pathfinder.model.dtos.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
//j
@Controller
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
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

    @GetMapping("/bicycle")
    private String bicycle(){
        return "bicycle";
    }
}
