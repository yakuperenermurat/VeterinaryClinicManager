package com.yakuperenermurat.librarymanagamentsystems.api;

import com.yakuperenermurat.librarymanagamentsystems.business.absract.ICategoryService;
import com.yakuperenermurat.librarymanagamentsystems.core.utilies.Msg;
import com.yakuperenermurat.librarymanagamentsystems.dto.request.category.CategorySaveRequest;
import com.yakuperenermurat.librarymanagamentsystems.dto.request.category.CategoryUpdateRequest;
import com.yakuperenermurat.librarymanagamentsystems.dto.response.category.CategoryResponse;
import com.yakuperenermurat.librarymanagamentsystems.core.config.modelMapper.IModelMapperService;
import com.yakuperenermurat.librarymanagamentsystems.core.result.Result;
import com.yakuperenermurat.librarymanagamentsystems.core.result.ResultData;
import com.yakuperenermurat.librarymanagamentsystems.core.utilies.ResultHelper;
import com.yakuperenermurat.librarymanagamentsystems.entities.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);
        this.categoryService.save(saveCategory);
        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(saveCategory, CategoryResponse.class);
        return ResultHelper.created(categoryResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id) {
        Category category = this.categoryService.get(id);
        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(category, CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest, Category.class);
        this.categoryService.update(updateCategory);
        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(updateCategory, CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        String result = this.categoryService.delete(id);
        if (result.equals("Bu kategoriye ait kitap var. Bu kategori silinemedi.")) {
            return ResultHelper.error();
        } else {
            return ResultHelper.ok();
        }
    }
}
