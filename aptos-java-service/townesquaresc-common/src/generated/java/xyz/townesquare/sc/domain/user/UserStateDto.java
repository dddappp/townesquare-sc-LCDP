// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.user;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.util.Date;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.specialization.*;


public class UserStateDto {

    private String userWallet;

    public String getUserWallet()
    {
        return this.userWallet;
    }

    public void setUserWallet(String userWallet)
    {
        this.userWallet = userWallet;
    }

    private String username;

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    private String profileImage;

    public String getProfileImage()
    {
        return this.profileImage;
    }

    public void setProfileImage(String profileImage)
    {
        this.profileImage = profileImage;
    }

    private String bio;

    public String getBio()
    {
        return this.bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    private BigInteger version;

    public BigInteger getVersion()
    {
        return this.version;
    }

    public void setVersion(BigInteger version)
    {
        this.version = version;
    }

    private Boolean active;

    public Boolean getActive()
    {
        return this.active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    private Long offChainVersion;

    public Long getOffChainVersion()
    {
        return this.offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion)
    {
        this.offChainVersion = offChainVersion;
    }

    private String createdBy;

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    private Date createdAt;

    public Date getCreatedAt()
    {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    private String updatedBy;

    public String getUpdatedBy()
    {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    private Date updatedAt;

    public Date getUpdatedAt()
    {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }


    public static class DtoConverter extends AbstractStateDtoConverter
    {
        public static Collection<String> collectionFieldNames = Arrays.asList(new String[]{});

        @Override
        protected boolean isCollectionField(String fieldName) {
            return CollectionUtils.collectionContainsIgnoringCase(collectionFieldNames, fieldName);
        }

        public UserStateDto[] toUserStateDtoArray(Iterable<UserState> states) {
            return toUserStateDtoList(states).toArray(new UserStateDto[0]);
        }

        public List<UserStateDto> toUserStateDtoList(Iterable<UserState> states) {
            ArrayList<UserStateDto> stateDtos = new ArrayList();
            for (UserState s : states) {
                UserStateDto dto = toUserStateDto(s);
                stateDtos.add(dto);
            }
            return stateDtos;
        }

        public UserStateDto toUserStateDto(UserState state)
        {
            if(state == null) {
                return null;
            }
            UserStateDto dto = new UserStateDto();
            if (returnedFieldsContains("UserWallet")) {
                dto.setUserWallet(state.getUserWallet());
            }
            if (returnedFieldsContains("Username")) {
                dto.setUsername(state.getUsername());
            }
            if (returnedFieldsContains("ProfileImage")) {
                dto.setProfileImage(state.getProfileImage());
            }
            if (returnedFieldsContains("Bio")) {
                dto.setBio(state.getBio());
            }
            if (returnedFieldsContains("Version")) {
                dto.setVersion(state.getVersion());
            }
            if (returnedFieldsContains("Active")) {
                dto.setActive(state.getActive());
            }
            if (returnedFieldsContains("OffChainVersion")) {
                dto.setOffChainVersion(state.getOffChainVersion());
            }
            if (returnedFieldsContains("CreatedBy")) {
                dto.setCreatedBy(state.getCreatedBy());
            }
            if (returnedFieldsContains("CreatedAt")) {
                dto.setCreatedAt(state.getCreatedAt());
            }
            if (returnedFieldsContains("UpdatedBy")) {
                dto.setUpdatedBy(state.getUpdatedBy());
            }
            if (returnedFieldsContains("UpdatedAt")) {
                dto.setUpdatedAt(state.getUpdatedAt());
            }
            return dto;
        }

    }
}
