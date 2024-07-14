package com.gustagco.dscommerce.controllers.dtos;

import com.gustagco.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
  private Long id;
  
  @Size(min = 3, max = 80, message = "Name must have at least 3 and a maximum of 80 characters")
  @NotBlank(message = "Name is required")
  private String name;
  

  @NotBlank(message = "Description is required")
  private String description;
  
  @Positive(message = "Price must be positive")
  private Double price;
  
  private String imgUrl;

  public ProductDto(Product entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.description = entity.getDescription();
    this.price = entity.getPrice();
    this.imgUrl = entity.getImgUrl();
  }
}

