package CiroVitiello.U5W2D3.repositories;

import CiroVitiello.U5W2D3.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogDAO extends JpaRepository<Blog, Long> {
    Optional<Blog> findByName(String name);
}
