// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.townesquarestate;

import java.util.Date;
import java.math.BigInteger;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.domain.AbstractCommand;

public abstract class AbstractTownesquareStateCommandDto extends AbstractCommand {

    /**
     * Account Address
     */
    private String accountAddress;

    public String getAccountAddress()
    {
        return this.accountAddress;
    }

    public void setAccountAddress(String accountAddress)
    {
        this.accountAddress = accountAddress;
    }

    /**
     * Off Chain Version
     */
    private Long offChainVersion;

    public Long getOffChainVersion()
    {
        return this.offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion)
    {
        this.offChainVersion = offChainVersion;
    }


    public void copyTo(TownesquareStateCommand command) {
        command.setAccountAddress(this.getAccountAddress());
        command.setOffChainVersion(this.getOffChainVersion());
        
        command.setRequesterId(this.getRequesterId());
        command.setCommandId(this.getCommandId());
    }

}
