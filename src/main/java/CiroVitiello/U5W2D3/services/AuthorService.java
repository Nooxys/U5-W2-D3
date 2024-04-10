package CiroVitiello.U5W2D3.services;


import CiroVitiello.U5W2D3.entities.Author;
import CiroVitiello.U5W2D3.exceptions.BadRequestException;
import CiroVitiello.U5W2D3.exceptions.NoFoundException;
import CiroVitiello.U5W2D3.repositories.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    public Page<Author> getAuthors(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorDAO.findAll(pageable);
    }

    public Author save(Author body) {
        this.authorDAO.findByEmail(body.getEmail()).ifPresent(
                author -> {
                    throw new BadRequestException("email " + author.getEmail() + "already in use!");
                }
        );
        body.setAvatar();
        return authorDAO.save(body);
    }

    public Author findById(long id) {
        return this.authorDAO.findById(id).orElseThrow(() -> new NoFoundException(id));
    }

    public Author findByIdAndUpdate(long id, Author updatedAuthor) {
        Author found = findById(id);

        found.setName(updatedAuthor.getName());
        found.setEmail(updatedAuthor.getEmail());
        found.setSurname(updatedAuthor.getSurname());
        found.setBirthDate(updatedAuthor.getBirthDate());
        found.setAvatar();
        return this.authorDAO.save(found);
    }


    public void findByIdAndDelete(long id) {
        Author found = this.findById(id);
        this.authorDAO.delete(found);
    }
}
