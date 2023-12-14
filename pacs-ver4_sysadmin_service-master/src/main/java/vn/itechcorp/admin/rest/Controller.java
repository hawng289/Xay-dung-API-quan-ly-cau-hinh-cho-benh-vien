package vn.itechcorp.admin.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vn.com.itechcorp.base.api.method.AsyncBaseDtoAPIMethod;
import vn.com.itechcorp.base.api.response.APIListResponse;
import vn.com.itechcorp.base.api.response.APIResponse;
import vn.com.itechcorp.base.api.response.APIResponseHeader;
import vn.com.itechcorp.base.api.response.APIResponseStatus;
import vn.com.itechcorp.base.service.filter.PaginationInfo;
import vn.itechcorp.admin.service.dto.ConfigAttributeDTOGet;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOCreate;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOGet;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOUpdate;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1")
public class Controller {
    private final AsyncBaseDtoAPIMethod<HospitalConfigDTOGet, HospitalConfig, Long> hospitalConfigAPIMethod;

    public Controller(AsyncBaseDtoAPIMethod<HospitalConfigDTOGet, HospitalConfig, Long> hospitalConfigAPIMethod) {
        this.hospitalConfigAPIMethod = hospitalConfigAPIMethod;
    }


    @PostMapping("/hospitalConfig")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> createHospitalConfig(
            @Valid @RequestBody HospitalConfigDTOCreate entity,
            Errors error) {
        if (error.hasErrors())
            return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return hospitalConfigAPIMethod.createAsync(entity);
    }
    @GetMapping("/hospitalConfig")
    public CompletableFuture<ResponseEntity<APIListResponse<List<HospitalConfigDTOGet>>>> getAllHospitalConfig(
            @RequestParam(required = false, name = "orderBy") String orderBy,
            @RequestParam(required = false, name = "offset", defaultValue = "0") int offset,
            @RequestParam(required = false, name = "limit", defaultValue = "100") int limit) {
        return hospitalConfigAPIMethod.getListAsync(new PaginationInfo(offset, limit, orderBy));
    }


    @GetMapping("/hospitalConfig/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<HospitalConfigDTOGet>>> getHospitalConfigById(
            @PathVariable("id") Long id) {
        return hospitalConfigAPIMethod.getByIdAsync(id);
    }



    @PutMapping("/hospitalConfig")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> updateHospitalConfig(
            @Valid @RequestBody HospitalConfigDTOUpdate entity,
            Errors error) {
        if (error.hasErrors())
            return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return hospitalConfigAPIMethod.updateAsync(entity);
    }


    @DeleteMapping("/hospitalConfig/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> deleteHospitalConfigByID(
            @PathVariable("id") Long id) {
        return hospitalConfigAPIMethod.deleteAsync(id);
    }
}
