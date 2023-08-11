// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.aptos.contract.repository;

import xyz.townesquare.sc.domain.user.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserEventRepository extends JpaRepository<AbstractUserEvent, UserEventId> {

    List<AbstractUserEvent> findByStatusIsNull();

    AbstractUserEvent.UserCreated findFirstUserCreatedByOrderByAptosEventSequenceNumber();

    AbstractUserEvent.UserUpdated findFirstUserUpdatedByOrderByAptosEventSequenceNumber();

}