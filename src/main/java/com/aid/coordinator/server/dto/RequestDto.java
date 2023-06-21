package com.aid.coordinator.server.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class RequestDto {

  private Long userId;

  private String location;

  private String category;

  private String description;
}
