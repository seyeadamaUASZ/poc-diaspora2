package sn.gainde2000.userservice.business.Otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OTPConfigurationRepository extends JpaRepository<OTPConfiguration, Long> {
    
	@Query("select o from OTPConfiguration o where o.Valcode=:code")
	public OTPConfiguration findOTPConfigurationByValcode(@Param("code")String code);
}
