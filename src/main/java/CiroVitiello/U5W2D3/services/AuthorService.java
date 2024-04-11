package CiroVitiello.U5W2D3.services;


import CiroVitiello.U5W2D3.entities.Author;
import CiroVitiello.U5W2D3.exceptions.BadRequestException;
import CiroVitiello.U5W2D3.exceptions.NoFoundException;
import CiroVitiello.U5W2D3.repositories.AuthorDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;

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
        body.setAvatar2();
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
        found.setAvatar2();
        return this.authorDAO.save(found);
    }


    public void findByIdAndDelete(long id) {
        Author found = this.findById(id);
        this.authorDAO.delete(found);
    }

    public String uploadImage(MultipartFile image, long authorId) throws IOException {
        Author found = findById(authorId);
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        authorDAO.save(found);
        return url;
    }

}
