// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.aptos.contract.taskservice;

import xyz.townesquare.sc.aptos.contract.repository.*;
import xyz.townesquare.sc.aptos.contract.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateUserStateTaskService {

    @Autowired
    private AptosUserService aptosUserService;

    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private UserEventService userEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.update-user-states.fixed-delay:5000}")
    @Transactional
    public void updateUserStates() {
        userEventRepository.findByStatusIsNull().forEach(e -> {
            aptosUserService.updateUserState(e.getUserWallet());
            userEventService.updateStatusToProcessed(e);
        });
    }
}
