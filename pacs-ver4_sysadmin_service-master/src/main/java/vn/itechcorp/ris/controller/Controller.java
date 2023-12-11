package vn.itechcorp.ris.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vn.com.itechcorp.base.api.method.AsyncBaseDtoAPIMethod;
import vn.com.itechcorp.base.api.response.APIResponse;
import vn.com.itechcorp.base.api.response.APIResponseHeader;
import vn.com.itechcorp.base.api.response.APIResponseStatus;
import vn.itechcorp.ris.dto.HospitalConfigDTOCreate;
import vn.itechcorp.ris.dto.HospitalConfigDTOGet;
import vn.itechcorp.ris.dto.HospitalConfigDTOUpdate;
import vn.itechcorp.ris.module.HospitalConfig;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
public class Controller {
    private final AsyncBaseDtoAPIMethod<HospitalConfigDTOGet, HospitalConfig, Long> hospitalConfigAPIMethod;

    public Controller(AsyncBaseDtoAPIMethod<HospitalConfigDTOGet, HospitalConfig, Long> hospitalConfigAPIMethod) {
        this.hospitalConfigAPIMethod = hospitalConfigAPIMethod;
    }


    @PostMapping("/hospitalconfig/create")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> createClient(
            @Valid @RequestBody HospitalConfigDTOCreate entity,
            Errors error) {
        if (error.hasErrors())
            return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return hospitalConfigAPIMethod.createAsync(entity);
    }


    @GetMapping("/hospitalconfig/read/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<HospitalConfigDTOGet>>> getHospitalById(
            @PathVariable("id") Long id) {
        return hospitalConfigAPIMethod.getByIdAsync(id);
    }



    @PutMapping("/hospitalconfig/update")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> updateHospital(
            @Valid @RequestBody HospitalConfigDTOUpdate entity,
            Errors error) {
        if (error.hasErrors())
            return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return hospitalConfigAPIMethod.updateAsync(entity);
    }


    @DeleteMapping("/hospitalconfig/delete/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> deleteHospitalByID(
            @PathVariable("id") Long id) {
        return hospitalConfigAPIMethod.deleteAsync(id);
    }
}
