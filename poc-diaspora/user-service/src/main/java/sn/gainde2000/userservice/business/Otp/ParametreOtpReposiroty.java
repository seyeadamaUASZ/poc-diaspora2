package sn.gainde2000.userservice.business.Otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ParametreOtpReposiroty extends JpaRepository<ParametreOtp, Long> {
 
	public ParametreOtp findParametreOtpByLibelle(String libelle);
	public ParametreOtp findParametreOtpByDureee(String dureee);
	public ParametreOtp findParametreOtpByEvenement(String evenement);
	
	//selon le type
	@Query("select p from ParametreOtp p where p.typeOtp=:type")
	public List<ParametreOtp> listParametrebyType(@Param("type")String type);
	
	//selon le type de durée
	@Query("select p from ParametreOtp p where p.dureee=:dureee")
	public List<ParametreOtp> listParametreOtpByDureee(@Param("dureee")String dureee);
	
}
