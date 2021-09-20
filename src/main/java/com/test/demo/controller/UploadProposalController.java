package com.test.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping(value = "/proposal-management/proposals/data")
public class UploadProposalController {

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    ResponseEntity write(@RequestBody String proposals)
            throws Exception {
        String referenceId = UUID.randomUUID().toString();
        return ResponseEntity.ok("Proposals successfully uploaded with: "+referenceId);
    }
}
