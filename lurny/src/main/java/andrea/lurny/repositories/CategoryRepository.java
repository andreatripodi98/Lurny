package andrea.lurny.repositories;

import andrea.lurny.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional <Category> findByName(String name);

    Page<Category> findAll(int pageNumber, int pageSize, String sortBy);
}
