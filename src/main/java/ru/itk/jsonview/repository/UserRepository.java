package ru.itk.jsonview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itk.jsonview.model.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
