package com.gustagco.dscommerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_order_item")
public class OrderItem {
  @EmbeddedId
  private OrderItemPK id = new OrderItemPK();
  
  private Integer quantity;
  private Double price;

  public Order getOrder() {
    return id.getOrder();
  }

  public Product getProduct() {
    return id.getProduct();
  }

  public OrderItem(Order order, Product product, Integer quantity, Double price) {
    this.id.setOrder(order);
    this.id.setProduct(product);
    this.quantity = quantity;
    this.price = price;
  }
}
