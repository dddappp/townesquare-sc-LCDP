// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.config;

import xyz.townesquare.sc.domain.post.*;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.domain.post.hibernate.*;
import xyz.townesquare.sc.domain.user.*;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.domain.user.hibernate.*;
import xyz.townesquare.sc.domain.townesquarestate.*;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.domain.townesquarestate.hibernate.*;
import xyz.townesquare.sc.specialization.AggregateEventListener;
import xyz.townesquare.sc.specialization.EventStore;
import xyz.townesquare.sc.specialization.IdGenerator;
import xyz.townesquare.sc.specialization.ReadOnlyProxyGenerator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AggregatesHibernateConfig {


    @Bean
    public PostStateRepository postStateRepository(
            SessionFactory hibernateSessionFactory,
            ReadOnlyProxyGenerator stateReadOnlyProxyGenerator
    ) {
        HibernatePostStateRepository repository = new HibernatePostStateRepository();
        repository.setSessionFactory(hibernateSessionFactory);
        repository.setReadOnlyProxyGenerator(stateReadOnlyProxyGenerator);
        return repository;
    }

    @Bean
    public PostStateQueryRepository postStateQueryRepository(
            SessionFactory hibernateSessionFactory,
            ReadOnlyProxyGenerator stateReadOnlyProxyGenerator
    ) {
        HibernatePostStateQueryRepository repository = new HibernatePostStateQueryRepository();
        repository.setSessionFactory(hibernateSessionFactory);
        repository.setReadOnlyProxyGenerator(stateReadOnlyProxyGenerator);
        return repository;
    }

    @Bean
    public HibernatePostEventStore postEventStore(SessionFactory hibernateSessionFactory) {
        HibernatePostEventStore eventStore = new HibernatePostEventStore();
        eventStore.setSessionFactory(hibernateSessionFactory);
        return eventStore;
    }

    @Bean
    public AbstractPostApplicationService.SimplePostApplicationService postApplicationService(
            @Qualifier("postEventStore") EventStore postEventStore,
            PostStateRepository postStateRepository,
            PostStateQueryRepository postStateQueryRepository
    ) {
        AbstractPostApplicationService.SimplePostApplicationService applicationService = new AbstractPostApplicationService.SimplePostApplicationService(
                postEventStore,
                postStateRepository,
                postStateQueryRepository
        );
        return applicationService;
    }



    @Bean
    public UserStateRepository userStateRepository(
            SessionFactory hibernateSessionFactory,
            ReadOnlyProxyGenerator stateReadOnlyProxyGenerator
    ) {
        HibernateUserStateRepository repository = new HibernateUserStateRepository();
        repository.setSessionFactory(hibernateSessionFactory);
        repository.setReadOnlyProxyGenerator(stateReadOnlyProxyGenerator);
        return repository;
    }

    @Bean
    public UserStateQueryRepository userStateQueryRepository(
            SessionFactory hibernateSessionFactory,
            ReadOnlyProxyGenerator stateReadOnlyProxyGenerator
    ) {
        HibernateUserStateQueryRepository repository = new HibernateUserStateQueryRepository();
        repository.setSessionFactory(hibernateSessionFactory);
        repository.setReadOnlyProxyGenerator(stateReadOnlyProxyGenerator);
        return repository;
    }

    @Bean
    public HibernateUserEventStore userEventStore(SessionFactory hibernateSessionFactory) {
        HibernateUserEventStore eventStore = new HibernateUserEventStore();
        eventStore.setSessionFactory(hibernateSessionFactory);
        return eventStore;
    }

    @Bean
    public AbstractUserApplicationService.SimpleUserApplicationService userApplicationService(
            @Qualifier("userEventStore") EventStore userEventStore,
            UserStateRepository userStateRepository,
            UserStateQueryRepository userStateQueryRepository
    ) {
        AbstractUserApplicationService.SimpleUserApplicationService applicationService = new AbstractUserApplicationService.SimpleUserApplicationService(
                userEventStore,
                userStateRepository,
                userStateQueryRepository
        );
        return applicationService;
    }



    @Bean
    public TownesquareStateStateRepository townesquareStateStateRepository(
            SessionFactory hibernateSessionFactory,
            ReadOnlyProxyGenerator stateReadOnlyProxyGenerator
    ) {
        HibernateTownesquareStateStateRepository repository = new HibernateTownesquareStateStateRepository();
        repository.setSessionFactory(hibernateSessionFactory);
        repository.setReadOnlyProxyGenerator(stateReadOnlyProxyGenerator);
        return repository;
    }

    @Bean
    public TownesquareStateStateQueryRepository townesquareStateStateQueryRepository(
            SessionFactory hibernateSessionFactory,
            ReadOnlyProxyGenerator stateReadOnlyProxyGenerator
    ) {
        HibernateTownesquareStateStateQueryRepository repository = new HibernateTownesquareStateStateQueryRepository();
        repository.setSessionFactory(hibernateSessionFactory);
        repository.setReadOnlyProxyGenerator(stateReadOnlyProxyGenerator);
        return repository;
    }

    @Bean
    public HibernateTownesquareStateEventStore townesquareStateEventStore(SessionFactory hibernateSessionFactory) {
        HibernateTownesquareStateEventStore eventStore = new HibernateTownesquareStateEventStore();
        eventStore.setSessionFactory(hibernateSessionFactory);
        return eventStore;
    }

    @Bean
    public AbstractTownesquareStateApplicationService.SimpleTownesquareStateApplicationService townesquareStateApplicationService(
            @Qualifier("townesquareStateEventStore") EventStore townesquareStateEventStore,
            TownesquareStateStateRepository townesquareStateStateRepository,
            TownesquareStateStateQueryRepository townesquareStateStateQueryRepository
    ) {
        AbstractTownesquareStateApplicationService.SimpleTownesquareStateApplicationService applicationService = new AbstractTownesquareStateApplicationService.SimpleTownesquareStateApplicationService(
                townesquareStateEventStore,
                townesquareStateStateRepository,
                townesquareStateStateQueryRepository
        );
        return applicationService;
    }


}
