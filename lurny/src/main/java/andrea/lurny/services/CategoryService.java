package andrea.lurny.services;

import andrea.lurny.entities.Category;
import andrea.lurny.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    // Return category
    public String getCategory( String name){
        return categoryRepository.findByName(name)
                .map(Category::getName)
                .orElse("--");

    }
}
