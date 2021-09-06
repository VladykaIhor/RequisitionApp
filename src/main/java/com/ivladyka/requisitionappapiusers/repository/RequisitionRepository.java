package com.ivladyka.requisitionappapiusers.repository;

import com.ivladyka.requisitionappapiusers.model.requisition.Requisition;
import org.springframework.data.repository.CrudRepository;

public interface RequisitionRepository extends CrudRepository<Requisition, Long> {
}
