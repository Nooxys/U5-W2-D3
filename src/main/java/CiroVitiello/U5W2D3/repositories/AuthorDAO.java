package CiroVitiello.U5W2D3.repositories;

import CiroVitiello.U5W2D3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email);
}
