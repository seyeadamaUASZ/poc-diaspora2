package sn.gainde2000.applicationservice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sn.gainde2000.applicationservice.entities.Application;
import sn.gainde2000.applicationservice.exceptions.ApplicationNotFoundException;
import sn.gainde2000.applicationservice.repositories.ApplicationRepository;
import sn.gainde2000.utils.DataResponse;

import java.util.List;

@Service
public class IApplicationImpl implements IApplication {
    private ApplicationRepository applicationRepository;

    public IApplicationImpl(sn.gainde2000.applicationservice.repositories.ApplicationRepository ApplicationRepository) {
        this.applicationRepository = ApplicationRepository;
    }

    @Override
    public Application addApplication(Application Application) {
        return this.applicationRepository.save(Application);
    }

    @Override
    public List<Application> listApplication() {
        return this.applicationRepository.findAll();
    }

    @Override
    public Application getApplication(Long id) throws ApplicationNotFoundException {
        Application Application = this.applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(
                        "Application not found for this id :: " + id));
        return Application;
    }

    @Override
    public Integer countApplications() {
        return this.applicationRepository.countApplications();
    }

    @Override
    public DataResponse allApplications ( int pageNo, int pageSize, String sortBy, String sortDir){
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

            Page<Application> Applications = this.applicationRepository.findAll(pageable);
            List<Application> listOfApplications = Applications.getContent();
            DataResponse dataResponse = new DataResponse();
            dataResponse.setContent(listOfApplications);
            dataResponse.setPageNo(Applications.getNumber());
            dataResponse.setPageSize(Applications.getSize());
            dataResponse.setTotalElements(Applications.getTotalElements());
            dataResponse.setTotalPages(Applications.getTotalPages());
            dataResponse.setLast(Applications.isLast());

            return dataResponse;
        }

    @Override
    public Application findApplicationByService(String service) throws ApplicationNotFoundException {
        Application application = this.applicationRepository.findApplicationByService(service);
        if(application==null){
            throw new ApplicationNotFoundException("App not found with service name "+service);
        }else{
            return application;
        }

    }

    @Override
    public Application findAppByCode(String code) {
        return this.applicationRepository.findApplicationByCode(code);
    }


}

