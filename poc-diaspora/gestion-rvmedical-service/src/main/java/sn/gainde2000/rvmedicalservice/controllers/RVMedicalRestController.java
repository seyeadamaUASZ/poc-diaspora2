package sn.gainde2000.rvmedicalservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sn.gainde2000.rvmedicalservice.entities.RVMedical;
import sn.gainde2000.rvmedicalservice.tools.RVMedicalConstant;
import sn.gainde2000.rvmedicalservice.tools.ServeurResponse;
import sn.gainde2000.rvmedicalservice.exceptions.RVMedicalNotFoundException;
import sn.gainde2000.rvmedicalservice.services.IRVMedical;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.Resource;


@RestController
@RequestMapping("/rvmedical")
public class RVMedicalRestController {
    private IRVMedical iRVMedical;
    private ApplicationContext context;
    RestTemplate restTemplate;
    @Autowired
    @Qualifier("dataSourceJasper")
    private DataSource dataSourceJasper;

    public RVMedicalRestController(IRVMedical iRVMedical, ApplicationContext context, RestTemplate restTemplate) {

        this.iRVMedical = iRVMedical;
        this.context = context;

        this.restTemplate = restTemplate;
    }

    @PostMapping()
    public ResponseEntity<RVMedical> save(@RequestBody RVMedical rvMedical,HttpServletRequest request){
        //System.out.println("***************enter**********");

           RVMedical rv= iRVMedical.addRVMedical(rvMedical);


        //adding tracability
            addTracability(RVMedicalConstant.RVMEDICALRV,RVMedicalConstant.RVMEDICAL_APP,RVMedicalConstant.CODE_APP,"En cours",request);
            return new ResponseEntity<>(rv,HttpStatus.CREATED);

    }

    @PostMapping(path = "nouveauRVMedical")
    public void getPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Resource resource = context.getResource("classpath:opt/ticketrvmedical.jrxml");
            Map<String, Object> data = new ObjectMapper().readValue(
                    request.getParameter("data"), new TypeReference<Map<String, Object>>() {
                    }
            );
            Map<String, Object> params = new HashMap<>();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
            List<Object> var1 = new ArrayList<>();



            InputStream inputStream = resource.getInputStream();



            JasperReport report = JasperCompileManager.compileReport(inputStream);



            JRDataSource dataSource = new JRBeanCollectionDataSource(var1);
            params.put("datasource", dataSource);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);



            response.setContentType(MediaType.APPLICATION_PDF_VALUE);



            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (JRException ex) {
            Logger.getLogger(RVMedicalRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GetMapping
    public ResponseEntity<?> listRVMedical(HttpServletRequest request){
        List<RVMedical> RVMedicals = iRVMedical.listRVMedical();
        //adding tracaiblity
        //addTracability(RVMedicalConstant.RVMEDICAL_LIST,RVMedicalConstant.RVMEDICAL_APP,RVMedicalConstant.CODE_APP,request);
        return ResponseEntity.ok(RVMedicals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RVMedical> getRVMedicalById(@PathVariable("id") Long id,HttpServletRequest request)
            throws RVMedicalNotFoundException {
        RVMedical rv = iRVMedical.getRVMedical(id);

        //adding tracability
        return ResponseEntity.ok().body(rv);
    }

    @GetMapping("/delivrerticket/{id}")
    public ResponseEntity<RVMedical> delivrer(@PathVariable("id") Long id,HttpServletRequest request)
            throws RVMedicalNotFoundException {
        RVMedical rv = iRVMedical.getRVMedical(id);
        //adding tracability
        rv.setDelivre(true);
        Resource resource = context.getResource("classpath:/opt/ticketrvmedical.jrxml");

        Map<String, Object> params = new HashMap<>();
        params.put("numrv",rv.getId());
        List<Object> var1 = new ArrayList<>();
        var1.add(params);
        System.out.println("var1"+var1);

        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JasperReport report = null;
        try {
            report = JasperCompileManager.compileReport(inputStream);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

        JasperPrint jasperPrint ;
        if ((report.getQuery() != null) && (report.getQuery().getText().length() != 0)) {

            try (Connection dataSource = this.dataSourceJasper.getConnection()) {
                try {
                    jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
                    rv.setTicketRv(JasperExportManager.exportReportToPdf(jasperPrint));
                    //writeBytesToFile("/opt/ticket.pdf",rv.getTicketRv());
                    iRVMedical.addRVMedical(rv);
                } catch (JRException e) {
                    e.printStackTrace();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        } else {
            JRDataSource dataSource = new JRBeanCollectionDataSource(var1);
            params.put("datasource", dataSource);
            try {
                jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
                rv.setTicketRv(JasperExportManager.exportReportToPdf(jasperPrint));
               // writeByRv());tesToFile("/opt/ticket.pdf",rv.getTicket
                iRVMedical.addRVMedical(rv);
            } catch (JRException e) {
                e.printStackTrace();
            }
            addTracability(RVMedicalConstant.RVMEDICALRV,RVMedicalConstant.RVMEDICAL_APP,RVMedicalConstant.CODE_APP,"terminé",request);
        }

        return ResponseEntity.ok().body(rv);
    }


    //adding tracability

    public ServeurResponse addTracability(@PathVariable("action")String action,
                                          @PathVariable("application")String application,
                                          @PathVariable("code") String code,
                                          @PathVariable("status") String status,
                                          HttpServletRequest request){
        HttpHeaders httpHeaders = new HttpHeaders();
        final String authHeader =
                request.getHeader(HttpHeaders.AUTHORIZATION);
        String[] payload = decodeToken(authHeader);
        //System.out.println("payload decoded "+payload[1]);
        //System.out.println("auth header :"+authHeader);
        String username = payload[1];
        String user = username.substring(1,username.length()-1);

        httpHeaders.set(HttpHeaders.AUTHORIZATION,authHeader);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return restTemplate.exchange("http://localhost:8085/tracability/"+action+"/"+application+"/"+code+"/"+user+"/"+status, HttpMethod.GET,null,ServeurResponse.class).getBody();
    }


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


    public void writeBytesToFile(String fileOutput, byte[] bytes) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(fileOutput)) {
            fos.write(bytes);
        }

    }


}
