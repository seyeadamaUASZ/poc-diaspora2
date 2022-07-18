package sn.gainde2000.userservice.business.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilRepository extends JpaRepository<Profil, Long> {
	Profil findProfilByNomProfil(String nomProfil);
}
