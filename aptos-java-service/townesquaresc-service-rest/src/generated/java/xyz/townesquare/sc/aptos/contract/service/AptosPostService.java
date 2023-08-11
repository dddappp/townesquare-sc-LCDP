// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.aptos.contract.service;

import com.github.wubuku.aptos.utils.NodeApiClient;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.domain.post.*;
import xyz.townesquare.sc.aptos.contract.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.*;
import java.util.*;
import java.math.*;

@Service
public class AptosPostService {

    @Autowired
    private PostStateRepository postStateRepository;


    private AptosPostStateRetriever aptosPostStateRetriever;

    @Autowired
    public AptosPostService(
        @Value("${aptos.contract.address}")
        String aptosContractAddress,
        NodeApiClient aptosNodeApiClient,
        AptosAccountRepository aptosAccountRepository
    ) {
        this.aptosPostStateRetriever = new AptosPostStateRetriever(
                aptosNodeApiClient,
                aptosContractAddress,
                aptosAccountRepository,
                postId -> {
                    PostState.MutablePostState s = new AbstractPostState.SimplePostState();
                    s.setPostId(postId);
                    return s;
                }
        );
    }

    @Transactional
    public void updatePostState(BigInteger postId) {
        PostState postState = aptosPostStateRetriever.retrievePostState(postId);
        if (postState == null) {
            return;
        }
        postStateRepository.merge(postState);
    }

    @Transactional
    public void deletePost(BigInteger postId) {
        PostState.MutablePostState s = (PostState.MutablePostState) postStateRepository.get(postId, true);
        if (s != null) {
            s.setDeleted(true);
            postStateRepository.merge(s);
        }
    }

}

