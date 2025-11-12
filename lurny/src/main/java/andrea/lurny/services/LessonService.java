package andrea.lurny.services;

import andrea.lurny.entities.Lesson;
import andrea.lurny.exceptions.NotFoundException;
import andrea.lurny.payloads.LessonDTO;
import andrea.lurny.repositories.LessonRepository;
import andrea.lurny.specifications.LessonSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> filterLessons(String title, String category) {
        Specification<Lesson> spec = Specification.where(
                LessonSpecifications.hasTitle(title)
        ).and(LessonSpecifications.hasCategory(category));

        return lessonRepository.findAll(spec);
    }
}
