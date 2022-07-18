package sn.gainde2000.userservice.business.Otp;

import java.util.List;

public interface IOTPConfiguration {
  public List<OTPConfiguration> listOTPConfigurations();
  public OTPConfiguration generateCodeWithOTPConfig(OTPConfiguration otpConfiguration);
  public OTPConfiguration getOTPConfiguration(Long id);
  public OTPConfiguration findOTPConfigurationByValcode(String code);
  public OTPConfiguration generateOtpByEvenement(String evenement);
  public OTPConfiguration generateOtpByLibelle(String libelle);
}
