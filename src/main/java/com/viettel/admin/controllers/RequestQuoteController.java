//package com.viettel.admin.controllers;
//
//import com.viettel.admin.core.model.ResponseWrapper;
//import com.viettel.admin.models.Partner;
//import com.viettel.admin.request.PartnerRequestDto;
//import com.viettel.admin.request.filter.PartnerFilter;
//import com.viettel.admin.services.PartnerService;
//import com.viettel.admin.services.excel.PartnerExcelService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.Resource;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@Slf4j
//@RestController
//@RequestMapping("")
//public class RequestQuoteController {
//
//    @Autowired
//    PartnerService partnerService;
//
//    @Autowired
//    PartnerExcelService partnerExcelService;
//
//    @PostMapping("import")
//    public ResponseEntity<?> importExcel(@RequestParam("file") MultipartFile file) {
//        try{
//            partnerExcelService.importExcel(file);
//            return new ResponseEntity<>(new ResponseWrapper(null, "Import file successfully"),HttpStatus.OK );
//        }catch (Exception ex){
//            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
//        }
//    }
//    @GetMapping("/download")
//    public ResponseEntity<?> downloadFile(@RequestParam String filePath) {
//        Resource resource = new FileSystemResource(filePath);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//
//    @PostMapping("list")
//    public ResponseEntity<?> list(@RequestBody PartnerFilter filter) {
//        try{
//            Page<Partner> partners =  partnerService.getList(filter);
//            return new ResponseEntity<>(new ResponseWrapper(partners),HttpStatus.OK );
//        }catch (Exception ex){
//            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
//        }
//    }
//
//    @GetMapping("detail")
//    public ResponseEntity<?> list(@RequestParam Long id) {
//        try{
//            Partner partner =  partnerService.getById(id);
//            return new ResponseEntity<>(new ResponseWrapper(partner),HttpStatus.OK );
//        }catch (Exception ex){
//            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
//        }
//    }
//
//    @PostMapping("create")
//    public ResponseEntity<?> create(@RequestBody PartnerRequestDto request) {
//        try {
//            PartnerRequestDto partner =  partnerService.create(request);
//            return new ResponseEntity<>(new ResponseWrapper(partner),HttpStatus.OK );
//        }catch (Exception ex){
//            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
//        }
//    }
//
//    @PutMapping("update/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PartnerRequestDto request) {
//        try {
//            PartnerRequestDto partner =  partnerService.update(id,request);
//            return new ResponseEntity<>(new ResponseWrapper(partner),HttpStatus.OK );
//        }catch (Exception ex){
//            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
//        }
//
//    }
//
//    @DeleteMapping("delete")
//    public ResponseEntity<?> delete(@RequestParam Long id) {
//        try {
//            Boolean bl =  partnerService.delete(id);
//            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
//        }catch (Exception ex){
//            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
//        }
//
//    }
//}
