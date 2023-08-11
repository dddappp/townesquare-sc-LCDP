// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.aptos.contract.townesquarestate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import xyz.townesquare.sc.aptos.contract.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TownesquareStateUpdated {

    private String accountAddress;

    private BigInteger version;

    private Boolean isEmergency;

    private String userAdmin;

    private String postAdmin;

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public Boolean getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(Boolean isEmergency) {
        this.isEmergency = isEmergency;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getPostAdmin() {
        return postAdmin;
    }

    public void setPostAdmin(String postAdmin) {
        this.postAdmin = postAdmin;
    }

    @Override
    public String toString() {
        return "TownesquareStateUpdated{" +
                "accountAddress=" + '\'' + accountAddress + '\'' +
                ", version=" + version +
                ", isEmergency=" + isEmergency +
                ", userAdmin=" + '\'' + userAdmin + '\'' +
                ", postAdmin=" + '\'' + postAdmin + '\'' +
                '}';
    }

}