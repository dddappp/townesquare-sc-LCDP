// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain.user;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.domain.AbstractCommand;

public abstract class AbstractUserCommand extends AbstractCommand implements UserCommand {

    private String userWallet;

    public String getUserWallet()
    {
        return this.userWallet;
    }

    public void setUserWallet(String userWallet)
    {
        this.userWallet = userWallet;
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


}

