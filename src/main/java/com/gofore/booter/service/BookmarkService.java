package com.gofore.booter.service;

import com.gofore.booter.model.Account;
import com.gofore.booter.model.Bookmark;
import com.gofore.booter.repository.AccountRepository;
import com.gofore.booter.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    private final AccountRepository accountRepository;

    public Bookmark addBookmark(String userId, Bookmark input) {
        System.out.println("userId = " + userId);
        Account account = accountRepository.findByUsername(userId).get();
        Bookmark save = bookmarkRepository.save(new Bookmark(account,
                input.uri, input.description));
        System.out.println("saved bookmark with id "+save.getId());
        return save;
    }

    @PreAuthorize("principal.username == #userId")
    public Bookmark getBookmark(@PathVariable("userId") String userId, @PathVariable Long bookmarkId) {
        return this.bookmarkRepository.findByAccountAndId(userId, bookmarkId);
    }

    @PreAuthorize("principal.username == #userId")
    public Collection<Bookmark> getBookmarks(@PathVariable("userId") String userId) {
        return this.bookmarkRepository.findByAccountUsername(userId);
    }

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository,
                    AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }

}

