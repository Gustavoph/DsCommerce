package com.gustagco.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gustagco.dscommerce.controllers.dtos.ProductDto;
import com.gustagco.dscommerce.entities.Product;
import com.gustagco.dscommerce.repositories.ProductRepository;
import com.gustagco.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Transactional(readOnly = true)
  public Page<ProductDto> getAll(Pageable pagination) {
    Page<Product> result = this.productRepository.findAll(pagination);
    return result.map(ProductDto::new);
  }

  @Transactional(readOnly = true)
  public ProductDto getById(Long id) {
    Product product = this.productRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException());
      
    return new ProductDto(product);
  }

  @Transactional()
  public ProductDto create(ProductDto dto) {
    Product product = new Product();
    this.dtoToEntity(dto, product);

    product = this.productRepository.save(product);
    return new ProductDto(product);
  }

  @Transactional()
  public ProductDto update(Long id, ProductDto dto) {
    var product = this.productRepository.getReferenceById(id);
    this.dtoToEntity(dto, product);

    product = this.productRepository.save(product);
    return new ProductDto(product);
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {
    this.productRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException());
    
    this.productRepository.deleteById(id);
  }

  private void dtoToEntity(ProductDto dto, Product entity) {
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setPrice(dto.getPrice());
    entity.setImgUrl(dto.getImgUrl());
  }
}
