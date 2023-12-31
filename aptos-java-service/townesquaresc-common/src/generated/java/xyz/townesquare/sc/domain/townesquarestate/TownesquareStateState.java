// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.townesquarestate;

import java.util.*;
import java.math.*;
import java.util.Date;
import java.math.BigInteger;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.specialization.Event;

public interface TownesquareStateState extends VersionedAptosMoveObject
{
    Long VERSION_ZERO = 0L;

    Long VERSION_NULL = VERSION_ZERO - 1;

    String getAccountAddress();

    Boolean getIsEmergency();

    String getUserAdmin();

    String getPostAdmin();

    Long getOffChainVersion();

    String getCreatedBy();

    Date getCreatedAt();

    String getUpdatedBy();

    Date getUpdatedAt();

    Boolean getActive();

    Boolean getDeleted();

    interface MutableTownesquareStateState extends TownesquareStateState, VersionedAptosMoveObject.MutableVersionedAptosMoveObject {
        void setAccountAddress(String accountAddress);

        void setIsEmergency(Boolean isEmergency);

        void setUserAdmin(String userAdmin);

        void setPostAdmin(String postAdmin);

        void setOffChainVersion(Long offChainVersion);

        void setCreatedBy(String createdBy);

        void setCreatedAt(Date createdAt);

        void setUpdatedBy(String updatedBy);

        void setUpdatedAt(Date updatedAt);

        void setActive(Boolean active);

        void setDeleted(Boolean deleted);


        void mutate(Event e);

        //void when(TownesquareStateEvent.TownesquareStateStateCreated e);

        //void when(TownesquareStateEvent.TownesquareStateStateMergePatched e);

        //void when(TownesquareStateEvent.TownesquareStateStateDeleted e);
    }

    interface SqlTownesquareStateState extends MutableTownesquareStateState {

        boolean isStateUnsaved();

        boolean getForReapplying();
    }
}

