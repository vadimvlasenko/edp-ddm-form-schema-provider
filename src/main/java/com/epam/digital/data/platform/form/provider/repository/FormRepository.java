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

package com.epam.digital.data.platform.form.provider.repository;

import com.epam.digital.data.platform.form.provider.entity.FormSchema;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface FormRepository extends KeyValueRepository<FormSchema, String> {

    @Query("SELECT f FROM FormSchema f WHERE f.type = :type AND f.showCardOnUi = :showCardOnUi")
    Collection<FormSchema> findFormSchemasByTypeAndShowCardOnUi(@Param("type") String type, @Param("showCardOnUi") boolean showCardOnUi);

}