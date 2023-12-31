// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.townesquarestate;

import java.util.*;
import java.util.function.Consumer;
import org.dddml.support.criterion.Criterion;
import java.util.Date;
import java.math.BigInteger;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.specialization.*;

public abstract class AbstractTownesquareStateApplicationService implements TownesquareStateApplicationService {

    private EventStore eventStore;

    protected EventStore getEventStore()
    {
        return eventStore;
    }

    private TownesquareStateStateRepository stateRepository;

    protected TownesquareStateStateRepository getStateRepository() {
        return stateRepository;
    }

    private TownesquareStateStateQueryRepository stateQueryRepository;

    protected TownesquareStateStateQueryRepository getStateQueryRepository() {
        return stateQueryRepository;
    }

    private AggregateEventListener<TownesquareStateAggregate, TownesquareStateState> aggregateEventListener;

    public AggregateEventListener<TownesquareStateAggregate, TownesquareStateState> getAggregateEventListener() {
        return aggregateEventListener;
    }

    public void setAggregateEventListener(AggregateEventListener<TownesquareStateAggregate, TownesquareStateState> eventListener) {
        this.aggregateEventListener = eventListener;
    }

    public AbstractTownesquareStateApplicationService(EventStore eventStore, TownesquareStateStateRepository stateRepository, TownesquareStateStateQueryRepository stateQueryRepository) {
        this.eventStore = eventStore;
        this.stateRepository = stateRepository;
        this.stateQueryRepository = stateQueryRepository;
    }

    public void when(TownesquareStateCommands.Create c) {
        update(c, ar -> ar.create(c.getIsEmergency(), c.getUserAdmin(), c.getPostAdmin(), c.getOffChainVersion(), c.getCommandId(), c.getRequesterId(), c));
    }

    public void when(TownesquareStateCommands.Update c) {
        update(c, ar -> ar.update(c.getIsEmergency(), c.getUserAdmin(), c.getPostAdmin(), c.getOffChainVersion(), c.getCommandId(), c.getRequesterId(), c));
    }

    public void when(TownesquareStateCommands.Delete c) {
        update(c, ar -> ar.delete(c.getOffChainVersion(), c.getCommandId(), c.getRequesterId(), c));
    }

    public TownesquareStateState get(String id) {
        TownesquareStateState state = getStateRepository().get(id, true);
        return state;
    }

    public Iterable<TownesquareStateState> getAll(Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().getAll(firstResult, maxResults);
    }

    public Iterable<TownesquareStateState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().get(filter, orders, firstResult, maxResults);
    }

    public Iterable<TownesquareStateState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().get(filter, orders, firstResult, maxResults);
    }

    public Iterable<TownesquareStateState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().getByProperty(propertyName, propertyValue, orders, firstResult, maxResults);
    }

    public long getCount(Iterable<Map.Entry<String, Object>> filter) {
        return getStateQueryRepository().getCount(filter);
    }

    public long getCount(Criterion filter) {
        return getStateQueryRepository().getCount(filter);
    }

    public TownesquareStateEvent getEvent(String accountAddress, long version) {
        TownesquareStateEvent e = (TownesquareStateEvent)getEventStore().getEvent(toEventStoreAggregateId(accountAddress), version);
        if (e != null) {
            ((TownesquareStateEvent.SqlTownesquareStateEvent)e).setEventReadOnly(true); 
        } else if (version == -1) {
            return getEvent(accountAddress, 0);
        }
        return e;
    }

    public TownesquareStateState getHistoryState(String accountAddress, long version) {
        EventStream eventStream = getEventStore().loadEventStream(AbstractTownesquareStateEvent.class, toEventStoreAggregateId(accountAddress), version - 1);
        return new AbstractTownesquareStateState.SimpleTownesquareStateState(eventStream.getEvents());
    }


    public TownesquareStateAggregate getTownesquareStateAggregate(TownesquareStateState state) {
        return new AbstractTownesquareStateAggregate.SimpleTownesquareStateAggregate(state);
    }

    public EventStoreAggregateId toEventStoreAggregateId(String aggregateId) {
        return new EventStoreAggregateId.SimpleEventStoreAggregateId(aggregateId);
    }

    protected void update(TownesquareStateCommand c, Consumer<TownesquareStateAggregate> action) {
        String aggregateId = c.getAccountAddress();
        EventStoreAggregateId eventStoreAggregateId = toEventStoreAggregateId(aggregateId);
        TownesquareStateState state = getStateRepository().get(aggregateId, false);
        boolean duplicate = isDuplicateCommand(c, eventStoreAggregateId, state);
        if (duplicate) { return; }

        TownesquareStateAggregate aggregate = getTownesquareStateAggregate(state);
        aggregate.throwOnInvalidStateTransition(c);
        action.accept(aggregate);
        persist(eventStoreAggregateId, c.getOffChainVersion() == null ? TownesquareStateState.VERSION_NULL : c.getOffChainVersion(), aggregate, state); // State version may be null!

    }

    private void persist(EventStoreAggregateId eventStoreAggregateId, long version, TownesquareStateAggregate aggregate, TownesquareStateState state) {
        getEventStore().appendEvents(eventStoreAggregateId, version, 
            aggregate.getChanges(), (events) -> { 
                getStateRepository().save(state); 
            });
        if (aggregateEventListener != null) {
            aggregateEventListener.eventAppended(new AggregateEvent<>(aggregate, state, aggregate.getChanges()));
        }
    }

    protected boolean isDuplicateCommand(TownesquareStateCommand command, EventStoreAggregateId eventStoreAggregateId, TownesquareStateState state) {
        boolean duplicate = false;
        if (command.getOffChainVersion() == null) { command.setOffChainVersion(TownesquareStateState.VERSION_NULL); }
        if (state.getOffChainVersion() != null && state.getOffChainVersion() > command.getOffChainVersion()) {
            Event lastEvent = getEventStore().getEvent(AbstractTownesquareStateEvent.class, eventStoreAggregateId, command.getOffChainVersion());
            if (lastEvent != null && lastEvent instanceof AbstractEvent
               && command.getCommandId() != null && command.getCommandId().equals(((AbstractEvent) lastEvent).getCommandId())) {
                duplicate = true;
            }
        }
        return duplicate;
    }

    public static class SimpleTownesquareStateApplicationService extends AbstractTownesquareStateApplicationService {
        public SimpleTownesquareStateApplicationService(EventStore eventStore, TownesquareStateStateRepository stateRepository, TownesquareStateStateQueryRepository stateQueryRepository)
        {
            super(eventStore, stateRepository, stateQueryRepository);
        }
    }

}

