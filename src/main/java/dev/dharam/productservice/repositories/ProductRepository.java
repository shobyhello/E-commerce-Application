package dev.dharam.productservice.repositories;


import dev.dharam.productservice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Product  save(Product product);

    @Override
    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);
}
