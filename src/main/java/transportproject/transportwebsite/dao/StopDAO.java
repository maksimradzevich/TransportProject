package transportproject.transportwebsite.dao;

import org.springframework.data.repository.CrudRepository;
import transportproject.transportwebsite.model.transport.Stop;

import java.util.List;

public interface StopDAO extends CrudRepository<Stop, Integer> {
    List<Stop> findAllByOrderByNameAsc();
}
