package sn.gainde2000.userservice.business.Users.dtos;

import java.util.List;

import org.mapstruct.Mapper;

import sn.gainde2000.userservice.business.Users.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User userPostDTOToUser(UserPostDTO userPostDTO);

    List<UserGetDTO> toUserGetDtos(List<User> users);

    UserGetDTO toUtilisateurGetDto(User utilisateurs);

	/*
	 * Profil profilPostDtoToProfil(ProfilPostDto profilPostDto);
	 * 
	 * ProfilGetDto profilToProfilGetDto(Profil profil);
	 */
}
