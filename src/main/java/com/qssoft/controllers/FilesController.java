package com.qssoft.controllers;

import com.qssoft.entities.File;
import com.qssoft.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", produces = "text/plain", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("file") MultipartFile file) {
        try {
            File created = fileService.saveFile(file);
            if ( created != null ) {
                return fileService.getReference(created);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @RequestMapping(value = "/file/{id}/*", method = RequestMethod.GET)
    public void download(HttpServletResponse response, @PathVariable("id") Long id) {
        try {
            File file = fileService.findById(id);
            if ( file == null ) {
                response.sendError(404);
            } else {
                response.setContentType(file.getContentType());
                response.getOutputStream().write(Base64.getDecoder().decode(file.getContent()));
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(500);
            } catch (Exception ignored) {}
        }
    }


//    @RequestMapping(value = "/file/{id}/*", method = RequestMethod.GET)
//    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) {
//        System.out.println("download: " + id);
//        try {
//            File file = fileService.findById(id);
//            if ( file == null ) {
//                response.sendError(404);
//            } else {
//                response.setContentType(file.getContentType());
//                response.getOutputStream().write(Base64.getDecoder().decode(file.getContent()));
//                response.flushBuffer();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                response.sendError(500);
//            } catch (Exception ignored) {}
//        }
//    }

    @RequestMapping(value = "/delete/{reference}", method = RequestMethod.POST)
    public @ResponseBody Boolean delete(@PathVariable String reference) {
        return fileService.delete(reference);
    }

    public static String getFullPath(String reference) {
        return "/files/" + reference;
    }

}
