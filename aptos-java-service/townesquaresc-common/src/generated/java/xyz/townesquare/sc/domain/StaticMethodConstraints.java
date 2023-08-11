// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package xyz.townesquare.sc.domain;

import xyz.townesquare.sc.specialization.ReflectUtils;
import xyz.townesquare.sc.specialization.MutationContext;
import xyz.townesquare.sc.specialization.VerificationContext;
import xyz.townesquare.sc.domain.post.*;
import java.math.BigInteger;
import java.util.Date;
import xyz.townesquare.sc.domain.*;
import xyz.townesquare.sc.domain.user.*;
import xyz.townesquare.sc.domain.townesquarestate.*;

public class StaticMethodConstraints {

    public static void assertStaticVerificationAndMutationMethods() {

        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.post.CreateLogic",
                    "verify",
                    new Class[]{PostState.class, String.class, String.class, String.class, String.class, VerificationContext.class},
                    new String[]{"_", "poster", "userId", "content", "digest"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.post.DeleteLogic",
                    "verify",
                    new Class[]{PostState.class, VerificationContext.class},
                    new String[]{"_"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.user.CreateLogic",
                    "verify",
                    new Class[]{UserState.class, String.class, String.class, String.class, VerificationContext.class},
                    new String[]{"_", "username", "profileImage", "bio"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.user.UpdateLogic",
                    "verify",
                    new Class[]{UserState.class, String.class, String.class, String.class, VerificationContext.class},
                    new String[]{"_", "username", "profileImage", "bio"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.townesquarestate.CreateLogic",
                    "verify",
                    new Class[]{TownesquareStateState.class, Boolean.class, String.class, String.class, VerificationContext.class},
                    new String[]{"_", "isEmergency", "userAdmin", "postAdmin"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.townesquarestate.UpdateLogic",
                    "verify",
                    new Class[]{TownesquareStateState.class, Boolean.class, String.class, String.class, VerificationContext.class},
                    new String[]{"_", "isEmergency", "userAdmin", "postAdmin"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.townesquarestate.DeleteLogic",
                    "verify",
                    new Class[]{TownesquareStateState.class, VerificationContext.class},
                    new String[]{"_"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.post.CreateLogic",
                    "mutate",
                    new Class[]{PostState.class, String.class, String.class, String.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "poster", "userId", "content", "digest", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.post.DeleteLogic",
                    "mutate",
                    new Class[]{PostState.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.user.CreateLogic",
                    "mutate",
                    new Class[]{UserState.class, String.class, String.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "username", "profileImage", "bio", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.user.UpdateLogic",
                    "mutate",
                    new Class[]{UserState.class, String.class, String.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "username", "profileImage", "bio", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.townesquarestate.CreateLogic",
                    "mutate",
                    new Class[]{TownesquareStateState.class, Boolean.class, String.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "isEmergency", "userAdmin", "postAdmin", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.townesquarestate.UpdateLogic",
                    "mutate",
                    new Class[]{TownesquareStateState.class, Boolean.class, String.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "isEmergency", "userAdmin", "postAdmin", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "xyz.townesquare.sc.domain.townesquarestate.DeleteLogic",
                    "mutate",
                    new Class[]{TownesquareStateState.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );



    }

}

