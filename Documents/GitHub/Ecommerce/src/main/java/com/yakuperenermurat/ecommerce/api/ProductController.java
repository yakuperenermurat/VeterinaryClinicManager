package com.yakuperenermurat.ecommerce.api;


import com.yakuperenermurat.ecommerce.business.abstracts.ICategoryService;
import com.yakuperenermurat.ecommerce.business.abstracts.IProductService;
import com.yakuperenermurat.ecommerce.business.abstracts.ISupplierService;
import com.yakuperenermurat.ecommerce.core.config.modelMapper.IModelMapperService;
import com.yakuperenermurat.ecommerce.core.result.ResultData;
import com.yakuperenermurat.ecommerce.core.utilies.ResultHelper;
import com.yakuperenermurat.ecommerce.dto.request.category.CategorySaveRequest;
import com.yakuperenermurat.ecommerce.dto.request.product.ProductSaveRequest;
import com.yakuperenermurat.ecommerce.dto.response.CursorResponse;
import com.yakuperenermurat.ecommerce.dto.response.category.CategoryResponse;
import com.yakuperenermurat.ecommerce.dto.response.product.ProductResponse;
import com.yakuperenermurat.ecommerce.dto.response.supplier.SupplierResponse;
import com.yakuperenermurat.ecommerce.entities.Category;
import com.yakuperenermurat.ecommerce.entities.Product;
import com.yakuperenermurat.ecommerce.entities.Supplier;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final IProductService productService;
    private final IModelMapperService modelMapper;
    private final ICategoryService categoryService;
    private final ISupplierService supplierService;
    public ProductController(IProductService productService, IModelMapperService modelMapper,
                             ICategoryService categoryService, ISupplierService supplierService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ProductResponse> save(@Valid @RequestBody ProductSaveRequest productSaveRequest) {
        Product saveProduct = this.modelMapper.forRequest().map(productSaveRequest,Product.class);
        Category category = this.categoryService.get(productSaveRequest.getCategoryId());
        saveProduct.setCategory(category);
        Supplier Supplier = this.supplierService.get(productSaveRequest.getSupplierId());
        saveProduct.setSupplier(Supplier);
        this.productService.save(saveProduct);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveProduct,ProductResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ProductResponse> get(@PathVariable("id") int id) {
        Product product = this.productService.get(id);
        ProductResponse productResponse = this. modelMapper.forResponse().map(product,ProductResponse.class);
        return ResultHelper.success(productResponse);
    }

    @GetMapping("/{id}/supplier")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SupplierResponse> getSupplier(@PathVariable("id") int id) {
        Product product = this.productService.get(id);
        return ResultHelper.success(this. modelMapper.forResponse().map(product.getSupplier(),SupplierResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<ProductResponse>> cursor (
            @RequestParam (name ="page",required = false,defaultValue = "0") int page,
            @RequestParam(name = " pageSize" , required = false , defaultValue = "2") int pageSize
    ){
        Page<Product> productPage = this.productService.cursor(page,pageSize);
        Page<ProductResponse> productResponsePage = productPage
                .map(product -> this.modelMapper.forResponse().map(product,ProductResponse.class));

        return ResultHelper.cursor(productResponsePage);
    }
}
