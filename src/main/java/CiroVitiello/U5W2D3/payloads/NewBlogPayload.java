package CiroVitiello.U5W2D3.payloads;

import CiroVitiello.U5W2D3.enums.BlogCategories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewBlogPayload {
    private BlogCategories category;
    private String name;
    private String cover;
    private String content;
    private int minutesOfLecture;
    private long authorId;
}
