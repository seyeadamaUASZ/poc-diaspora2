package sn.gainde2000.pi.otp.service;

import java.util.List;

import sn.gainde2000.pi.otp.entity.OTPConfiguration;

public interface IOTPConfiguration {
  public List<OTPConfiguration> listOTPConfigurations();
  public OTPConfiguration generateCodeWithOTPConfig(OTPConfiguration otpConfiguration);
  public OTPConfiguration getOTPConfiguration(Long id);
  public OTPConfiguration findOTPConfigurationByValcode(String code);
  public OTPConfiguration generateOtpByEvenement(String evenement);
  public OTPConfiguration generateOtpByLibelle(String libelle);
}
