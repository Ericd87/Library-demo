package org.wecancodeit.libraryjpa2demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.libraryjpa2demo.models.Author;
import org.wecancodeit.libraryjpa2demo.models.Book;
import org.wecancodeit.libraryjpa2demo.models.Campus;
import org.wecancodeit.libraryjpa2demo.repositories.AuthorRepository;
import org.wecancodeit.libraryjpa2demo.repositories.BookRepository;
import org.wecancodeit.libraryjpa2demo.repositories.CampusRepository;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class WebLayerTest {

    @MockBean
    private CampusRepository campusRepo;

    @MockBean
    private BookRepository bookRepo;

   @MockBean
    private AuthorRepository authorRepo;


    @Autowired
    private MockMvc mockMvc;



    @Test
    public void campusShouldBeOkAndReturnCampusesViewWithCampusesModelAttribute() throws Exception{
        mockMvc.perform(get("/campuses"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("campusesView"))
                .andExpect(model().attributeExists("campuses"));
    }

     @Test
    public void booksShouldBeOkAndReturnBooksViewWithBooksModelAttribute() throws Exception{
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("booksView"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    public void authorsShouldBeOkAndReturnAuthorsViewWithAuthorsModelAttribute() throws Exception{
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("authorsView"))
                .andExpect(model().attributeExists("authors"));
    }


    @Test
    public void shouldBeOkForASingleCampusEndpointWithCampusViewAndCampusModelAttribute() throws Exception{
        Campus testCampus = new Campus("Columbus");
        when(campusRepo.findCampusByLocation("Columbus")).thenReturn(testCampus);
        mockMvc.perform(get("/campuses/Columbus"))
                .andExpect(status().isOk())
                .andExpect(view().name("campusView"))
                .andExpect(model().attributeExists("campus"));


    }

    @Test
    public void shouldBeOkForASingleBookEndpointWithBookViewAndCampusModelAttribute() throws Exception{
        Campus testCampus = new Campus("Columbus");
        Author testAuthor = new Author("First", "Last");
        Book testBook = new Book("Title", "Description", testCampus, testAuthor);
        when(bookRepo.findById(testBook.getId())).thenReturn(java.util.Optional.of(testBook));
        mockMvc.perform(get("/books/testBook.getId()}"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookView"))
                .andExpect(model().attributeExists("book"));

    }






}
