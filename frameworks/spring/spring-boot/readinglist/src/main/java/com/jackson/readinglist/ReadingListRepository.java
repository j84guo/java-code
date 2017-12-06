package com.jackson.readinglist;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// our repository is responsible for persisting a particular type of entity (1 table)
// it is parameterized with the record type and id type
// by extending jpa repository, reading list repository inherits common jpa operations
// note that the interface is automatically implemented
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}