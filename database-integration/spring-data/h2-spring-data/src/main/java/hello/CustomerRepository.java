package hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// the CrudRepository extension is implemented automatically by Spring Boot, communicating with H2
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    // spring data allows queries to be created using a method signature
    List<Customer> findByLastName(String lastName);
}