package com.aid.coordinator.server.mapper;

import com.aid.coordinator.server.dto.RequestDto;
import com.aid.coordinator.server.entity.Request;
import com.aid.coordinator.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RequestMapper {

  private UserRepository userRepository;

  public Request convertToEntity(RequestDto requestDto) {
    Request request = new Request();
    request.setUser(userRepository.findById(requestDto.getUserId()).get());
    request.setLocation(requestDto.getLocation());
    request.setCategory(requestDto.getCategory());
    request.setDescription(requestDto.getDescription());

    return request;
  }

  public RequestDto convertToDto(Request request) {
    return RequestDto.builder()
        .userId(request.getUser().getId())
        .location(request.getLocation())
        .category(request.getCategory())
        .description(request.getDescription())
        .build();
  }

}
