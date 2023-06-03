package com.aid.coordinator.server.repository;


import com.aid.coordinator.server.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

  public List<Request> findByCategory(String location);

  @Query("select max(r.id) from Request r")
  public Long findMaxId();

}
