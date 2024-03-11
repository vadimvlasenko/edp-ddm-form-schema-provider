/*
 * Copyright 2022 EPAM Systems.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.epam.digital.data.platform.form.provider.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RedisHash("bpm-form-schemas")
public class FormSchema {

  @Id
  @EqualsAndHashCode.Include
  private String id;

  private String formData;
  
  @Indexed
  private String type;
  
  @Indexed
  private boolean showCardOnUi;
  
  private List<String> roles;

  public FormSchema(String id, String formData, String type, boolean showCardOnUi, List<String> roles) {
    this.id = id;
    this.formData = formData;
    this.type = type;
    this.showCardOnUi = showCardOnUi;
    this.roles = roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FormSchema)) return false;
    FormSchema that = (FormSchema) o;
    return showCardOnUi == that.showCardOnUi &&
           Objects.equals(id, that.id) &&
           Objects.equals(formData, that.formData) &&
           Objects.equals(type, that.type) &&
           Objects.equals(roles, that.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, formData, type, showCardOnUi, roles);
  }
}