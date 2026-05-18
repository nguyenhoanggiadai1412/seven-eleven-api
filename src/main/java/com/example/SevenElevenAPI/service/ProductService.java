package com.example.SevenElevenAPI.service;

import com.example.SevenElevenAPI.entity.Product;

import java.util.List;

public interface ProductService {

  List<Product> getAll();

  Product getById(Long id);

  Product create(Product product);

  Product update(Long id, Product product);

  void delete(Long id);
}
