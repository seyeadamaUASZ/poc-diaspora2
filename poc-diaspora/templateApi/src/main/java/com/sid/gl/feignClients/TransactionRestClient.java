package com.sid.gl.feignClients;


import com.sid.gl.models.ServeurResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="auth")
public interface TransactionRestClient {
   @GetMapping("/tracability/{action}/{application}/{username}")
   ServeurResponse addTrace(@PathVariable(name="action")String action, @PathVariable(name="application") String application,@PathVariable(name="username") String username);
}
