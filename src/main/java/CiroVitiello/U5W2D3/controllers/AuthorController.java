package CiroVitiello.U5W2D3.controllers;


import CiroVitiello.U5W2D3.entities.Author;
import CiroVitiello.U5W2D3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    private Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return authorService.getAuthors(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Author saveAuthor(@RequestBody Author body) {
        return authorService.save(body);
    }

    @GetMapping("/{authorId}")
    private Author findAuthorById(@PathVariable int authorId) {
        return authorService.findById(authorId);
    }

    @PutMapping("/{authorId}")
    private Author findAuthorByIdAndUpdate(@PathVariable int authorId, @RequestBody Author body) {
        return authorService.findByIdAndUpdate(authorId, body);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findAuthorByIdAndDelete(@PathVariable int authorId) {
        authorService.findByIdAndDelete(authorId);
    }
}
