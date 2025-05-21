package uz.hojiakbar.newprogect.service;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.hojiakbar.newprogect.Entity.FileStorage;
import uz.hojiakbar.newprogect.Entity.enummartion.FileStorageStatus;
import uz.hojiakbar.newprogect.repository.FileStorageRepository;

import java.io.File;
import java.util.Date;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;
    @Value("${file.upload-dir}")
    private String serverFolderPath;

    private final Hashids hashids;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids(getClass().getSimpleName(), 6);
    }
    public FileStorage save(MultipartFile file) {
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(file.getOriginalFilename());
        fileStorage.setFileSize(file.getSize());
        fileStorage.setContentType(file.getContentType());
        fileStorage.setExtension(getExt(file.getOriginalFilename()));
        fileStorage.setFileStorageStatus(FileStorageStatus.Draft);
        fileStorage = fileStorageRepository.save(fileStorage);

        Date now = new Date();
        String path = String.format("%s/upload_files/%d/%d/%d/",
                this.serverFolderPath, 1900 + now.getYear(), 1 + now.getMonth(), now.getDate());
        File uploadFolder = new File(path);
        if (!uploadFolder.exists() && !uploadFolder.mkdirs()) {
            System.out.println("create upload folder failed");
        }
        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        String pathLoacal = String.format("/upload_files/%d.%d/%d/%s.%s",
                1900 + now.getYear(), 1 + now.getMonth(), now.getDate(),
                fileStorage.getId(), fileStorage.getExtension());
        return fileStorage;
    }
    public void delete(String hashId){
        FileStorage fileStorage = fileStorageRepository.findByHashId(hashId);
        File file = new File(String.format("%s/%s", this.serverFolderPath, fileStorage.getUploadFolder()));
        if (file.delete()) {
        fileStorageRepository.delete(fileStorage);
        }
    }

        public FileStorage findByHashId(String hashId){
            return fileStorageRepository.findByHashId(hashId);
        }
        private String getExt (String fileName){
            String ext = null;
            if (fileName != null && !fileName.isEmpty()) {
                int dot = fileName.lastIndexOf('.');
                if (dot > 0 && fileName.length() - 2 >= dot) {
                    ext = fileName.substring(dot + 1);
                }
            }
            return ext;

    }
}
