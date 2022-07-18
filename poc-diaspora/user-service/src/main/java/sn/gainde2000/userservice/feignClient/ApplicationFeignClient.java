package sn.gainde2000.userservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.gainde2000.userservice.business.Transaction.Application;

@FeignClient(name="appplication-service")
public interface ApplicationFeignClient {

    @GetMapping("/findApp/{code}")
    Application getApplicationByCode(@PathVariable("code")String code);
}
