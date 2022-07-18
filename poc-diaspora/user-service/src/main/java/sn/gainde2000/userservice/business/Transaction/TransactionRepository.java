package sn.gainde2000.userservice.business.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.gainde2000.userservice.utils.TransactionPojo;
import sn.gainde2000.userservice.utils.TransactionPojoStatus;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    //find transactions with user

    @Query("select t from Transaction t,User u where t.user.id=u.id and u.id=:id")
    Page<Transaction> allTransactionsByUser(@Param("id")Long id, Pageable pageable);

    @Query("select count(t) from Transaction t")
    Integer nombreTransactions();

    @Query("select new sn.gainde2000.userservice.utils.TransactionPojo(count(t),t.application) from Transaction t group by t.application")
    List<TransactionPojo> listAgreges();

    @Query("select new sn.gainde2000.userservice.utils.TransactionPojoStatus(count(t.status),count(t.status1),t.application) from Transaction t group by t.application")
    List<TransactionPojoStatus> listTransactionByStatus();
}
