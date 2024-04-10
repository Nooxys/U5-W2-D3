package CiroVitiello.U5W2D3.controllers;

import CiroVitiello.U5W2D3.entities.Blog;
import CiroVitiello.U5W2D3.payloads.NewBlogPayload;
import CiroVitiello.U5W2D3.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    private Page<Blog> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        return this.blogService.getBlogs(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Blog SaveBlog(@RequestBody NewBlogPayload body) {
        return this.blogService.saveBlog(body);
    }

    @GetMapping("/{blogId}")
    private Blog findBlogById(@PathVariable int blogId) {
        return this.blogService.findById(blogId);
    }

    @PutMapping("/{blogId}")
    private Blog findBlogByIdAndUpdate(@PathVariable int blogId, @RequestBody Blog body) {
        return this.blogService.findByIdAndUpdate(blogId, body);
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findBlogByIdAndDelete(@PathVariable int blogId) {
        this.blogService.findByIdAndDelete(blogId);
    }

}
