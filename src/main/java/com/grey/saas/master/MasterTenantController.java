package com.grey.saas.master;

import com.grey.saas.base.BaseController;
import com.grey.saas.base.BaseResponse;
import com.grey.saas.master.model.MasterTenantEntity;
import com.grey.saas.master.service.MasterTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class MasterTenantController extends BaseController {

    @Autowired
    private MasterTenantService tenantService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<MasterTenantEntity>>> findAll() {
        return new BaseResponse<>(tenantService.findAll()).ok();
    }


    @PostMapping
    public ResponseEntity<BaseResponse<MasterTenantEntity>> saveTenant(@RequestBody MasterTenantEntity tenant) {
        return new BaseResponse<>(tenantService.save(tenant)).ok();
    }


}
