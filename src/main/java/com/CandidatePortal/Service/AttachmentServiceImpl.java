package com.CandidatePortal.Service;

import com.CandidatePortal.Entity.Attachment;
import com.CandidatePortal.Repository.AttachmentRepository;
import com.CandidatePortal.Service.Implemetation.AttachmentService;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository repository;

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if (fileName.contains("..")){
                throw new Exception("fileName Contains invalid path Sequence"+fileName);
            }
            Attachment attachment=new Attachment(fileName,file.getContentType(),file.getBytes());
            return repository.save(attachment);
        }catch (Exception e){
            throw new Exception("could not save file"+fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
//                PdfReader reader = new PdfReader(fileId);
//        WriterProperties props = new WriterProperties()
//                .setStandardEncryption(USERPASS, OWNERPASS, EncryptionConstants.ALLOW_PRINTING,
//                        EncryptionConstants.ENCRYPTION_AES_128 | EncryptionConstants.DO_NOT_ENCRYPT_METADATA);
//        PdfWriter writer = new PdfWriter(new FileOutputStream(dest), props);
//        PdfDocument pdfDoc = new PdfDocument(reader, writer);
//        pdfDoc.close();
        return (Attachment) repository.findById(fileId)
                .orElseThrow(()->new Exception("file not found"));
    }
}
