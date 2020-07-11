package com.ahoubouby.brs.service;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */

import com.ahoubouby.brs.dto.mapper.TicketMapper;
import com.ahoubouby.brs.dto.mapper.TripMapper;
import com.ahoubouby.brs.dto.mapper.TripScheduleMapper;
import com.ahoubouby.brs.dto.model.*;
import com.ahoubouby.brs.model.*;
import com.ahoubouby.brs.repository.*;
import com.ahoubouby.brs.utils.RandomStringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.ahoubouby.brs.exception.BRSException.*;
import static com.ahoubouby.brs.exception.BRSException.throwException;
import static com.ahoubouby.brs.exception.EntityType.*;
import static com.ahoubouby.brs.exception.ExceptionType.*;

@Service
public class BusReservationServiceImpl implements BusReservationService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripScheduleRepository tripScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<StopDto> getAllStops() {
        return stopRepository.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, StopDto.class))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public StopDto getStopByCode(String stopCode) {
        Optional<Stop> stop = Optional.of(stopRepository.findByCode(stopCode));
        return modelMapper.map(stop.get(), StopDto.class);
    }

    @Override
    public AgencyDto getAgency(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            Optional<Agency> agency = Optional.ofNullable(agencyRepository.findByOwner(user));
            if (agency.isPresent()) {
                return modelMapper.map(agency.get(), AgencyDto.class);
            }
            throw throwExceptionWithId(AGENCY, ENTITY_NOT_FOUND, "2", user.getEmail());
        }
        throw throwException(USER, ENTITY_NOT_FOUND, userDto.getEmail());

    }

    @Override
    public AgencyDto addAgency(AgencyDto agencyDto) {
        User admin = userRepository.findByEmail(agencyDto.getOwner().getEmail());
        if (admin != null) {
            Optional<Agency> agency = Optional.ofNullable(agencyRepository.findByName(agencyDto.getName()));
            if (!agency.isPresent()) {
                Agency agencyModel = new Agency()
                        .setName(agencyDto.getName())
                        .setDetails(agencyDto.getDetails())
                        .setCode(RandomStringUtil.getAlphaNumericString(8, agencyDto.getName()))
                        .setOwner(admin);
                agencyRepository.save(agencyModel);
                return modelMapper.map(agencyModel, AgencyDto.class);
            }
            throw throwException(AGENCY, DUPLICATE_ENTITY, agencyDto.getName());
        }
        throw throwException(USER, ENTITY_NOT_FOUND, agencyDto.getOwner().getEmail());
    }

    @Override
    @Transactional
    public AgencyDto updateAgency(AgencyDto agencyDto, BusDto busDto) {
        Agency agency = agencyRepository.findByCode(agencyDto.getCode());
        if (agency != null) {
            if (busDto != null) {
                Optional<Bus> bus = Optional.ofNullable(busRepository.findByCodeAndAgency(busDto.getCode(), agency));
                if (!bus.isPresent()) {
                    Bus busModel = new Bus()
                            .setAgency(agency)
                            .setCode(busDto.getCode())
                            .setCapacity(busDto.getCapacity())
                            .setMake(busDto.getMake());
                    busRepository.save(busModel);
                    if (agency.getBuses() == null) {
                        agency.setBuses(new HashSet<>());
                    }
                    agency.getBuses().add(busModel);
                    return modelMapper.map(agencyRepository.save(agency), AgencyDto.class);
                }
                throw throwExceptionWithId(BUS, DUPLICATE_ENTITY, "2", busDto.getCode(), agencyDto.getCode());
            } else {
                //update agency details case
                agency.setName(agencyDto.getName())
                        .setDetails(agencyDto.getDetails());
                return modelMapper.map(agencyRepository.save(agency), AgencyDto.class);
            }
        }
        throw throwExceptionWithId(AGENCY, ENTITY_NOT_FOUND, "2", agencyDto.getOwner().getEmail());
    }

    @Override
    public TripDto getTripById(String tripID) {
        Optional<Trip> trip = tripRepository.findById(tripID);
        if (trip.isPresent()) {
            return TripMapper.toTripDto(trip.get());
        }
        throw throwException(TRIP, ENTITY_NOT_FOUND, tripID);
    }

    @Override
    public List<TripDto> addTrip(TripDto tripDto) {
        Stop sourceStop = stopRepository.findByCode(tripDto.getSourceStopCode());
        if (sourceStop != null) {
            Stop destinationStop = stopRepository.findByCode(tripDto.getDestinationStopCode());
            if (destinationStop != null) {
                if (!sourceStop.getCode().equalsIgnoreCase(destinationStop.getCode())) {
                    Agency agency = agencyRepository.findByCode(tripDto.getAgencyCode());
                    if (agency != null) {
                        Bus bus = busRepository.findByCode(tripDto.getBusCode());
                        if (bus != null) {
                            //Each new trip creation results in a to and a fro trip
                            List<TripDto> trips = new ArrayList<>(2);
                            Trip toTrip = new Trip()
                                    .setSourceStop(sourceStop)
                                    .setDestStop(destinationStop)
                                    .setAgency(agency)
                                    .setBus(bus)
                                    .setJourneyTime(tripDto.getJourneyTime())
                                    .setFare(tripDto.getFare());
                            trips.add(TripMapper.toTripDto(tripRepository.save(toTrip)));

                            Trip froTrip = new Trip()
                                    .setSourceStop(destinationStop)
                                    .setDestStop(sourceStop)
                                    .setAgency(agency)
                                    .setBus(bus)
                                    .setJourneyTime(tripDto.getJourneyTime())
                                    .setFare(tripDto.getFare());
                            trips.add(TripMapper.toTripDto(tripRepository.save(froTrip)));
                            return trips;
                        }
                        throw throwException(BUS, ENTITY_NOT_FOUND, tripDto.getBusCode());
                    }
                    throw throwException(AGENCY, ENTITY_NOT_FOUND, tripDto.getAgencyCode());
                }
                throw throwException(TRIP, ENTITY_EXCEPTION, "");
            }
            throw throwException(STOP, ENTITY_NOT_FOUND, tripDto.getDestinationStopCode());
        }
        throw throwException(STOP, ENTITY_NOT_FOUND, tripDto.getSourceStopCode());
    }

    @Override
    public List<TripDto> getAgencyTrips(String agencyCode) {
        Agency agency = agencyRepository.findByCode(agencyCode);
        if (agency != null) {
            List<Trip> agencyTrips = tripRepository.findByAgency(agency);
            if (!agencyTrips.isEmpty()) {
                return agencyTrips
                        .stream()
                        .map(TripMapper::toTripDto)
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        }
        throw throwException(AGENCY, ENTITY_NOT_FOUND, agencyCode);
    }

    @Override
    public List<TripDto> getAvailableTripsBetweenStops(String sourceStopCode, String destinationStopCode) {
        List<Trip> availableTrips = findTripsBetweenStops(sourceStopCode, destinationStopCode);
        if (!availableTrips.isEmpty()) {
            return availableTrips
                    .stream()
                    .map(TripMapper::toTripDto)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<TripScheduleDto> getAvailableTripSchedules(String sourceStopCode, String destinationStopCode, String tripDate) {
        List<Trip> availableTrips = findTripsBetweenStops(sourceStopCode, destinationStopCode);
        if (!availableTrips.isEmpty()) {
            return availableTrips
                    .stream()
                    .map(trip -> tripScheduleRepository.findByTripDetailAndTripDate(trip, tripDate))
                    .map(TripScheduleMapper::toTripScheduleDto)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public TripScheduleDto getTripSchedule(TripDto tripDto, String tripDate, boolean createSchedForTrip) {
        Optional<Trip> trip = tripRepository.findById(tripDto.getId());
        if (trip.isPresent()) {
            Optional<TripSchedule> tripSchedule = Optional.ofNullable(tripScheduleRepository.findByTripDetailAndTripDate(trip.get(), tripDate));
            if (tripSchedule.isPresent()) {
                return TripScheduleMapper.toTripScheduleDto(tripSchedule.get());
            } else {
                if (createSchedForTrip) { //create the schedule
                    TripSchedule tripSchedule1 = new TripSchedule()
                            .setTripDetail(trip.get())
                            .setTripDate(tripDate)
                            .setAvailableSeats(trip.get().getBus().getCapacity());
                    return TripScheduleMapper.toTripScheduleDto(tripScheduleRepository.save(tripSchedule1));
                } else {
                    throw throwExceptionWithId(TRIP, ENTITY_NOT_FOUND, "2", tripDto.getId(), tripDate);
                }
            }

        }
        throw throwException(TRIP, ENTITY_NOT_FOUND, tripDto.getId());
    }

    @Override
    @Transactional
    public TicketDto bookTicket(TripScheduleDto tripScheduleDto, UserDto passenger) {
        User user = userRepository.findByEmail(passenger.getEmail());
        if (user != null) {
            Optional<TripSchedule> tripSchedule = tripScheduleRepository.findById(tripScheduleDto.getId());
            if (tripSchedule.isPresent()) {
                Ticket ticket = new Ticket()
                        .setCancellable(false)
                        .setJourneyDate(tripSchedule.get().getTripDate())
                        .setPassenger(user)
                        .setTripSchedule(tripSchedule.get())
                        .setSeatNumber(tripSchedule.get().getTripDetail().getBus().getCapacity() - tripSchedule.get().getAvailableSeats());
                ticketRepository.save(ticket);
                tripSchedule.get().setAvailableSeats(tripSchedule.get().getAvailableSeats() - 1); //reduce availability by 1
                tripScheduleRepository.save(tripSchedule.get());//update schedule
                return TicketMapper.toTicketDto(ticket);
            }
            throw throwException(TRIP, ENTITY_NOT_FOUND, "2", tripScheduleDto.getTripId(), tripScheduleDto.getTripDate());
        }
        throw throwException(USER, ENTITY_NOT_FOUND, passenger.getEmail());
    }

    private List<Trip> findTripsBetweenStops(String sourceStopCode, String destinationStopCode) {
        Optional<Stop> sourceStop = Optional
                .ofNullable(stopRepository.findByCode(sourceStopCode));
        if (sourceStop.isPresent()) {
            Optional<Stop> destStop = Optional
                    .ofNullable(stopRepository.findByCode(destinationStopCode));
            if (destStop.isPresent()) {
                List<Trip> availableTrips = tripRepository.findAllBySourceStopAndDestStop(sourceStop.get(), destStop.get());
                if (!availableTrips.isEmpty()) {
                    return availableTrips;
                }
                return Collections.emptyList();
            }
            throw throwException(STOP, ENTITY_NOT_FOUND, destinationStopCode);
        }
        throw throwException(STOP, ENTITY_NOT_FOUND, sourceStopCode);
    }
}
