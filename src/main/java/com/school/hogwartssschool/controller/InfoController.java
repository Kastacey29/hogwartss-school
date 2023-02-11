package com.school.hogwartssschool.controller;

import com.school.hogwartssschool.service.InfoService;
import com.school.hogwartssschool.service.InfoServiceProfileTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/port")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping()
    public ResponseEntity<String> getPort() {
        return ResponseEntity.ok(infoService.getPort());
    }
}
