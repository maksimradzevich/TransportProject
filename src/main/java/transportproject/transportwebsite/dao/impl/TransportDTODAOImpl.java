package transportproject.transportwebsite.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dto.TransportDTO;
import transportproject.transportwebsite.dto.TransportType;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("transportDAO")
public class TransportDTODAOImpl implements TransportDTODAO {

    private static final String TYPE_FIELD = "type";
    private static final String ROUTE_NUMBER_FIELD = "routeNumber";
    private final SessionFactory sessionFactory;

    @Autowired
    public TransportDTODAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria createEntityCriteria() {
        return getSession().createCriteria(TransportDTO.class);
    }

    @Override
    public List<TransportDTO> findAllTransport() {
        return (List<TransportDTO>) createEntityCriteria().list();
    }

    @Override
    public List<TransportDTO> findTransportByType(TransportType transportType) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(TYPE_FIELD, transportType));
        criteria.addOrder(Order.asc(ROUTE_NUMBER_FIELD));
        return criteria.list();
    }

    @Override
    public TransportDTO findTransportByRouteNumberAndTypeWithRoutes(int routeNumber, TransportType transportType) {
        final Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(TYPE_FIELD, transportType));
        criteria.add(Restrictions.eq(ROUTE_NUMBER_FIELD, routeNumber));
        final TransportDTO transportDTO = (TransportDTO) criteria.uniqueResult();
        Hibernate.initialize(transportDTO.getRouteDTOS());
        return transportDTO;
    }

    @Override
    public TransportDTO findTransportByRouteNumberAndType(int routeNumber, TransportType transportType) {
        final Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(TYPE_FIELD, transportType));
        criteria.add(Restrictions.eq(ROUTE_NUMBER_FIELD, routeNumber));
        final TransportDTO transportDTO = (TransportDTO) criteria.uniqueResult();
        return transportDTO;
    }

    @Override
    public TransportDTO findById(int id) {
        return (TransportDTO) getSession().get(TransportDTO.class, id);
    }
}
