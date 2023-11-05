package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(MultipartFile file, String fileType) {
        String uploadDir = "file/upload/" + fileType + "Dir";
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> upload dir" + uploadDir);
        if (!file.isEmpty()) {
            try {
                File fileDir = new File(uploadDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> make dir" + fileDir);
                }
                String filePath = System.currentTimeMillis() + file.getOriginalFilename();
                File uploadedFile = new File(fileDir.getAbsolutePath() + File.separator + filePath);
//
//                File dest = new File(filePath);

                file.transferTo(uploadedFile);
                return uploadDir + "/" + filePath;
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new IllegalArgumentException("upload file failed");
            }
        } else {
            throw new IllegalArgumentException("error.file.empty");
        }
    }
}
