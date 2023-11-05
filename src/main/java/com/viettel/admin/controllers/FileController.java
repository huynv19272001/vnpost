package com.viettel.admin.controllers;

import com.viettel.admin.services.FileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file, @RequestParam String fileType) {
        return ResponseEntity.ok(fileService.uploadFile(file,fileType));
    }
}
