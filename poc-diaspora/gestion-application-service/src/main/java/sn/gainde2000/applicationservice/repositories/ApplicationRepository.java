package sn.gainde2000.applicationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.gainde2000.applicationservice.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select count(a) from Application  a")
    Integer countApplications();

    @Query("select a from Application a where a.service=:service")
    Application findApplicationByService(@Param("service") String service);

    Application findApplicationByCode(String code);



}
