package uz.hojiakbar.newprogect.Web.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;
import uz.hojiakbar.newprogect.Entity.FileStorage;
import uz.hojiakbar.newprogect.service.FileStorageService;

import java.net.MalformedURLException;
@RestController
@RequestMapping("/api")

public class FileStorageResource {
    private final FileStorageService fileStorageService;
    @Value("${file.upload-dir}")
    private String serverFolderPath;

    public FileStorageResource(FileStorageService fileStorageService) {

        this.fileStorageService = fileStorageService;
    }
    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file){
        FileStorage fileStorage = fileStorageService.save(file);
        return ResponseEntity.ok(fileStorage);

    }
    @GetMapping("/file-preview/{hashId}")
    public ResponseEntity preview(@PathVariable String hashId) throws MalformedURLException {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "Inline; fileName=\"" + UriEncoder.encode(fileStorage.getName()))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s", this.serverFolderPath, fileStorage.getUploadFolder())));


    }
    @DeleteMapping("/delete/{hasId}")
    public ResponseEntity delete(@PathVariable String hashId){
        fileStorageService.delete(hashId);
        return ResponseEntity.ok("File O'chirildi");
    }
}
