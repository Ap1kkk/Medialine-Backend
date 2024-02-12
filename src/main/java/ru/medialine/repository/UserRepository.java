package ru.medialine.repository;

import ru.medialine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query("FROM User u WHERE u.email = :email AND u.status = 'ACTIVE'")
    User findByEmailWithStatusActive(@Param("email") String email);
}
