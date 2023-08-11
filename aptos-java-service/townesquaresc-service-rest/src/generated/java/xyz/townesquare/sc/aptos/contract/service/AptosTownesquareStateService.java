// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.aptos.contract.service;

import com.github.wubuku.aptos.utils.NodeApiClient;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.domain.townesquarestate.*;
import xyz.townesquare.sc.aptos.contract.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.*;
import java.util.*;
import java.math.*;

@Service
public class AptosTownesquareStateService {

    @Autowired
    private TownesquareStateStateRepository townesquareStateStateRepository;


    private AptosTownesquareStateStateRetriever aptosTownesquareStateStateRetriever;

    @Autowired
    public AptosTownesquareStateService(
        @Value("${aptos.contract.address}")
        String aptosContractAddress,
        NodeApiClient aptosNodeApiClient,
        AptosAccountRepository aptosAccountRepository
    ) {
        this.aptosTownesquareStateStateRetriever = new AptosTownesquareStateStateRetriever(
                aptosNodeApiClient,
                aptosContractAddress,
                aptosAccountRepository,
                accountAddress -> {
                    TownesquareStateState.MutableTownesquareStateState s = new AbstractTownesquareStateState.SimpleTownesquareStateState();
                    s.setAccountAddress(accountAddress);
                    return s;
                }
        );
    }

    @Transactional
    public void updateTownesquareStateState(String accountAddress) {
        TownesquareStateState townesquareStateState = aptosTownesquareStateStateRetriever.retrieveTownesquareStateState(accountAddress);
        if (townesquareStateState == null) {
            return;
        }
        townesquareStateStateRepository.merge(townesquareStateState);
    }

    @Transactional
    public void deleteTownesquareState(String accountAddress) {
        TownesquareStateState.MutableTownesquareStateState s = (TownesquareStateState.MutableTownesquareStateState) townesquareStateStateRepository.get(accountAddress, true);
        if (s != null) {
            s.setDeleted(true);
            townesquareStateStateRepository.merge(s);
        }
    }

}

