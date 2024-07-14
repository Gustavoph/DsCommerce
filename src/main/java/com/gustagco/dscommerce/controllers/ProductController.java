package com.gustagco.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gustagco.dscommerce.controllers.dtos.ProductDto;
import com.gustagco.dscommerce.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping()
  @ResponseStatus(value = HttpStatus.OK)
  public Page<ProductDto> getAll(Pageable pagination) {
    return this.productService.getAll(pagination);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ProductDto getById(@PathVariable Long id) {
    return this.productService.getById(id);
  }

  @PostMapping()
  @ResponseStatus(value = HttpStatus.CREATED)
  public ProductDto create(@Valid @RequestBody ProductDto dto) {
    return this.productService.create(dto);
  }

  @PutMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ProductDto update(
      @PathVariable Long id,
      @Valid @RequestBody ProductDto dto) {
    return this.productService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    this.productService.delete(id);
  }
}
