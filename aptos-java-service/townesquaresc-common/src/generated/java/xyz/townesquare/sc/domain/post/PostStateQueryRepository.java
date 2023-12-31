// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.post;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import xyz.townesquare.sc.domain.*;

public interface PostStateQueryRepository {
    PostState get(BigInteger id);

    Iterable<PostState> getAll(Integer firstResult, Integer maxResults);
    
    Iterable<PostState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<PostState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    PostState getFirst(Iterable<Map.Entry<String, Object>> filter, List<String> orders);

    PostState getFirst(Map.Entry<String, Object> keyValue, List<String> orders);

    Iterable<PostState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

}

