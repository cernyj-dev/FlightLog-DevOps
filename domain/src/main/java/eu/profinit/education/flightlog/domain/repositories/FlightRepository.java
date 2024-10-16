package eu.profinit.education.flightlog.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import eu.profinit.education.flightlog.domain.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByFlightType(Flight.Type flightType);

    List<Flight> findAllByLandingTimeNullOrderByTakeoffTimeAscIdAsc();


    /**
     * It's expected that this query will be used to populate
     * a list of BuildingBlockSummaryTo instances.
     */
    @EntityGraph(
        type = EntityGraph.EntityGraphType.LOAD,
        attributePaths = {"pilot", "copilot", "gliderFlight", "gliderFlight.pilot", "gliderFlight.copilot"}
    )
    List<Flight> findAllByLandingTimeNotNullAndFlightTypeOrderByTakeoffTimeDescIdAsc(Flight.Type flightType, Pageable pageable);
}

