package sn.gainde2000.rvmedicalservice.services;

import org.springframework.stereotype.Service;
import sn.gainde2000.rvmedicalservice.entities.RVMedical;
import sn.gainde2000.rvmedicalservice.exceptions.RVMedicalNotFoundException;
import sn.gainde2000.rvmedicalservice.repositories.RVMedicalRepository;

import java.util.List;

@Service
public class IRVMedicalImpl implements IRVMedical {
    private RVMedicalRepository RVMedicalRepository;

    public IRVMedicalImpl(RVMedicalRepository RVMedicalRepository) {
        this.RVMedicalRepository = RVMedicalRepository;
    }

    @Override
    public RVMedical addRVMedical(RVMedical RVMedical) {
        return this.RVMedicalRepository.save(RVMedical);
    }

    @Override
    public List<RVMedical> listRVMedical() {
        return this.RVMedicalRepository.findAll();
    }

    @Override
    public RVMedical getRVMedical(Long id) throws RVMedicalNotFoundException {
        RVMedical RVMedical = this.RVMedicalRepository.findById(id)
                .orElseThrow(() -> new RVMedicalNotFoundException(
                        "RVMedical not found for this id :: " + id));
        return RVMedical;
    }
}
