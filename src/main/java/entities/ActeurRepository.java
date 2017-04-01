package entities;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@org.springframework.stereotype.Repository
public interface ActeurRepository extends CrudRepository<ActeurEntity, Long> {
}

