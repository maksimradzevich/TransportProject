package transportproject.transportwebsite.dao;

import org.springframework.data.repository.CrudRepository;
import transportproject.transportwebsite.dto.StopDTO;

import java.util.List;

public interface StopDTODAO extends CrudRepository<StopDTO, Integer> {
    List<StopDTO> findAllByOrderByNameAsc();
}
