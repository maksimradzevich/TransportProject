package transportproject.transportwebsite.business.user;

import org.hibernate.Hibernate;
import org.springframework.data.repository.CrudRepository;
import transportproject.transportwebsite.business.stop.Stop;
import transportproject.transportwebsite.business.transport.Transport;
import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dao.UserDTODAO;
import transportproject.transportwebsite.dto.FavoriteItem;
import transportproject.transportwebsite.dto.StopDTO;
import transportproject.transportwebsite.dto.TransportDTO;
import transportproject.transportwebsite.dto.UserDTO;
import transportproject.transportwebsite.service.exceptions.NotFoundException;
import transportproject.transportwebsite.service.exceptions.UserExistsException;

import java.util.List;
import java.util.stream.Collectors;

import static transportproject.transportwebsite.dto.FavoriteItemTypes.STOP;
import static transportproject.transportwebsite.dto.FavoriteItemTypes.TRANSPORT;

public class UserWithFavoritesImpl implements UserWithFavorites {
    private final User user;
    private final StopDTODAO stopRepository;
    private final TransportDTODAO transportRepository;

    public UserWithFavoritesImpl(User user, StopDTODAO stopRepository, TransportDTODAO transportRepository) {
        this.user = user;
        this.stopRepository = stopRepository;
        this.transportRepository = transportRepository;
    }

    @Override
    public boolean isInFavorites(Stop stop) {
        final List<Stop> favoriteStops = getFavoriteStops();
        return favoriteStops.contains(stop);
    }

    private List<Stop> getFavoriteStops() {
        final List<StopDTO> favoriteStopDTOS = user.getInnerState().getFavoriteStopDTOS();
        Hibernate.initialize(favoriteStopDTOS);
        return favoriteStopDTOS.stream().map(stopDTO -> new Stop(stopDTO)).collect(Collectors.toList());
    }

    @Override
    public boolean isInFavorites(Transport transport) {
        final List<Transport> transports = getFavoriteTransports();
        return transports.contains(transport);
    }

    private List<Transport> getFavoriteTransports() {
        final List<TransportDTO> favoriteTransportDTO = user.getInnerState().getFavoriteTransportDTO();
        Hibernate.initialize(favoriteTransportDTO);
        return favoriteTransportDTO.stream().map(transportDTO -> new Transport(transportDTO)).collect(Collectors.toList());
    }

    @Override
    public void removeFavorite(Stop stop) {
        final List<StopDTO> favoriteStopDTOS = user.getInnerState().getFavoriteStopDTOS();
        Hibernate.initialize(favoriteStopDTOS);
        favoriteStopDTOS.remove(stop.getDatabaseIndex());
        user.save();
    }

    @Override
    public void removeFavorite(Transport transport) {
        final List<TransportDTO> favoriteTransportDTO = user.getInnerState().getFavoriteTransportDTO();
        Hibernate.initialize(favoriteTransportDTO);
        favoriteTransportDTO.remove(transport.getDatabaseIndex());
        user.save();
    }

    @Override
    public void addFavorite(Transport transport) {
        final List<TransportDTO> favoriteTransportDTO = user.getInnerState().getFavoriteTransportDTO();
        Hibernate.initialize(favoriteTransportDTO);
        favoriteTransportDTO.add(transport.getInnerState());
        user.save();
    }

    @Override
    public void addFavorite(Stop stop) {
        final List<StopDTO> stopDTOS = user.getInnerState().getFavoriteStopDTOS();
        Hibernate.initialize(stopDTOS);
        stopDTOS.add(stop.getInnerState());
        user.save();
    }

    @Override
    public void addFavorite(FavoriteItem item) throws NotFoundException {
        if (item.isTypeEquals(STOP)) {
            final Stop stop = new Stop(stopRepository.findOne(item.getId()));
            addFavorite(stop);
        } else if (item.isTypeEquals(TRANSPORT)) {
            final Transport transport = new Transport(transportRepository.findById(item.getId()));
            addFavorite(transport);
        }
    }

    @Override
    public void removeFavorite(FavoriteItem item) throws NotFoundException {
        if (item.isTypeEquals(STOP)) {
            final Stop stop = new Stop(stopRepository.findOne(item.getId()));
            removeFavorite(stop);
        } else if (item.isTypeEquals(TRANSPORT)) {
            final Transport transport = new Transport(transportRepository.findById(item.getId()));
            removeFavorite(transport);
        }
    }


    @Override
    public void save() {
        user.save();
    }

    @Override
    public void register() throws UserExistsException {
        user.register();
    }

    @Override
    public UserDTO getInnerState() {
        return user.getInnerState();
    }
}