package org.wecancodeit.libraryjpa2demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wecancodeit.libraryjpa2demo.models.Author;
import org.wecancodeit.libraryjpa2demo.models.Book;
import org.wecancodeit.libraryjpa2demo.models.Campus;
import org.wecancodeit.libraryjpa2demo.repositories.AuthorRepository;
import org.wecancodeit.libraryjpa2demo.repositories.BookRepository;
import org.wecancodeit.libraryjpa2demo.repositories.CampusRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JPAWritingTest {

    @Autowired
    private TestEntityManager entityManager;


    @Autowired
    private CampusRepository campusRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private BookRepository bookRepo;

    @Test
    public void campusShouldHaveAListOfBooks() {
        Campus testCampus = new Campus("Test Location");
        Campus testCampus2 = new Campus("Test Location2");
        Author testAuthor1 = new Author("Test firstName", "Test lastName");
        Book testBook1 = new Book("Title", "Description", testCampus, testAuthor1);
        Book testBook2 = new Book("Title", "Description", testCampus2,testAuthor1);

        campusRepo.save(testCampus);
        campusRepo.save(testCampus2);
        authorRepo.save(testAuthor1);
        bookRepo.save(testBook1);
        bookRepo.save(testBook2);

        entityManager.flush();
        entityManager.clear();


        Optional<Campus> retrievedCampusOpt = campusRepo.findById(testCampus.getId());
        Campus retrievedCampus = retrievedCampusOpt.get();
        assertThat(retrievedCampus.getBooks()).contains(testBook1);

    }

    @Test
    public void bookShouldBeAbleToHaveMultipleAuthors(){
        Campus testCampus = new Campus("Test Location");
        Author testAuthor1 = new Author("Test firstName", "Test lastName");
        Author testAuthor2 = new Author("Test firstName2", "Test lastName2");
        Book testBook1 = new Book("Title", "Description", testCampus, testAuthor1, testAuthor2);
        Book testBook2 = new Book("Title", "Description", testCampus,testAuthor1);
        Book testBook3 = new Book("Title", "Description", testCampus, testAuthor2);
        campusRepo.save(testCampus);
        authorRepo.save(testAuthor1);
        authorRepo.save(testAuthor2);
        bookRepo.save(testBook1);
        bookRepo.save(testBook2);
        bookRepo.save(testBook3);

        entityManager.flush();
        entityManager.clear();

        Book retrievedBook = bookRepo.findById(testBook1.getId()).get();
        Author retrievedAuthor1 = authorRepo.findById(testAuthor1.getId()).get();
        Author retrievedAuthor2 = authorRepo.findById(testAuthor2.getId()).get();
        assertThat(retrievedBook.getAuthors()).contains(testAuthor1, testAuthor2);
    }

}
