package com.epam.digital.data.platform.form.provider.repository;

import com.epam.digital.data.platform.form.provider.entity.FormSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormSchemaRepository extends JpaRepository<FormSchema, Long> {

  List<FormSchema> findFormSchemasByTypeAndShowCardOnUi(String type, Boolean showCardOnUi);
}