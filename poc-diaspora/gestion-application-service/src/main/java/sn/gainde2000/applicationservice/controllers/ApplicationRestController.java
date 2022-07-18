package sn.gainde2000.applicationservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sn.gainde2000.applicationservice.entities.Application;
import sn.gainde2000.applicationservice.exceptions.ApplicationNotFoundException;
import sn.gainde2000.applicationservice.services.IApplication;
import sn.gainde2000.applicationservice.entities.Application;
import sn.gainde2000.tools.ServeurResponse;
import sn.gainde2000.utils.AppConstants;
import sn.gainde2000.utils.DataResponse;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationRestController {
    static final Logger logger = LoggerFactory.getLogger(ApplicationRestController.class);
    private IApplication iApplication;

    public ApplicationRestController(IApplication iApplication) {
        this.iApplication = iApplication;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Application> save(HttpServletRequest request, @RequestPart("img") MultipartFile img) {
        Application application1 = null;
        try {
            application1 = new ObjectMapper().readValue(request.getParameter("application"),
                    new TypeReference<Application>() {
                    });
            if (img != null) {
                application1.setImg(img.getBytes());
            }
            iApplication.addApplication(application1);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> listapplication() {
        List<Application> applications = iApplication.listApplication();
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getapplicationById(@PathVariable("id") Long id)
            throws ApplicationNotFoundException {
        Application application = iApplication.getApplication(id);
        return ResponseEntity.ok().body(application);
    }


    @GetMapping("/countApplications")
    public ServeurResponse countApplications() {
        ServeurResponse response = new ServeurResponse();
        Integer totalApplications = iApplication.countApplications();
        response.setDescription("total application");
        response.setData(totalApplications);
        response.setStatut(true);
        return response;
    }

    @GetMapping("/pages")
    public ResponseEntity<DataResponse> apllicationPages(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        logger.info("loading all app in response.....");
        return ResponseEntity.ok(iApplication.allApplications(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("/findAppByService/{service}")
    public Application findAppByService(@PathVariable(name="service") String service){
        //ServeurResponse response = new ServeurResponse();
        Application application=new Application();
        try {
           application = iApplication.findApplicationByService(service);
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
        return application;

    }

    @GetMapping("/findApp/{code}")
    public Application findAppByCode(@PathVariable(name="code")String code){
        return iApplication.findAppByCode(code);
    }

}
