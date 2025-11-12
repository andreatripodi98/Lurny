package andrea.lurny.specifications;

import andrea.lurny.entities.Lesson;
import org.springframework.data.jpa.domain.Specification;

public class LessonSpecifications {

    public static Specification<Lesson> hasTitle(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.equal(root.get("title"), title);
    }

    public static Specification<Lesson> hasCategory(String category) {
        return (root, query, cb) ->
                category == null ? null : cb.equal(root.get("category"), category);
    }
}
