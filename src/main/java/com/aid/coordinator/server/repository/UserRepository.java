package com.aid.coordinator.server.repository;

import com.aid.coordinator.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public boolean existsByEmail(String email);

  public User findByEmail(String email);

  @Query("select max(u.id) from User u")
  public Long findMaxId();

}
