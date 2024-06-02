package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.dtos.RouteShortInfoDTO;
import bg.softuni.pathfinder.repositories.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private Random random;
    private ModelMapper modelMapper;

    public RouteService(RouteRepository routeRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.random = new Random();
    }

    @Transactional
    public RouteShortInfoDTO getRandomRoute(){
        //Request from DB
        long routeCount = routeRepository.count();
        long randomId = random.nextLong(routeCount) + 1;

        Optional<Route> byId = routeRepository.findById(randomId);

        if (byId.isEmpty()){

        }

        //Map to DTO
        Route route = byId.get();

        return mapToShortInfo(route);
    }

    @Transactional
    public List<RouteShortInfoDTO> getAll(){
        return routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfo)
                .collect(Collectors.toList());
    }

    private RouteShortInfoDTO mapToShortInfo(Route route){
        RouteShortInfoDTO dto = modelMapper.map(route, RouteShortInfoDTO.class);

        Optional<Picture> picture = route.getPictures().stream().findFirst();
        dto.setImageUrl(picture.get().getUrl());

        return dto;
    }
}
