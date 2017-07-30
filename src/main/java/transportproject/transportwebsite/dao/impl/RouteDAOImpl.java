package transportproject.transportwebsite.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportproject.transportwebsite.dto.RouteDTO;
import transportproject.transportwebsite.dto.TransportType;

import javax.transaction.Transactional;
import java.util.List;

@Repository("routeDAO")
@Transactional
public class RouteDAOImpl {

    private final SessionFactory sessionFactory;

    @Autowired
    public RouteDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria createEntityCriteria() {
        return getSession().createCriteria(RouteDTO.class);
    }

    public List<RouteDTO> getRoutesByRouteNumberAndTransportType(Integer routeNumber, TransportType transportType) {

        final Criteria entityCriteria = createEntityCriteria();
        entityCriteria.createAlias("transport", "t")
                .add(Restrictions.eq("t.routeNumber", routeNumber))
                .add(Restrictions.eq("t.type", transportType));
        final List<RouteDTO> list = (List<RouteDTO>) entityCriteria.list();
        return list;
    }
}
