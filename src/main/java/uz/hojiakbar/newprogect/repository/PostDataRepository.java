package uz.hojiakbar.newprogect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.hojiakbar.newprogect.Entity.PostData;

@Repository
public interface PostDataRepository extends JpaRepository<PostData, Long> {


}
