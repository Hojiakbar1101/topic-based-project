package uz.hojiakbar.newprogect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.hojiakbar.newprogect.Entity.FileStorage;
@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
    FileStorage   findByHashId(String hashId);

}
