package com.aid.coordinator.server.model;

import com.aid.coordinator.server.model.enums.RequestCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "request")
public class Request {

  @Id
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private String location;

  @Enumerated(EnumType.STRING)
  private RequestCategory category;

  private String description;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Request request = (Request) o;
    return id != null && Objects.equals(id, request.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
