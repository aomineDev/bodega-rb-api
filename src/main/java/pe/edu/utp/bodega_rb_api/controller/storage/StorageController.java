package pe.edu.utp.bodega_rb_api.controller.storage;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/storage")
public class StorageController {
  @PostMapping("/{folder}")
  public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
      @PathVariable("folder") String folder) throws IOException {
    if (file.isEmpty())
      return ResponseEntity.badRequest().body("File is empty");

    String destiny = "storage/" + folder + "/";
    String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    filename = filename.replace(" ", "_");

    Path path = Paths.get(destiny + filename);

    Files.createDirectories(path.getParent());
    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

    String baseUrl = "http://localhost:8080/";
    return ResponseEntity.ok(baseUrl + destiny + filename);

  }

}
