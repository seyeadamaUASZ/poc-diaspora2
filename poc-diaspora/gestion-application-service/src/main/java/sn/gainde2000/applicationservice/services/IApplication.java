package sn.gainde2000.applicationservice.services;

import sn.gainde2000.applicationservice.entities.Application;
import sn.gainde2000.applicationservice.exceptions.ApplicationNotFoundException;
import sn.gainde2000.utils.DataResponse;

import java.util.List;

public interface IApplication {

    Application addApplication(Application application);

    List<Application> listApplication();

    Application getApplication(Long id) throws ApplicationNotFoundException;

    Integer countApplications();

    DataResponse allApplications(int pageNo, int pageSize, String sortBy, String sortDir);

    Application findApplicationByService(String service)throws ApplicationNotFoundException;

    Application findAppByCode(String code);

}
