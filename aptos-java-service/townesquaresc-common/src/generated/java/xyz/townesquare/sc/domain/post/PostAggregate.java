// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.post;

import java.util.List;
import java.math.BigInteger;
import java.util.Date;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.specialization.Event;
import xyz.townesquare.sc.domain.Command;

public interface PostAggregate {
    PostState getState();

    List<Event> getChanges();

    void create(String poster, String userId, String content, String digest, Long offChainVersion, String commandId, String requesterId, PostCommands.Create c);

    void delete(Long offChainVersion, String commandId, String requesterId, PostCommands.Delete c);

    void throwOnInvalidStateTransition(Command c);
}

