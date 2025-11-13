package andrea.lurny.services;

import andrea.lurny.entities.Lesson;
import andrea.lurny.exceptions.NotFoundException;
import andrea.lurny.payloads.LessonDTO;
import andrea.lurny.repositories.LessonRepository;
import andrea.lurny.specifications.LessonSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public Page<Lesson> sortBy(String title, String category, String language, int pageNumber, int pageSize, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return lessonRepository.findAll(
                Specification
                        .allOf(LessonSpecifications.hasTitle(title))
                        .and(LessonSpecifications.hasCategory(category)).and(LessonSpecifications.hasLanguage(language)),

                pageable
        );

    }
}
