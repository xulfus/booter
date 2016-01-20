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

    void addBookmark(String userId, Bookmark input) {
        this.validateUser(userId);
        Account account = accountRepository.findByUsername(userId).get();
        bookmarkRepository.save(new Bookmark(account,
                input.uri, input.description));
    }

    @PostAuthorize("returnObject.account.username == principal.username")
    public Bookmark getBookmark(@PathVariable("userId") String userId, @PathVariable Long bookmarkId) {
        this.validateUser(userId);
        return this.bookmarkRepository.findOne(bookmarkId);
    }

    @PreAuthorize("principal.username == #userId")
    public Collection<Bookmark> getBookmarks(@PathVariable("userId") String userId) {
        this.validateUser(userId);
        return this.bookmarkRepository.findByAccountUsername(userId);
    }

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository,
                    AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }

    private void validateUser(String userId) {
        this.accountRepository.findByUsername(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'.");
    }
}

