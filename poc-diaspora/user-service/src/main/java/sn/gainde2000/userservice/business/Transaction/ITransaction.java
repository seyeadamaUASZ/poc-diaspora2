package sn.gainde2000.userservice.business.Transaction;

import sn.gainde2000.userservice.exceptionHandler.ResourceNotFoundException;
import sn.gainde2000.userservice.utils.DataResponse;
import sn.gainde2000.userservice.utils.TransactionPojo;
import sn.gainde2000.userservice.utils.TransactionPojoStatus;

import java.util.List;

public interface ITransaction {
    public Transaction addTransaction(Transaction transaction);
    public List<Transaction> listTransactions();
    public Transaction getTransaction(Long id) throws ResourceNotFoundException;

    // public List<UtilisateursGetDto> listUtilisateurs();
    DataResponse getTransactionsByUser(int pageNo, int pageSize, String sortBy, String sortDir,Long id);

    //all transactions
    DataResponse allTransactions(int pageNo, int pageSize, String sortBy, String sortDir);

    //nombre de transactions

    Integer countTransactions();

    List<TransactionPojo> listAgreges();

    List<TransactionPojoStatus> listTransactionByStatus();
}
