package CiroVitiello.U5W2D3.entities;


import CiroVitiello.U5W2D3.enums.BlogCategories;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @Enumerated(EnumType.STRING)
    private BlogCategories category;
    private String name;
    private String cover;
    private String content;
    private int minutesOfLecture;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Blog(BlogCategories category, String cover, String name, String content, int minutesOfLecture, Author author) {
        this.category = category;
        this.cover = cover;
        this.name = name;
        this.content = content;
        this.minutesOfLecture = minutesOfLecture;
        this.author = author;
    }
}
