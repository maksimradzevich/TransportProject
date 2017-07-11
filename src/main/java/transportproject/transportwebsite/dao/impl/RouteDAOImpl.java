package transportproject.transportwebsite.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportproject.transportwebsite.dao.RouteDAO;
import transportproject.transportwebsite.model.transport.Route;
import transportproject.transportwebsite.model.transport.TransportType;

import javax.transaction.Transactional;
import java.util.List;

@Repository("routeDAO")
@Transactional
public class RouteDAOImpl  {

    private final SessionFactory sessionFactory;

    @Autowired
    public RouteDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria createEntityCriteria() {
        return getSession().createCriteria(Route.class);
    }


    @Override
    public List<Route> getRoutesByRouteNumberAndTransportType(Integer routeNumber, TransportType transportType) {

        final Criteria entityCriteria = createEntityCriteria();
        entityCriteria.createAlias("transport", "t")
                .add(Restrictions.eq("t.routeNumber", routeNumber))
                .add(Restrictions.eq("t.type", transportType));
        final List<Route> list = (List<Route>) entityCriteria.list();
        return list;
    }
}
