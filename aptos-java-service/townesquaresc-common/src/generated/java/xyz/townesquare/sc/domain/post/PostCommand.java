// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.post;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.domain.Command;
import xyz.townesquare.sc.specialization.DomainError;

public interface PostCommand extends Command {

    BigInteger getPostId();

    void setPostId(BigInteger postId);

    Long getOffChainVersion();

    void setOffChainVersion(Long offChainVersion);

    static void throwOnInvalidStateTransition(PostState state, Command c) {
        if (state.getOffChainVersion() == null) {
            if (isCommandCreate((PostCommand)c)) {
                return;
            }
            throw DomainError.named("premature", "Can't do anything to unexistent aggregate");
        }
        if (state.getDeleted() != null && state.getDeleted()) {
            throw DomainError.named("zombie", "Can't do anything to deleted aggregate.");
        }
        if (isCommandCreate((PostCommand)c))
            throw DomainError.named("rebirth", "Can't create aggregate that already exists");
    }

    static boolean isCommandCreate(PostCommand c) {
        if (c.getOffChainVersion().equals(PostState.VERSION_NULL))
            return true;
        return false;
    }

}
