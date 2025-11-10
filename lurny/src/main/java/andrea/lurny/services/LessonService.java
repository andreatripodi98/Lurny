package andrea.lurny.services;

import andrea.lurny.entities.Lesson;
import andrea.lurny.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    // Return lesson text

    public String lessonText( String text){
        return lessonRepository.findByText(text)
                .map(Lesson::getText)
                .orElse("Lesson not found");
    }
}
