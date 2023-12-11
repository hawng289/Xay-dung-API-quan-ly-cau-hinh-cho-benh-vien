package vn.itechcorp.admin.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import vn.itechcorp.admin.jpa.entity.*;
import vn.itechcorp.admin.service.dto.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Api(value = "sysadmin-api", tags = "sysadmin-api")
@RequestMapping("/sysadmin/ws/rest/v1")
public class AdminController {


    private final AsyncBaseDtoAPIMethod<ConfigAttributeDTOGet, ConfigAttribute, String> configAttributeAPIMethod;



    public AdminController(AsyncBaseDtoAPIMethod<ConfigAttributeDTOGet, ConfigAttribute, String> configAttributeAPIMethod)
    {

        this.configAttributeAPIMethod = configAttributeAPIMethod;

    }



    @ApiOperation(value = "View a list of config attributes")
    @GetMapping("/async/configAttribute")
    public CompletableFuture<ResponseEntity<APIListResponse<List<ConfigAttributeDTOGet>>>> getAllConfigAttributes(
            @RequestParam(required = false, name = "orderBy") String orderBy,
            @RequestParam(required = false, name = "offset", defaultValue = "0") int offset,
            @RequestParam(required = false, name = "limit", defaultValue = "100") int limit) {
        return configAttributeAPIMethod.getListAsync(new PaginationInfo(offset, limit, orderBy));
    }

    @ApiOperation(value = "View a config attribute by ID")
    @GetMapping("/async/configAttribute/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<ConfigAttributeDTOGet>>> getConfigAttributeById(
            @PathVariable("id") String id) {
        return configAttributeAPIMethod.getByIdAsync(id);
    }

    @ApiOperation(value = "Create a new config attribute")
    @PostMapping("/async/configAttribute")
    public CompletableFuture<ResponseEntity<APIResponse<String>>> createConfigAttribute(
            @Valid @RequestBody ConfigAttributeDTOCreate entity,
            Errors error) {
        if (error.hasErrors())
            return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return configAttributeAPIMethod.createAsync(entity);
    }

    @ApiOperation(value = "Update a config attribute")
    @PutMapping("/async/configAttribute")
    public CompletableFuture<ResponseEntity<APIResponse<String>>> updateConfigAttribute(
            @Valid @RequestBody ConfigAttributeDTOUpdate entity,
            Errors error) {
        if (error.hasErrors())
            return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return configAttributeAPIMethod.updateAsync(entity);
    }

    @ApiOperation(value = "Delete a config attribute by ID")
    @DeleteMapping("/async/configAttribute/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<String>>> deleteConfigAttributeByID(
            @PathVariable("id") String id) {
        return configAttributeAPIMethod.deleteAsync(id);
    }
}