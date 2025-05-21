package uz.hojiakbar.newprogect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.hojiakbar.newprogect.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   boolean existsByLogin(String login);

   User findByLogin(String login);




}
