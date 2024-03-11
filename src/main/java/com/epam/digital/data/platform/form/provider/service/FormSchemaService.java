package com.epam.digital.data.platform.form.provider.service;

import com.epam.digital.data.platform.form.provider.entity.FormSchema;
import com.epam.digital.data.platform.form.provider.repository.FormSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormSchemaService {

  @Autowired
  private FormSchemaRepository formSchemaRepository;

  public List<FormSchema> getVisibleCardsForCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    List<String> userRoles = authentication.getAuthorities().stream()
        .map(authority -> authority.getAuthority())
        .collect(Collectors.toList());

    return formSchemaRepository.findFormSchemasByTypeAndShowCardOnUi("card", true).stream()
        .filter(formSchema -> formSchema.getRoles().stream().anyMatch(userRoles::contains))
        .collect(Collectors.toList());
  }
}