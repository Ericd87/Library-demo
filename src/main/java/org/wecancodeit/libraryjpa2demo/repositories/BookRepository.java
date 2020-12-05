package org.wecancodeit.libraryjpa2demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.libraryjpa2demo.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
