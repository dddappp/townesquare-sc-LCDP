// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.post;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.specialization.*;
import xyz.townesquare.sc.domain.AbstractEvent;

public abstract class AbstractPostEvent extends AbstractEvent implements PostEvent.SqlPostEvent, AptosEvent.MutableAptosEvent, HasStatus.MutableHasStatus {
    private PostEventId postEventId = new PostEventId();

    public PostEventId getPostEventId() {
        return this.postEventId;
    }

    public void setPostEventId(PostEventId eventId) {
        this.postEventId = eventId;
    }
    
    public BigInteger getPostId() {
        return getPostEventId().getPostId();
    }

    public void setPostId(BigInteger postId) {
        getPostEventId().setPostId(postId);
    }

    private boolean eventReadOnly;

    public boolean getEventReadOnly() { return this.eventReadOnly; }

    public void setEventReadOnly(boolean readOnly) { this.eventReadOnly = readOnly; }

    public BigInteger getVersion() {
        return getPostEventId().getVersion();
    }
    
    public void setVersion(BigInteger version) {
        getPostEventId().setVersion(version);
    }

    private BigInteger aptosEventVersion;

    public BigInteger getAptosEventVersion() {
        return this.aptosEventVersion;
    }
    
    public void setAptosEventVersion(BigInteger aptosEventVersion) {
        this.aptosEventVersion = aptosEventVersion;
    }

    private BigInteger aptosEventSequenceNumber;

    public BigInteger getAptosEventSequenceNumber() {
        return this.aptosEventSequenceNumber;
    }
    
    public void setAptosEventSequenceNumber(BigInteger aptosEventSequenceNumber) {
        this.aptosEventSequenceNumber = aptosEventSequenceNumber;
    }

    private String aptosEventType;

    public String getAptosEventType() {
        return this.aptosEventType;
    }
    
    public void setAptosEventType(String aptosEventType) {
        this.aptosEventType = aptosEventType;
    }

    private AptosEventGuid aptosEventGuid;

    public AptosEventGuid getAptosEventGuid() {
        return this.aptosEventGuid;
    }
    
    public void setAptosEventGuid(AptosEventGuid aptosEventGuid) {
        this.aptosEventGuid = aptosEventGuid;
    }

    private String status;

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    private String createdBy;

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private Date createdAt;

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    private String commandId;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    private String commandType;

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    protected AbstractPostEvent() {
    }

    protected AbstractPostEvent(PostEventId eventId) {
        this.postEventId = eventId;
    }


    public abstract String getEventClass();

    public static class PostClobEvent extends  AbstractPostEvent {

        protected Map<String, Object> getDynamicProperties() {
            return dynamicProperties;
        }

        protected void setDynamicProperties(Map<String, Object> dynamicProperties) {
            if (dynamicProperties == null) {
                throw new IllegalArgumentException("dynamicProperties is null.");
            }
            this.dynamicProperties = dynamicProperties;
        }

        private Map<String, Object> dynamicProperties = new HashMap<>();

        protected String getDynamicPropertiesLob() {
            return ApplicationContext.current.getClobConverter().toString(getDynamicProperties());
        }

        protected void setDynamicPropertiesLob(String text) {
            getDynamicProperties().clear();
            Map<String, Object> ps = ApplicationContext.current.getClobConverter().parseLobProperties(text);
            if (ps != null) {
                for (Map.Entry<String, Object> kv : ps.entrySet()) {
                    getDynamicProperties().put(kv.getKey(), kv.getValue());
                }
            }
        }

        @Override
        public String getEventClass() {
            return "PostClobEvent";
        }

    }

    public static class PostEvent extends PostClobEvent {

        @Override
        public String getEventClass() {
            return "PostEvent";
        }

        public Integer getEventType() {
            Object val = getDynamicProperties().get("eventType");
            if (val instanceof Integer) {
                return (Integer) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Integer.class);
        }

        public void setEventType(Integer value) {
            getDynamicProperties().put("eventType", value);
        }

        public String getPoster() {
            Object val = getDynamicProperties().get("poster");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setPoster(String value) {
            getDynamicProperties().put("poster", value);
        }

        public String getUserId() {
            Object val = getDynamicProperties().get("userId");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setUserId(String value) {
            getDynamicProperties().put("userId", value);
        }

        public String getContent() {
            Object val = getDynamicProperties().get("content");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setContent(String value) {
            getDynamicProperties().put("content", value);
        }

        public String getDigest() {
            Object val = getDynamicProperties().get("digest");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setDigest(String value) {
            getDynamicProperties().put("digest", value);
        }

    }


}

