package com.sid.gl.controllers;


import com.sid.gl.feignClients.TransactionRestClient;
import com.sid.gl.models.*;
import com.sid.gl.tools.TransactionConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collections;



@RestController
public class RestConsumer {
    RestTemplate restTemplate;
    private TransactionRestClient transactionRestClient;

    public RestConsumer(RestTemplate restTemplate, TransactionRestClient transactionRestClient) {
        this.restTemplate = restTemplate;
        this.transactionRestClient = transactionRestClient;
    }

    @RequestMapping(value = "demandeautfra/list")
    public ServeurResponse getDemandes(HttpServletRequest request){
        HttpHeaders httpHeaders=new HttpHeaders();
        final String authHeader =
                request.getHeader(HttpHeaders.AUTHORIZATION);
        String[] payload = decodeToken(authHeader);
        //System.out.println("payload decoded "+payload[1]);
        //System.out.println("auth header :"+authHeader);
        String username = payload[1];
        String user = username.substring(1,username.length()-1);
        //System.out.println("user ....."+user);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        //user seing list
        transactionRestClient.addTrace(TransactionConstant.DEMANDELIST,TransactionConstant.APPLICATION,user);

        //adding tracabilty

        //addTracability("consultationliste","fra-service",request);

        return restTemplate.exchange("http://192.168.2.153:8081/backendfra/demandeautfra/list", HttpMethod.GET,null,ServeurResponse.class).getBody();
    }

    @RequestMapping(value = "demandeautfra/suivredemand")
    public ServeurResponse checkSuivreDemande(@RequestBody SuivreDemandeRequest suivreDemandeRequest,HttpServletRequest request){
        HttpHeaders httpHeaders=new HttpHeaders();

        final String authHeader =
                request.getHeader(HttpHeaders.AUTHORIZATION);
        String[] payload = decodeToken(authHeader);
        //System.out.println("payload decoded "+payload[1]);
        //System.out.println("auth header :"+authHeader);
        String username = payload[1];
        String user = username.substring(1,username.length()-1);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<SuivreDemandeRequest> entity = new HttpEntity<SuivreDemandeRequest>(suivreDemandeRequest,httpHeaders);

        transactionRestClient.addTrace(TransactionConstant.SUIVIEDEMANDE,TransactionConstant.APPLICATION,user);

        return restTemplate.exchange("http://localhost:9090/demandeautfra/suivredemand", HttpMethod.POST, entity, ServeurResponse.class).getBody();

    }

    //auth
    @RequestMapping(value = "/auth1")
    public ServeurResponse auth(@RequestBody UserCredentials userCredentials){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserCredentials> entity = new HttpEntity<UserCredentials>(userCredentials,httpHeaders);
        return restTemplate.exchange("http://localhost:9090/auth1", HttpMethod.POST, entity, ServeurResponse.class).getBody();

    }


    //add traçability

    /*@RequestMapping(value="/tracability/{action}/{application}")
    public ServeurResponse addTracability(@PathVariable("action")String action, @PathVariable("application")String application, HttpServletRequest inReq){
        HttpHeaders httpHeaders = new HttpHeaders();
        final String authHeader =
                inReq.getHeader(HttpHeaders.AUTHORIZATION);
        String[] payload = decodeToken(authHeader);
        System.out.println("payload decoded "+payload[1]);
        //System.out.println("auth header :"+authHeader);
        String username = payload[1];

        httpHeaders.set(HttpHeaders.AUTHORIZATION,authHeader);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return restTemplate.exchange("http://localhost:8085/tracability/"+action+"/"+application+"/"+username, HttpMethod.GET,null,ServeurResponse.class).getBody();
    }*/

    //decode token
    public String[] decodeToken(String token){
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        String[] chunk2 = payload.split(",");
        String payload2 = chunk2[0];
        String[] chunk3 = payload2.split(":");
        return chunk3;
    }





}
