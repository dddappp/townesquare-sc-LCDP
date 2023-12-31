// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.townesquarestate.hibernate;

import java.io.Serializable;
import java.util.*;
import java.util.Date;
import java.math.BigInteger;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.specialization.*;
import xyz.townesquare.sc.specialization.hibernate.AbstractHibernateEventStore;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.transaction.annotation.Transactional;
import xyz.townesquare.sc.domain.townesquarestate.*;

public class HibernateTownesquareStateEventStore extends AbstractHibernateEventStore {
    @Override
    protected Serializable getEventId(EventStoreAggregateId eventStoreAggregateId, long version)
    {
        return new TownesquareStateEventId((String) eventStoreAggregateId.getId(), BigInteger.valueOf(version));
    }

    @Override
    protected Class getSupportedEventType()
    {
        return AbstractTownesquareStateEvent.class;
    }

    @Transactional(readOnly = true)
    @Override
    public EventStream loadEventStream(Class eventType, EventStoreAggregateId eventStoreAggregateId, long version) {
        Class supportedEventType = AbstractTownesquareStateEvent.class;
        if (!eventType.isAssignableFrom(supportedEventType)) {
            throw new UnsupportedOperationException();
        }
        String idObj = (String) eventStoreAggregateId.getId();
        Criteria criteria = getCurrentSession().createCriteria(AbstractTownesquareStateEvent.class);
        criteria.add(Restrictions.eq("townesquareStateEventId.accountAddress", idObj));
        criteria.add(Restrictions.le("townesquareStateEventId.offChainVersion", version));
        criteria.addOrder(Order.asc("townesquareStateEventId.offChainVersion"));
        List es = criteria.list();
        for (Object e : es) {
            ((AbstractTownesquareStateEvent) e).setEventReadOnly(true);
        }
        EventStream eventStream = new EventStream();
        if (es.size() > 0) {
            eventStream.setSteamVersion(((AbstractTownesquareStateEvent) es.get(es.size() - 1)).getTownesquareStateEventId().getVersion().longValue());
        } else {
            //todo?
        }
        eventStream.setEvents(es);
        return eventStream;
    }

}

