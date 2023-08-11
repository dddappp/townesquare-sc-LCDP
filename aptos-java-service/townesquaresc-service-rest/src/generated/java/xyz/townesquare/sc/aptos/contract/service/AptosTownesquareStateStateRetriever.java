// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.aptos.contract.service;


import com.github.wubuku.aptos.bean.AccountResource;
import com.github.wubuku.aptos.utils.*;
import xyz.townesquare.sc.aptos.contract.AptosAccount;
import xyz.townesquare.sc.aptos.contract.ContractConstants;
import xyz.townesquare.sc.aptos.contract.DomainBeanUtils;
import xyz.townesquare.sc.aptos.contract.repository.AptosAccountRepository;
import xyz.townesquare.sc.domain.townesquarestate.*;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.aptos.contract.TownesquareState;

import java.io.IOException;
import java.math.*;
import java.util.*;
import java.util.function.*;


public class AptosTownesquareStateStateRetriever {

    private NodeApiClient aptosNodeApiClient;

    private String aptosContractAddress;

    private AptosAccountRepository aptosAccountRepository;

    private Function<String, TownesquareStateState.MutableTownesquareStateState> townesquareStateStateFactory;


    public AptosTownesquareStateStateRetriever(NodeApiClient aptosNodeApiClient,
                                    String aptosContractAddress,
                                    AptosAccountRepository aptosAccountRepository,
                                    Function<String, TownesquareStateState.MutableTownesquareStateState> townesquareStateStateFactory
    ) {
        this.aptosNodeApiClient = aptosNodeApiClient;
        this.aptosContractAddress = aptosContractAddress;
        this.aptosAccountRepository = aptosAccountRepository;
        this.townesquareStateStateFactory = townesquareStateStateFactory;
    }

    public TownesquareStateState retrieveTownesquareStateState(String accountAddress) {
        String resourceAccountAddress = getResourceAccountAddress();
        AccountResource<TownesquareState> accountResource;
        try {
            accountResource = aptosNodeApiClient.getAccountResource(resourceAccountAddress,
                    this.aptosContractAddress + "::" + ContractConstants.TOWNESQUARE_STATE_MODULE_TOWNESQUARE_STATE,
                    TownesquareState.class,
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TownesquareState townesquareState = accountResource.getData();
        townesquareState.setAccountAddress(resourceAccountAddress);
        return toTownesquareStateState(townesquareState);
    }

    private TownesquareStateState toTownesquareStateState(TownesquareState townesquareState) {
        TownesquareStateState.MutableTownesquareStateState townesquareStateState = townesquareStateStateFactory.apply(townesquareState.getAccountAddress());
        townesquareStateState.setVersion(townesquareState.getVersion());
        townesquareStateState.setIsEmergency(townesquareState.getIsEmergency());
        townesquareStateState.setUserAdmin(townesquareState.getUserAdmin());
        townesquareStateState.setPostAdmin(townesquareState.getPostAdmin());
        return townesquareStateState;
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }

}

