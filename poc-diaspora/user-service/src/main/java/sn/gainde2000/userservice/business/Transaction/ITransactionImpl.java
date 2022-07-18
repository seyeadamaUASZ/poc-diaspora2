package sn.gainde2000.userservice.business.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sn.gainde2000.userservice.exceptionHandler.ResourceNotFoundException;
import sn.gainde2000.userservice.utils.DataResponse;
import sn.gainde2000.userservice.utils.TransactionPojo;
import sn.gainde2000.userservice.utils.TransactionPojoStatus;

import java.util.List;

@Service
public class ITransactionImpl implements ITransaction {
    private TransactionRepository repository;
    private TransactionMapper mapper;

    public ITransactionImpl(TransactionRepository repository, TransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return this.repository.save(transaction);
    }

    @Override
    public List<Transaction> listTransactions() {
        return this.repository.findAll();
    }

    @Override
    public Transaction getTransaction(Long id) throws ResourceNotFoundException {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Utilisateur not found for this id :: " + id));
    }



    @Override
    public DataResponse getTransactionsByUser(int pageNo, int pageSize, String sortBy, String sortDir, Long id) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Transaction> transactions = this.repository.allTransactionsByUser(id,pageable);
        List<Transaction> listOfTransactions = transactions.getContent();
        List<TransactionDTO> transactionGetDtos = mapper.toTransactionDtos(listOfTransactions);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setContent(transactionGetDtos);
        dataResponse.setPageNo(transactions.getNumber());
        dataResponse.setPageSize(transactions.getSize());
        dataResponse.setTotalElements(transactions.getTotalElements());
        dataResponse.setTotalPages(transactions.getTotalPages());
        dataResponse.setLast(transactions.isLast());

        return dataResponse;
    }

    @Override
    public DataResponse allTransactions(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Transaction> transactions = this.repository.findAll(pageable);
        List<Transaction> listOfTransactions = transactions.getContent();
        List<TransactionDTO> transactionGetDtos = mapper.toTransactionDtos(listOfTransactions);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setContent(transactionGetDtos);
        dataResponse.setPageNo(transactions.getNumber());
        dataResponse.setPageSize(transactions.getSize());
        dataResponse.setTotalElements(transactions.getTotalElements());
        dataResponse.setTotalPages(transactions.getTotalPages());
        dataResponse.setLast(transactions.isLast());

        return dataResponse;
    }

    @Override
    public Integer countTransactions() {
        return this.repository.nombreTransactions();
    }

    @Override
    public List<TransactionPojo> listAgreges() {
        return this.repository.listAgreges();
    }

    @Override
    public List<TransactionPojoStatus> listTransactionByStatus() {
        return this.repository.listTransactionByStatus();
    }
}
