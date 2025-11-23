package andrea.lurny.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Lesson {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private String imageUrl;
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
