package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Customers", path = "Customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

  List<Customer> findByLastName(@Param("name") String name);

}
