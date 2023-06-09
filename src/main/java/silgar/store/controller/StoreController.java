package silgar.store.controller;

import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import silgar.store.service.IStoreService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@RestController
public class StoreController {

    private final IStoreService iStoreService;

    @Autowired
    public StoreController(IStoreService iStoreService){
        this.iStoreService = iStoreService;
    }

    /*
    Store a file to the Storage
     */
    @PostMapping(path = "/store-s3")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<String> handleFileStore(@RequestParam("file") MultipartFile file) {

        iStoreService.store(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "StoreController");

        return ResponseEntity.ok().headers(headers).body("You successfully stored " + file.getOriginalFilename() + "!");
    }


    /*
    Check if the application is running
     */
    @GetMapping(value = "/health")
    @ResponseBody
    public ResponseEntity<String> status() {

        return ResponseEntity.ok().body("Store Application is running...");
    }


}
