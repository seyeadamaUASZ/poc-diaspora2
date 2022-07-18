package sn.gainde2000.userservice.business.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findUserByEmail(String email);

    User findUserByTelephone(String telephone);


    @Query("select count(u) from User u")
    Integer countUsers();

    @Query("select count(u) from User u where u.userStatus='ACTIVATED'")
    Integer countUsersactivated();

    @Query("select count(u) from User u where u.userStatus='NO_ACTIVATED'")
    Integer countUsersdesactived();
}
