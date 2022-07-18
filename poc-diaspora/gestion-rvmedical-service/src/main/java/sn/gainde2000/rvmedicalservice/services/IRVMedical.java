package sn.gainde2000.rvmedicalservice.services;

import sn.gainde2000.rvmedicalservice.entities.RVMedical;
import sn.gainde2000.rvmedicalservice.exceptions.RVMedicalNotFoundException;

import java.util.List;

public interface IRVMedical {

    RVMedical addRVMedical(RVMedical RVMedical);
    List<RVMedical> listRVMedical();
    RVMedical getRVMedical(Long id) throws RVMedicalNotFoundException;
}
