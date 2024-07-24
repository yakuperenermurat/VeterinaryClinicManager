package com.yakuperenermurat.ecommerce.api;


import com.yakuperenermurat.ecommerce.business.abstracts.ISupplierService;
import com.yakuperenermurat.ecommerce.core.config.modelMapper.IModelMapperService;
import com.yakuperenermurat.ecommerce.core.result.Result;
import com.yakuperenermurat.ecommerce.core.result.ResultData;
import com.yakuperenermurat.ecommerce.core.utilies.ResultHelper;
import com.yakuperenermurat.ecommerce.dto.request.category.CategorySaveRequest;
import com.yakuperenermurat.ecommerce.dto.request.supplier.SupplierSaveRequest;
import com.yakuperenermurat.ecommerce.dto.request.supplier.SupplierUpdateRequest;
import com.yakuperenermurat.ecommerce.dto.response.CursorResponse;
import com.yakuperenermurat.ecommerce.dto.response.category.CategoryResponse;
import com.yakuperenermurat.ecommerce.dto.response.supplier.SupplierResponse;
import com.yakuperenermurat.ecommerce.entities.Category;
import com.yakuperenermurat.ecommerce.entities.Supplier;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/suppliers")
public class SupplierController {

    private final ISupplierService supplierService;
    private final IModelMapperService modelMapper;

    public SupplierController(ISupplierService supplierService, IModelMapperService modelMapper) {
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<SupplierResponse> save(@Valid @RequestBody SupplierSaveRequest supplierSaveRequest) {
        Supplier saveSupplier = this.modelMapper.forRequest().map(supplierSaveRequest,Supplier.class);
        this.supplierService.save(saveSupplier);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveSupplier,SupplierResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<SupplierResponse> update(@Valid @RequestBody SupplierUpdateRequest supplierUpdateRequest) {
        Supplier updateSupplier = this.modelMapper.forRequest().map(supplierUpdateRequest,Supplier.class);
        this.supplierService.update(updateSupplier);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateSupplier,SupplierResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SupplierResponse> get(@PathVariable("id") int id) {
        Supplier supplier = this.supplierService.get(id);
        return ResultHelper.success(this. modelMapper.forResponse().map(supplier,SupplierResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<SupplierResponse>> cursor (
            @RequestParam (name ="page",required = false,defaultValue = "0") int page,
            @RequestParam(name = " pageSize" , required = false , defaultValue = "2") int pageSize
    ){
        Page<Supplier> supplierPage = this.supplierService.cursor(page,pageSize);
        Page<SupplierResponse> supplierResponsesPage = supplierPage
                .map(supplier -> this.modelMapper.forResponse().map(supplier,SupplierResponse.class));

        return ResultHelper.cursor(supplierResponsesPage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id ){
        this.supplierService.delete(id);
        return ResultHelper.ok();
    }
}
