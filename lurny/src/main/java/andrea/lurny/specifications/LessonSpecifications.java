package andrea.lurny.specifications;

import andrea.lurny.entities.Lesson;
import org.springframework.data.jpa.domain.Specification;

public class LessonSpecifications {

    public static Specification<Lesson> hasTitle(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.equal(root.get("title"), title);
    }

    public static Specification<Lesson> hasCategory(String categoryName) {
        return (root, query, cb) ->
                categoryName == null
                        ? null
                        : cb.equal(root.get("category").get("name"), categoryName);
    }
    public static Specification<Lesson> hasLanguage(String language) {
        return (root, query, cb) ->
                language == null ? null : cb.equal(root.get("language"), language);
    }
}
