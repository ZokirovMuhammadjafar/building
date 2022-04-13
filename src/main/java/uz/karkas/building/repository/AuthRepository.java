package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.karkas.building.domain.AuthUser;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthUser,Integer> {

    @Query(nativeQuery = true,value = "select * from auth_user where active =?1 and username = ?2 and block=false")
    AuthUser find(boolean active, String username);

}
