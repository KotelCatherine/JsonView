package ru.itk.jsonview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itk.jsonview.model.Order;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

}
