/*
 * Copyright 2022 EPAM Systems.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.digital.data.platform.form.provider.controller;

import com.epam.digital.data.platform.form.provider.service.impl.FormSchemaProviderServiceImpl;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FormSchemaProviderController {

  private final FormSchemaProviderServiceImpl formSchemaProviderServiceImpl;

  public FormSchemaProviderController(FormSchemaProviderServiceImpl formSchemaProviderServiceImpl) {
    this.formSchemaProviderServiceImpl = formSchemaProviderServiceImpl;
  }

  @PostMapping("/forms")
  public ResponseEntity<Void> saveForm(@RequestBody String formData) {
    formSchemaProviderServiceImpl.saveForm(formData);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/forms/{key}")
  public ResponseEntity<JSONObject> getForm(@PathVariable("key") String key) {
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(formSchemaProviderServiceImpl.getFormByKey(key));
  }

  @PutMapping("/forms/{key}")
  public ResponseEntity<Void> updateForm(@PathVariable("key") String key,
      @RequestBody String formSchemaData) {
    formSchemaProviderServiceImpl.updateForm(key, formSchemaData);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/forms/{key}")
  public ResponseEntity<Void> deleteFormByKey(@PathVariable("key") String key) {
    formSchemaProviderServiceImpl.deleteFormByKey(key);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  
  @GetMapping("/cards/visible")
  public ResponseEntity<JSONArray> getVisibleCardsForCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JSONArray visibleCards = formSchemaProviderServiceImpl.getVisibleCardsForCurrentUser(authentication);
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(visibleCards);
  }
}