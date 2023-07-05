package com.aid.coordinator.server.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

  public static final String TEXT = "TEXT";
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "userRole", columnDefinition = TEXT)
  private String role = "SIMPLE_USER";

  @Column(name = "name", columnDefinition = TEXT)
  private String name;

  @Column(name = "surname", columnDefinition = TEXT)
  private String surname;

  @Column(name = "email", columnDefinition = TEXT)
  private String email;

  @Column(name = "phoneNumber", columnDefinition = TEXT)
  private String phoneNumber;

  @Column(name = "password", columnDefinition = TEXT)
  private String password;

  @ToStringExclude
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
  private List<Request> requests;

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    final User user = (User) o;
    return id != null && id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}