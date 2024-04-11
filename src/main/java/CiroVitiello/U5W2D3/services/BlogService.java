package CiroVitiello.U5W2D3.services;

import CiroVitiello.U5W2D3.entities.Blog;
import CiroVitiello.U5W2D3.exceptions.BadRequestException;
import CiroVitiello.U5W2D3.exceptions.NoFoundException;
import CiroVitiello.U5W2D3.payloads.NewBlogDTO;
import CiroVitiello.U5W2D3.repositories.BlogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogDAO blogDAO;


    @Autowired
    private AuthorService authorService;


    public Page<Blog> getBlogs(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogDAO.findAll(pageable);
    }

    public Blog saveBlog(NewBlogDTO body) {
        this.blogDAO.findByName(body.name()).ifPresent(
                blog -> {
                    throw new BadRequestException("blog " + blog.getName() + " already exist!");
                }
        );

        return this.blogDAO.save(new Blog(body.category(), body.cover(), body.name(), body.content(), body.minutesOfLecture(), authorService.findById(body.authorId())));
    }

    public Blog findById(long id) {

        return this.blogDAO.findById(id).orElseThrow(() -> new NoFoundException(id));
    }

    public Blog findByIdAndUpdate(long id, NewBlogDTO updatedBlog) {
        Blog found = findById(id);

        found.setName(updatedBlog.name());
        found.setCategory(updatedBlog.category());
        found.setCover(updatedBlog.cover());
        found.setContent(updatedBlog.content());
        found.setMinutesOfLecture(updatedBlog.minutesOfLecture());
        found.setAuthor(authorService.findById(updatedBlog.authorId()));
        return this.blogDAO.save(found);

    }

    public void findByIdAndDelete(long id) {
        Blog found = this.findById(id);
        blogDAO.delete(found);
    }


}
