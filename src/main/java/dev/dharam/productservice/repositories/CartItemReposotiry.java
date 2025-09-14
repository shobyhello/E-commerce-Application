package dev.dharam.productservice.repositories;

import dev.dharam.productservice.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemReposotiry extends JpaRepository<CartItem, Long> {
}
