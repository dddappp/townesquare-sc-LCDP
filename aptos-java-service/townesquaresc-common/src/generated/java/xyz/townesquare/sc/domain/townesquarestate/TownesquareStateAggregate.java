// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.townesquarestate;

import java.util.List;
import java.util.Date;
import java.math.BigInteger;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.specialization.Event;
import xyz.townesquare.sc.domain.Command;

public interface TownesquareStateAggregate {
    TownesquareStateState getState();

    List<Event> getChanges();

    void create(Boolean isEmergency, String userAdmin, String postAdmin, Long offChainVersion, String commandId, String requesterId, TownesquareStateCommands.Create c);

    void update(Boolean isEmergency, String userAdmin, String postAdmin, Long offChainVersion, String commandId, String requesterId, TownesquareStateCommands.Update c);

    void delete(Long offChainVersion, String commandId, String requesterId, TownesquareStateCommands.Delete c);

    void throwOnInvalidStateTransition(Command c);
}

