package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.dtos.AddRouteDTO;
import bg.softuni.pathfinder.model.dtos.RouteShortInfoDTO;
import bg.softuni.pathfinder.repositories.RouteRepository;
import bg.softuni.pathfinder.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private Random random;
    private ModelMapper modelMapper;

    public RouteService(RouteRepository routeRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
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

    public boolean add(AddRouteDTO addRouteDTO, MultipartFile gpxFile) throws IOException {
        Route toInsert = modelMapper.map(addRouteDTO, Route.class);

        Path destinationFile = Paths
                .get("src", "main","resources", "uploads", "file.gpx")
                .normalize()
                .toAbsolutePath();

        try (InputStream inputStream = gpxFile.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        }


        // originalFilename, fileLocation ->  /uploads/{userId}/{fileId}.{ext}

        return false;
    }
}
