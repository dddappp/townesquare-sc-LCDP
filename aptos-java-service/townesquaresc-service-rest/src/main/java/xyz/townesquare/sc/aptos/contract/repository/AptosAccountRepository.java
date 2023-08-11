package xyz.townesquare.sc.aptos.contract.repository;

import xyz.townesquare.sc.aptos.contract.AptosAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AptosAccountRepository extends JpaRepository<AptosAccount, String> {
    
}
