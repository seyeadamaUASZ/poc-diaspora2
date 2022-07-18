package sn.gainde2000.userservice.security.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import sn.gainde2000.userservice.business.Users.User;
import sn.gainde2000.userservice.security.dto.AuthenticatedUserDTO;
import sn.gainde2000.userservice.security.dto.RegistrationRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthUserMapper {
	AuthUserMapper INSTANCE = Mappers.getMapper(AuthUserMapper.class);

	User convertToUser(RegistrationRequest registrationRequest);

	AuthenticatedUserDTO convertToAuthenticatedUserDto(User user);

	User convertToUser(AuthenticatedUserDTO authenticatedUserDto);
}
