package com.aid.coordinator.server.controller;

import com.aid.coordinator.server.model.Request;
import com.aid.coordinator.server.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

  @Autowired
  private RequestService requestService;

  @RequestMapping(value = "allRequests", method = RequestMethod.GET)
  public List<Request> getAllRequests() {
    return requestService.getAllRequests();
  }

  @RequestMapping(value = "createRequest", method = RequestMethod.POST)
  public String createRequest(@RequestBody Request request) {
    return requestService.createRequest(request);
  }

  @RequestMapping(value = "updateRequest", method = RequestMethod.PUT)
  public String updateRequest(@RequestBody Request request) {
    return requestService.updateRequest(request);
  }

  @RequestMapping(value = "deleteRequest", method = RequestMethod.DELETE)
  public String deleteRequest(@RequestBody Request request) {
    return requestService.deleteRequest(request);
  }

}
