package com.yakuperenermurat.ecommerce.api;


import com.yakuperenermurat.ecommerce.business.abstracts.ICategoryService;
import com.yakuperenermurat.ecommerce.core.config.modelMapper.IModelMapperService;
import com.yakuperenermurat.ecommerce.core.result.Result;
import com.yakuperenermurat.ecommerce.core.result.ResultData;
import com.yakuperenermurat.ecommerce.core.utilies.ResultHelper;
import com.yakuperenermurat.ecommerce.dto.request.category.CategorySaveRequest;
import com.yakuperenermurat.ecommerce.dto.request.category.CategoryUpdateRequest;
import com.yakuperenermurat.ecommerce.dto.response.CursorResponse;
import com.yakuperenermurat.ecommerce.dto.response.category.CategoryResponse;
import com.yakuperenermurat.ecommerce.entities.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private  final ICategoryService categoryService;
    private  final IModelMapperService modelMapper;
    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    /*
{
        "status" : true,
        "message" : "Veri kayıt edildi",
        "code"  : "201",
        "data"  : {
            {
                "id": 2 ,
                "name":"Test  Kategorisi"
            }

        }

}
    */

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest,Category.class);
        this.categoryService.save(saveCategory);
        CategoryResponse categoryResponse =this.modelMapper.forResponse().map(saveCategory,CategoryResponse.class);
       return ResultHelper.created(categoryResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id) {
        Category category = this.categoryService.get(id);
        CategoryResponse categoryResponse = this. modelMapper.forResponse().map(category,CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor (
            @RequestParam (name ="page",required = false,defaultValue = "0") int page,
            @RequestParam(name = " pageSize" , required = false , defaultValue = "2") int pageSize
    ){
        Page<Category> categoryPage = this.categoryService.cursor(page,pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category,CategoryResponse.class));

        return ResultHelper.cursor(categoryResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest,Category.class);
        this.categoryService.update(updateCategory);
        CategoryResponse categoryResponse =this.modelMapper.forResponse().map(updateCategory,CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id ){
        this.categoryService.delete(id);
        return ResultHelper.ok();
    }

}
