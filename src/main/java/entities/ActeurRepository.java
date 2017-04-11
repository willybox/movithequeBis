package entities;


import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ActeurRepository extends JpaRepository<ActeurEntity, Long> {
}

