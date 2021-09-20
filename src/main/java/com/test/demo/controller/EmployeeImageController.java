package com.test.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping(value = "/proposal-management/proposals/{id}/data")
@PropertySource("classpath:application.properties")
public class EmployeeImageController {

    private File uploadDirRoot;

    @Autowired
    EmployeeImageController(@Value("${image.upload.dir}") String uploadDir) {
        this.uploadDirRoot = new File(uploadDir);
    }


    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, consumes = {"multipart/form-data"})
    ResponseEntity write(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam("count") String type, @RequestParam("year") String parent_id)
            throws Exception {
        File uploadFile = uploadPath(id, file);
        try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(uploadFile)) {
            FileCopyUtils.copy(in, out);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok("successfully uploaded");
    }

    private File uploadPath(Long id, MultipartFile file) throws IOException {
        File uploadPath = Paths.get(this.uploadDirRoot.getPath(), id.toString()).toFile();
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        return new File(uploadPath.getAbsolutePath(), file.getOriginalFilename());
    }
    public static void main(String[] args){
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(17);
        A.add(3);
        A.add(8);
        A.add(12);
        A.add(16);
        A.add(27);
        A.add(28);
        A.add(22);
        ArrayList<Integer> B = new ArrayList<Integer>(A);
        Collections.sort(B);
        int count = 0;
        parent:
        for (int i = 1; i < A.size() - 1; i++) {
            if(!(A.get(i)<A.get(i+1)&&A.get(i)>A.get(i-1)))
                continue;
            for (int j = B.indexOf(A.get(i)) - 1; j >= 0; j--) {
                if (A.get(i - 1) >= B.get(j))
                    continue;
                else
                    continue parent;
            }
            for (int j = B.indexOf(A.get(i)) + 1; j < B.size(); j++) {
                if (A.get(i + 1) <= B.get(j))
                    continue;
                else
                    continue parent;
            }
            count++;
        }
        System.out.println(count);
    }
}