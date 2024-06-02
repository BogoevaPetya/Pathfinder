package bg.softuni.pathfinder.repositories;

import bg.softuni.pathfinder.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> findById(long randomId);
}
