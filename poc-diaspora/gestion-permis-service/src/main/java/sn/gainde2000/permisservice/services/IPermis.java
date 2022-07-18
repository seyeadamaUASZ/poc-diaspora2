package sn.gainde2000.permisservice.services;

import sn.gainde2000.permisservice.entities.Permis;
import sn.gainde2000.permisservice.exceptions.PermisNotFoundException;

import java.util.List;

public interface IPermis {

    Permis addPermis(Permis Permis);
    List<Permis> listPermis();
    Permis getPermis(Long id) throws PermisNotFoundException;
    Permis deliver(Permis permis) throws PermisNotFoundException;
}
