// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.townesquarestate;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.util.Date;
import java.math.BigInteger;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.specialization.Event;
import xyz.townesquare.sc.domain.Command;

public interface TownesquareStateApplicationService {
    void when(TownesquareStateCommands.Create c);

    void when(TownesquareStateCommands.Update c);

    void when(TownesquareStateCommands.Delete c);

    TownesquareStateState get(String id);

    Iterable<TownesquareStateState> getAll(Integer firstResult, Integer maxResults);

    Iterable<TownesquareStateState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<TownesquareStateState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<TownesquareStateState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

    TownesquareStateEvent getEvent(String accountAddress, long version);

    TownesquareStateState getHistoryState(String accountAddress, long version);

}

