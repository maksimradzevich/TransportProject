package transportproject.transportwebsite.dao.impl;

import org.hibernate.Criteria;
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
        criteria.add(Restrictions.eq("type", transportType));
        return criteria.list();
    }
}
