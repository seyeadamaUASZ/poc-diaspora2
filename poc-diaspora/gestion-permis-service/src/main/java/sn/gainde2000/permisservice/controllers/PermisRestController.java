package sn.gainde2000.permisservice.controllers;

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
import sn.gainde2000.permisservice.entities.Permis;
import sn.gainde2000.permisservice.tools.PermisConstant;
import sn.gainde2000.permisservice.tools.ServeurResponse;
import sn.gainde2000.permisservice.exceptions.PermisNotFoundException;
import sn.gainde2000.permisservice.services.IPermis;

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
@RequestMapping("/permis")
public class PermisRestController {
    private IPermis iPermis;
    private ApplicationContext context;
    RestTemplate restTemplate;

    @Autowired
    @Qualifier("dataSourceJasper")
    private DataSource dataSourceJasper;

    public PermisRestController(IPermis iPermis, ApplicationContext context, RestTemplate restTemplate) {

        this.iPermis = iPermis;
        this.context = context;

        this.restTemplate = restTemplate;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Permis> save(HttpServletRequest request, @RequestPart("img") MultipartFile img){
        //System.out.println("***************enter**********");
        Permis Permis1= null;

        try {
            Permis1 = new ObjectMapper().readValue(request.getParameter("Permis"),
                     new TypeReference<Permis>() {
                     });
            if(img!=null){
                Permis1.setImg(img.getBytes());
            }
            Permis1 = iPermis.addPermis(Permis1);

            //adding tracability
            addTracability(PermisConstant.PERMIS_INSERT,PermisConstant.PERMIS_APP,PermisConstant.CODE_APP,"En cours",request);
            return new ResponseEntity<>(Permis1,HttpStatus.CREATED);

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "nouveaupermis/{id}")
    public void getPdf(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        try {
            Resource resource = context.getResource("classpath:/opt/permis.jrxml");
//            Map<String, Object> data = new ObjectMapper().readValue(
//                    request.getParameter("data"), new TypeReference<Map<String, Object>>() {
//                    }
//            );
            Map<String, Object> params = new HashMap<>();
//            for (Map.Entry<String, Object> entry : data.entrySet()) {
//                params.put(entry.getKey(), entry.getValue());
//            }
                      params.put("numpermis", id);

            List<Object> var1 = new ArrayList<>();
            var1.add(params);



            InputStream inputStream = resource.getInputStream();



            JasperReport report = JasperCompileManager.compileReport(inputStream);



            JRDataSource dataSource = new JRBeanCollectionDataSource(var1);
            params.put("datasource", dataSource);



            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);



            response.setContentType(MediaType.APPLICATION_PDF_VALUE);



            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (JRException ex) {
            Logger.getLogger(PermisRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GetMapping(path = "delivrerpermis/{id}")
    public ResponseEntity<?> delivrer(@PathVariable("id") Long id, HttpServletResponse response,HttpServletRequest request) throws IOException, PermisNotFoundException {
        Permis Permis1 = iPermis.getPermis(id);
        Permis1.setDelivre(true);
        Resource resource = context.getResource("classpath:/opt/permis.jrxml");

        Map<String, Object> params = new HashMap<>();

        params.put("numpermis", Permis1.getId());
        System.out.println("µ**************"+Permis1.getId());

        List<Object> var1 = new ArrayList<>();
        var1.add(params);
        System.out.println("varrr1"+var1.get(0));


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
                    Permis1.setNouveauPermis(JasperExportManager.exportReportToPdf(jasperPrint));

                    iPermis.addPermis(Permis1);
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
                Permis1.setNouveauPermis(JasperExportManager.exportReportToPdf(jasperPrint));

                iPermis.addPermis(Permis1);
            } catch (JRException e) {
                e.printStackTrace();
            }
        }



        addTracability(PermisConstant.DELIVRER_PERMIS,PermisConstant.PERMIS_APP,PermisConstant.CODE_APP,"terminé",request);
        return ResponseEntity.ok().body(this.iPermis.deliver(Permis1));
    }

        @GetMapping
    public ResponseEntity<?> listPermis(HttpServletRequest request){
        List<Permis> Permiss = iPermis.listPermis();
        //adding tracaiblity
        //addTracability(PermisConstant.PERMIS_INSERT,PermisConstant.CODE_APP,PermisConstant.PERMIS_APP,request);
        return ResponseEntity.ok(Permiss);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permis> getPermisById(@PathVariable("id") Long id,HttpServletRequest request)
            throws PermisNotFoundException {
        Permis Permis = iPermis.getPermis(id);
        //adding tracability
        //addTracability(PermisConstant.PERMIS_INSERT,PermisConstant.CODE_APP,PermisConstant.PERMIS_APP,request);
        return ResponseEntity.ok().body(Permis);


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
