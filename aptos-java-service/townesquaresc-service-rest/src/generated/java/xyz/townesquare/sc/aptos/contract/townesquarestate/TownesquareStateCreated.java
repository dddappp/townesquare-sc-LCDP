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
public class TownesquareStateCreated {

    private String accountAddress;

    private Boolean isEmergency;

    private String userAdmin;

    private String postAdmin;

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
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
        return "TownesquareStateCreated{" +
                "accountAddress=" + '\'' + accountAddress + '\'' +
                ", isEmergency=" + isEmergency +
                ", userAdmin=" + '\'' + userAdmin + '\'' +
                ", postAdmin=" + '\'' + postAdmin + '\'' +
                '}';
    }

}
