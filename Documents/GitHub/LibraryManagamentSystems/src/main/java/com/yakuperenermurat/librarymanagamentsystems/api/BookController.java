package com.yakuperenermurat.librarymanagamentsystems.api;

import com.yakuperenermurat.librarymanagamentsystems.business.absract.IBookService;
import com.yakuperenermurat.librarymanagamentsystems.business.absract.ICategoryService;
import com.yakuperenermurat.librarymanagamentsystems.core.config.modelMapper.IModelMapperService;
import com.yakuperenermurat.librarymanagamentsystems.core.result.Result;
import com.yakuperenermurat.librarymanagamentsystems.core.result.ResultData;
import com.yakuperenermurat.librarymanagamentsystems.core.utilies.ResultHelper;
import com.yakuperenermurat.librarymanagamentsystems.dto.request.book.BookSaveRequest;
import com.yakuperenermurat.librarymanagamentsystems.dto.request.book.BookUpdateRequest;
import com.yakuperenermurat.librarymanagamentsystems.dto.response.book.BookResponse;
import com.yakuperenermurat.librarymanagamentsystems.entities.Book;
import com.yakuperenermurat.librarymanagamentsystems.entities.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final IBookService bookService;
    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public BookController(IBookService bookService, ICategoryService categoryService, IModelMapperService modelMapper) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);
        List<Category> categories = bookSaveRequest.getCategoryIds().stream()
                .map(categoryId -> categoryService.get(categoryId))
                .collect(Collectors.toList());
        saveBook.setCategories(categories);
        this.bookService.save(saveBook);
        BookResponse bookResponse = this.modelMapper.forResponse().map(saveBook, BookResponse.class);
        return ResultHelper.created(bookResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);
        List<Category> categories = bookUpdateRequest.getCategoryIds().stream()
                .map(categoryId -> categoryService.get(categoryId))
                .collect(Collectors.toList());
        updateBook.setCategories(categories);
        this.bookService.update(updateBook);
        BookResponse bookResponse = this.modelMapper.forResponse().map(updateBook, BookResponse.class);
        return ResultHelper.success(bookResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        BookResponse bookResponse = this.modelMapper.forResponse().map(book, BookResponse.class);
        return ResultHelper.success(bookResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookService.delete(id);
        return ResultHelper.ok();
    }
}