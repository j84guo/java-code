package mysqlspringdata.data.repository;

import org.springframework.data.repository.CrudRepository;

import mysqlspringdata.data.entity.User;

// automatically by Spring into a Bean called userRepository
// CrudRepository provides various CRUD methods by default
public interface UserRepository extends CrudRepository<User, Long> {

}