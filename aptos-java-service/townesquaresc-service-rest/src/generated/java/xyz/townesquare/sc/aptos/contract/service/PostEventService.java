// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.aptos.contract.service;

import com.github.wubuku.aptos.bean.Event;
import com.github.wubuku.aptos.utils.NodeApiClient;

import xyz.townesquare.sc.domain.post.AbstractPostEvent;
import xyz.townesquare.sc.aptos.contract.ContractConstants;
import xyz.townesquare.sc.aptos.contract.DomainBeanUtils;
import xyz.townesquare.sc.aptos.contract.AptosAccount;

import xyz.townesquare.sc.aptos.contract.post.PostEvent;
import xyz.townesquare.sc.aptos.contract.repository.PostEventRepository;
import xyz.townesquare.sc.aptos.contract.repository.AptosAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.*;
import java.util.*;


@Service
public class PostEventService {

    public static boolean isDeletionCommand(AbstractPostEvent e) {
        if (e instanceof AbstractPostEvent.PostEvent && "2".equals(((AbstractPostEvent.PostEvent) e).getEventType())) {
            return true;
        }
        return false;
    }

    @Value("${aptos.contract.address}")
    private String aptosContractAddress;

    @Autowired
    private AptosAccountRepository aptosAccountRepository;

    @Autowired
    private NodeApiClient aptosNodeApiClient;

    @Autowired
    private PostEventRepository postEventRepository;

    @Transactional
    public void updateStatusToProcessed(AbstractPostEvent event) {
        event.setStatus("D");
        postEventRepository.save(event);
    }

    @Transactional
    public void pullPostEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getPostEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<PostEvent>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.POST_MODULE_EVENTS,
                        ContractConstants.POST_MODULE_POST_EVENT_HANDLE_FIELD,
                        PostEvent.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<PostEvent> eventEnvelope : eventPage) {
                    savePostEvent(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getPostEventNextCursor() {
        AbstractPostEvent.PostEvent lastEvent = postEventRepository.findFirstPostEventByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void savePostEvent(Event<PostEvent> eventEnvelope) {
        AbstractPostEvent.PostEvent postEvent = DomainBeanUtils.toPostEvent(eventEnvelope);
        if (postEventRepository.findById(postEvent.getPostEventId()).isPresent()) {
            return;
        }
        postEventRepository.save(postEvent);
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }
}
