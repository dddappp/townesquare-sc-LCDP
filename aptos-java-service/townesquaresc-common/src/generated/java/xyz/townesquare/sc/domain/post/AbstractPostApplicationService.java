// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.post;

import java.util.*;
import java.util.function.Consumer;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.specialization.*;

public abstract class AbstractPostApplicationService implements PostApplicationService {

    private EventStore eventStore;

    protected EventStore getEventStore()
    {
        return eventStore;
    }

    private PostStateRepository stateRepository;

    protected PostStateRepository getStateRepository() {
        return stateRepository;
    }

    private PostStateQueryRepository stateQueryRepository;

    protected PostStateQueryRepository getStateQueryRepository() {
        return stateQueryRepository;
    }

    private AggregateEventListener<PostAggregate, PostState> aggregateEventListener;

    public AggregateEventListener<PostAggregate, PostState> getAggregateEventListener() {
        return aggregateEventListener;
    }

    public void setAggregateEventListener(AggregateEventListener<PostAggregate, PostState> eventListener) {
        this.aggregateEventListener = eventListener;
    }

    public AbstractPostApplicationService(EventStore eventStore, PostStateRepository stateRepository, PostStateQueryRepository stateQueryRepository) {
        this.eventStore = eventStore;
        this.stateRepository = stateRepository;
        this.stateQueryRepository = stateQueryRepository;
    }

    public void when(PostCommands.Create c) {
        update(c, ar -> ar.create(c.getPoster(), c.getUserId(), c.getContent(), c.getDigest(), c.getOffChainVersion(), c.getCommandId(), c.getRequesterId(), c));
    }

    public void when(PostCommands.Delete c) {
        update(c, ar -> ar.delete(c.getOffChainVersion(), c.getCommandId(), c.getRequesterId(), c));
    }

    public PostState get(BigInteger id) {
        PostState state = getStateRepository().get(id, true);
        return state;
    }

    public Iterable<PostState> getAll(Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().getAll(firstResult, maxResults);
    }

    public Iterable<PostState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().get(filter, orders, firstResult, maxResults);
    }

    public Iterable<PostState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().get(filter, orders, firstResult, maxResults);
    }

    public Iterable<PostState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().getByProperty(propertyName, propertyValue, orders, firstResult, maxResults);
    }

    public long getCount(Iterable<Map.Entry<String, Object>> filter) {
        return getStateQueryRepository().getCount(filter);
    }

    public long getCount(Criterion filter) {
        return getStateQueryRepository().getCount(filter);
    }

    public PostEvent getEvent(BigInteger postId, long version) {
        PostEvent e = (PostEvent)getEventStore().getEvent(toEventStoreAggregateId(postId), version);
        if (e != null) {
            ((PostEvent.SqlPostEvent)e).setEventReadOnly(true); 
        } else if (version == -1) {
            return getEvent(postId, 0);
        }
        return e;
    }

    public PostState getHistoryState(BigInteger postId, long version) {
        EventStream eventStream = getEventStore().loadEventStream(AbstractPostEvent.class, toEventStoreAggregateId(postId), version - 1);
        return new AbstractPostState.SimplePostState(eventStream.getEvents());
    }


    public PostAggregate getPostAggregate(PostState state) {
        return new AbstractPostAggregate.SimplePostAggregate(state);
    }

    public EventStoreAggregateId toEventStoreAggregateId(BigInteger aggregateId) {
        return new EventStoreAggregateId.SimpleEventStoreAggregateId(aggregateId);
    }

    protected void update(PostCommand c, Consumer<PostAggregate> action) {
        BigInteger aggregateId = c.getPostId();
        EventStoreAggregateId eventStoreAggregateId = toEventStoreAggregateId(aggregateId);
        PostState state = getStateRepository().get(aggregateId, false);
        boolean duplicate = isDuplicateCommand(c, eventStoreAggregateId, state);
        if (duplicate) { return; }

        PostAggregate aggregate = getPostAggregate(state);
        aggregate.throwOnInvalidStateTransition(c);
        action.accept(aggregate);
        persist(eventStoreAggregateId, c.getOffChainVersion() == null ? PostState.VERSION_NULL : c.getOffChainVersion(), aggregate, state); // State version may be null!

    }

    private void persist(EventStoreAggregateId eventStoreAggregateId, long version, PostAggregate aggregate, PostState state) {
        getEventStore().appendEvents(eventStoreAggregateId, version, 
            aggregate.getChanges(), (events) -> { 
                getStateRepository().save(state); 
            });
        if (aggregateEventListener != null) {
            aggregateEventListener.eventAppended(new AggregateEvent<>(aggregate, state, aggregate.getChanges()));
        }
    }

    protected boolean isDuplicateCommand(PostCommand command, EventStoreAggregateId eventStoreAggregateId, PostState state) {
        boolean duplicate = false;
        if (command.getOffChainVersion() == null) { command.setOffChainVersion(PostState.VERSION_NULL); }
        if (state.getOffChainVersion() != null && state.getOffChainVersion() > command.getOffChainVersion()) {
            Event lastEvent = getEventStore().getEvent(AbstractPostEvent.class, eventStoreAggregateId, command.getOffChainVersion());
            if (lastEvent != null && lastEvent instanceof AbstractEvent
               && command.getCommandId() != null && command.getCommandId().equals(((AbstractEvent) lastEvent).getCommandId())) {
                duplicate = true;
            }
        }
        return duplicate;
    }

    public static class SimplePostApplicationService extends AbstractPostApplicationService {
        public SimplePostApplicationService(EventStore eventStore, PostStateRepository stateRepository, PostStateQueryRepository stateQueryRepository)
        {
            super(eventStore, stateRepository, stateQueryRepository);
        }
    }

}

