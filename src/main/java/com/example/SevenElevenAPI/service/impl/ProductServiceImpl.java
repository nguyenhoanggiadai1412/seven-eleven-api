package com.example.SevenElevenAPI.service.impl;

import com.example.SevenElevenAPI.entity.Product;
import com.example.SevenElevenAPI.repository.ProductRepository;
import com.example.SevenElevenAPI.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl
        implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<Product> getAll() {
    return productRepository.findAll();
  }

  @Override
  public Product getById(Long id) {
    return productRepository.findById(id)
            .orElseThrow();
  }

  @Override
  public Product create(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product update(Long id, Product product) {

    Product oldProduct = getById(id);

    oldProduct.setName(product.getName());
    oldProduct.setDescription(product.getDescription());
    oldProduct.setPrice(product.getPrice());
    oldProduct.setStock(product.getStock());
    oldProduct.setImageUrl(product.getImageUrl());

    return productRepository.save(oldProduct);
  }

  @Override
  public void delete(Long id) {
    productRepository.deleteById(id);
  }
}