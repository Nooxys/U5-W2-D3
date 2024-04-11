package CiroVitiello.U5W2D3.payloads;

import CiroVitiello.U5W2D3.enums.BlogCategories;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewBlogDTO(@NotNull(message = "category is required!")
                         BlogCategories category,
                         @NotEmpty(message = "Name is required!")
                         @Size(min = 3, max = 30, message = "your name must be between 3 and 30 characters!")
                         String name,
                         @NotEmpty(message = "cover is required!")
                         String cover,
                         @NotEmpty(message = "content is required!")
                         String content,
                         @NotNull(message = "You must insert the minutes of the lecture!")
                         @Min(message = "Minutes of lecture must be at least 1", value = 1)
                         int minutesOfLecture,
                         @NotNull(message = "u must insert the creator of this blogpost!")
                         @Min(message = "Author id must be at least 1", value = 1)
                         long authorId) {
}
