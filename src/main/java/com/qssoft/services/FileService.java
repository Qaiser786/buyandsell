package com.qssoft.services;

import com.qssoft.dao.FileDAO;
import com.qssoft.entities.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class FileService {

    @Autowired
    private FileDAO fileDAO;

    public String getReference(File file) {
        return file.getId() + "/image";
    }

    public File findById(Long id) {
        return fileDAO.findById(id);
    }

    public File findByReference(String reference) {
        try {
            String idStr = reference.substring(0, reference.indexOf("/"));
            Long id = new Long(idStr);
            return this.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file reference: " + reference, e);
        }
    }

    public File create(File file) {
        return fileDAO.create(file);
    }

    public Boolean delete(File file) {
        return fileDAO.delete(file);
    }

    public Boolean delete(Long id) {
        return fileDAO.delete(id);
    }

    public Boolean delete(String reference) {
        return delete(findByReference(reference));
    }

    public File saveFile(MultipartFile file) throws IOException {
        File result = null;

        if ( file == null || file.getSize() <= 0 ) {
            throw new IllegalArgumentException("File can't be null or empty");
        }

        if ( file.getSize() > 10485760 /* 10 Mb */ ) {
            throw new IllegalArgumentException("File can't be larger than 10 Mb");
        }

        String name = file.getOriginalFilename();
        String extension = name.substring(name.lastIndexOf(".")+1);
        result = new File(name,
                file.getContentType(),
                extension,
                Base64.getEncoder().encodeToString(file.getBytes()));
        result = this.create(result);

        return result;
    }

}
