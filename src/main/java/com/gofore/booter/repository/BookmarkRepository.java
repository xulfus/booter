package com.gofore.booter.repository;

import com.gofore.booter.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Collection<Bookmark> findByAccountUsername(String username);

    Bookmark findByAccountAndId(String username, Long id);
}