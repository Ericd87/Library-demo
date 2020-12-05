package org.wecancodeit.libraryjpa2demo.repositories;

import org.wecancodeit.libraryjpa2demo.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
