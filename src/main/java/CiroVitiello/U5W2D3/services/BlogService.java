package CiroVitiello.U5W2D3.services;


import CiroVitiello.U5W2D3.entities.Blog;
import CiroVitiello.U5W2D3.exceptions.BadRequestException;
import CiroVitiello.U5W2D3.exceptions.NoFoundException;
import CiroVitiello.U5W2D3.payloads.NewBlogPayload;
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

    public Blog saveBlog(NewBlogPayload body) {
        this.blogDAO.findByName(body.getName()).ifPresent(
                blog -> {
                    throw new BadRequestException("blog " + blog.getName() + " already exist!");
                }
        );

        return this.blogDAO.save(new Blog(body.getCategory(), body.getCover(), body.getName(), body.getContent(), body.getMinutesOfLecture(), authorService.findById(body.getAuthorId())));
    }

    public Blog findById(long id) {

        return this.blogDAO.findById(id).orElseThrow(() -> new NoFoundException(id));
    }

    public Blog findByIdAndUpdate(long id, Blog updatedBlog) {
        Blog found = findById(id);

        found.setName(updatedBlog.getName());
        found.setCategory(updatedBlog.getCategory());
        found.setCover(updatedBlog.getCover());
        found.setContent(updatedBlog.getContent());
        found.setMinutesOfLecture(updatedBlog.getMinutesOfLecture());
        return this.blogDAO.save(found);

    }

    public void findByIdAndDelete(long id) {
        Blog found = this.findById(id);
        blogDAO.delete(found);
    }
}
