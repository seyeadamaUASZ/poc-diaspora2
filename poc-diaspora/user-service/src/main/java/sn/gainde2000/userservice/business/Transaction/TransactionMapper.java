package sn.gainde2000.userservice.business.Transaction;


import org.mapstruct.Mapper;
import sn.gainde2000.userservice.business.Users.User;
import sn.gainde2000.userservice.business.Users.dtos.UserGetDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDTO toTransactionDTO(Transaction transaction);
    List<TransactionDTO> toTransactionDtos(List<Transaction> transactions);
}
