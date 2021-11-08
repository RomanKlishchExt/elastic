package com.rk.elastic.controller;

import com.rk.elastic.service.ArticleService;
import com.rk.elastic.service.LoadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImportController {

    private final ArticleService articleService;

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam(value = "file") MultipartFile file) {
        articleService.saveImport(file);
    }
}
