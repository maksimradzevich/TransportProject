package transportproject.transportwebsite.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportproject.transportwebsite.dao.TransportDAO;
import transportproject.transportwebsite.model.transport.Transport;
import transportproject.transportwebsite.model.transport.TransportType;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("transportDAO")
public class TransportDAOImpl implements TransportDAO {

    private static final String TYPE_FIELD = "type";
    private static final String ROUTE_NUMBER_FIELD = "routeNumber";
    private final SessionFactory sessionFactory;

    @Autowired
    public TransportDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria createEntityCriteria() {
        return getSession().createCriteria(Transport.class);
    }

    @Override
    public List<Transport> findAllTransport() {
        return (List<Transport>) createEntityCriteria().list();
    }

    @Override
    public List<Transport> findTransportByType(TransportType transportType) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(TYPE_FIELD, transportType));
        return criteria.list();
    }

    @Override
    public Transport findTransportByRouteNumberAndTypeWithRoutes(int routeNumber, TransportType transportType) {
        final Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(TYPE_FIELD, transportType));
        criteria.add(Restrictions.eq(ROUTE_NUMBER_FIELD, routeNumber));
        final Transport transport = (Transport) criteria.uniqueResult();
        Hibernate.initialize(transport.getRoutes());
        return transport;
    }
}
