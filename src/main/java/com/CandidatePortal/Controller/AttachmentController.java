package com.CandidatePortal.Controller;

import com.CandidatePortal.Entity.Attachment;
import com.CandidatePortal.Model.ResponseData;
import com.CandidatePortal.Service.Implemetation.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AttachmentController {

    @Autowired
    private AttachmentService service;

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam(name = "file")MultipartFile file) throws Exception {
        Attachment attachment=null;
         attachment=service.saveAttachment(file);
         String downloadURL="";
         downloadURL= ServletUriComponentsBuilder.fromCurrentContextPath()
                 .path("/download")
                 .path(attachment.getId())
                 .toUriString();
         return new ResponseData(attachment.getFileName(),downloadURL,file.getContentType(),file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment=null;
        attachment=service.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}
