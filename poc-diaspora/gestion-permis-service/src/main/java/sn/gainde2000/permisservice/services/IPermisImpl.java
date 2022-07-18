package sn.gainde2000.permisservice.services;

import org.springframework.stereotype.Service;
import sn.gainde2000.permisservice.entities.Permis;
import sn.gainde2000.permisservice.exceptions.PermisNotFoundException;
import sn.gainde2000.permisservice.repositories.PermisRepository;

import java.util.List;

@Service
public class IPermisImpl implements IPermis {
    private PermisRepository PermisRepository;

    public IPermisImpl(PermisRepository PermisRepository) {
        this.PermisRepository = PermisRepository;
    }

    @Override
    public Permis addPermis(Permis Permis) {
        return this.PermisRepository.save(Permis);
    }

    @Override
    public List<Permis> listPermis() {
        return this.PermisRepository.findAll();
    }

    @Override
    public Permis getPermis(Long id) throws PermisNotFoundException {
        Permis Permis = this.PermisRepository.findById(id)
                .orElseThrow(() -> new PermisNotFoundException(
                        "Permis not found for this id :: " + id));
        return Permis;
    }

    @Override
    public Permis deliver(Permis permis) throws PermisNotFoundException {

        permis.setDelivre(true);

        PermisRepository.save(permis);
        return permis;
    }
}
