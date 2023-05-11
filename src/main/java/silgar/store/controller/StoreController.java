package silgar.store.controller;

import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import silgar.store.service.IStoreService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Slf4j
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
    public Mono<ResponseEntity<String>> handleFileStore(@RequestParam("file") MultipartFile file) throws InterruptedException{

        log.info("Entering handleFileStore. ");
        TimeUnit.SECONDS.sleep(10);
        log.info("10 seconds later... It shows MS upload is reactive.");
        iStoreService.store(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "StoreController");
        ResponseEntity<String> responseEntity = ResponseEntity.ok()
                .headers(headers)
                .body("File " + file.getOriginalFilename() + " stored!");

        return Mono.just(responseEntity);
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
