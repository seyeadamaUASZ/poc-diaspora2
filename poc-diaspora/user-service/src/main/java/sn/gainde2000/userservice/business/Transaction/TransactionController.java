package sn.gainde2000.userservice.business.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.userservice.business.Users.IUser;
import sn.gainde2000.userservice.business.Users.User;
import sn.gainde2000.userservice.feignClient.ApplicationFeignClient;
import sn.gainde2000.userservice.utils.*;

import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {
    static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private ITransaction iTransaction;
    private IUser iUser;
    private ApplicationFeignClient applicationFeignClient;

    public TransactionController(ITransaction iTransaction, IUser iUser, ApplicationFeignClient applicationFeignClient) {
        this.iTransaction = iTransaction;
        this.iUser = iUser;
        this.applicationFeignClient = applicationFeignClient;
    }

    @PostMapping("/saveTransaction")
    public ServeurResponse addTransaction(@RequestBody Transaction transaction,Authentication authentication){
        ServeurResponse response = new ServeurResponse();
        User user = iUser.findUserByUsername(authentication.getName());
        if(user!=null){
            transaction.setUser(user);

        }
        iTransaction.addTransaction(transaction);
        response.setStatut(true);
        response.setDescription("transaction saved");
        response.setData(transaction);
        return response;
    }

    //tracabilite on get

    @GetMapping("/tracability/{action}/{application}/{code}/{username}/{status}")
    public ServeurResponse tracability(@PathVariable(name="action")String action, @PathVariable(name="code") String code,
                                       @PathVariable(name="application")String application,
                                       @PathVariable(name="username") String username,
                                       @PathVariable(name="status")String status){
        ServeurResponse response = new ServeurResponse();
        User user = iUser.findUserByUsername(username);
        System.out.println("user connected "+user.getUsername());
        Transaction transaction=new Transaction();

        transaction.setApplication(application);
        transaction.setAction(action);
        transaction.setDateTransaction(new Date());
        transaction.setUser(user);
        transaction.setCodeApplication(code);
        //le status de la transaction en mode termine
        //verifier au niveau du status
        if(status.equals("En cours")){
            transaction.setStatus(status);
        }else{
            transaction.setStatus1(status);
        }
        iTransaction.addTransaction(transaction);
        response.setStatut(true);
        response.setDescription("transaction saved");
        response.setData(transaction);
        return response;
    }

    //trace by name service

   /* @GetMapping("/tracability/{action}/{application}/{username}")
    public ServeurResponse trace(@PathVariable(name="action")String action,@PathVariable(name="application")String application, @PathVariable(name="username") String username){
        ServeurResponse response = new ServeurResponse();
        User user = iUser.findUserByUsername(username);
        System.out.println("user connected "+user.getUsername());
        Transaction transaction=new Transaction();

        transaction.setApplication(application);
        transaction.setAction(action);
        transaction.setDateTransaction(new Date());
        transaction.setUser(user);

        //calling and add service pointing

        Application app = applicationFeignClient.getApplicationByCode(application);

        System.out.println("application service name :"+app.getService());

        transaction.setApp(app);

        iTransaction.addTransaction(transaction);
        response.setStatut(true);
        response.setDescription("transaction saved");
        response.setData(transaction);
        return response;
    }*/


    @GetMapping("/transactions/{id}")
    public ResponseEntity<DataResponse> getTransactionsByUser(
            @PathVariable(name="id") Long id,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        logger.info("loading all transaction for user with id :"+id);
        return ResponseEntity.ok(iTransaction.getTransactionsByUser(pageNo, pageSize, sortBy, sortDir,id));
    }

    @GetMapping("/transactions")
    public ResponseEntity<DataResponse> allTransactions(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        logger.info("loading all transactions !!!");
        return ResponseEntity.ok(iTransaction.allTransactions(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("/countTransactions")
    public ServeurResponse nombreTransactions(){
        ServeurResponse response = new ServeurResponse();
        Integer countransactions = iTransaction.countTransactions();
        response.setDescription("nombre de transactions faites");
        response.setData(countransactions);
        response.setStatut(true);
        return response;
    }

    @GetMapping("/findapp/{application}")
    public Application findApptransaction(@PathVariable("application") String application){
        return applicationFeignClient.getApplicationByCode(application);
    }

    @GetMapping("transactions/transactionByService")
    public ServeurResponse  listDesTransactionByApp(){
        ServeurResponse response = new ServeurResponse();
        List<TransactionPojo> aggregats = iTransaction.listAgreges();
        if(!aggregats.isEmpty()){
            response.setData(aggregats);
            response.setDescription("liste agregée des transactions par application");
            response.setStatut(true);
        }else{
            response.setStatut(false);
            response.setDescription("données non fournis");
            response.setData(null);
        }
        return response;
    }


    @GetMapping("transactions/transactionByStatus")
    public ServeurResponse listTransactionByStatus(){
        ServeurResponse response = new ServeurResponse();
        List<TransactionPojoStatus> aggregats = iTransaction.listTransactionByStatus();
        if(!aggregats.isEmpty()){
            response.setData(aggregats);
            response.setDescription("Liste des transactions par status");
            response.setStatut(true);
        }else{
            response.setStatut(false);
            response.setDescription("données non fournis");
            response.setData(null);
        }
        return response;
    }



}
