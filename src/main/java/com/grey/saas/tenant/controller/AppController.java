package com.grey.saas.tenant.controller;

import com.grey.saas.base.BaseResponse;
import com.grey.saas.tenant.model.AppEntity;
import com.grey.saas.tenant.service.AppService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apps")
@AllArgsConstructor
public class AppController {

    private AppService appService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<AppEntity>>> findAll() {
        return new BaseResponse<>(appService.findAll()).ok();
    }

    @PostMapping
    public ResponseEntity<BaseResponse<AppEntity>> saveTenant(@RequestBody AppEntity appEntity) {
        return new BaseResponse<>(appService.save(appEntity)).ok();
    }
}
