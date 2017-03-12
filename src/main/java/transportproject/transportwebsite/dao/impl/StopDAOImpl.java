package transportproject.transportwebsite.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportproject.transportwebsite.dao.StopDAO;
import transportproject.transportwebsite.model.Stop;
import transportproject.transportwebsite.model.transport.Transport;

import javax.transaction.Transactional;
import java.util.List;

@Repository("stopDAO")
@Transactional
public class StopDAOImpl implements StopDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public StopDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria createEntityCriteria() {
        return getSession().createCriteria(Stop.class);
    }

    @Override
    public List<Stop> findAllStops() {
        return createEntityCriteria().list();
    }

    @Override
    public List<Stop> findAllStopsSorted() {
        final Criteria entityCriteria = createEntityCriteria();
        entityCriteria.addOrder(Order.asc("name"));
        return entityCriteria.list();
    }
}
