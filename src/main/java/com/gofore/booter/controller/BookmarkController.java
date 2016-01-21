package com.gofore.booter.controller;

import com.gofore.booter.model.Bookmark;
import com.gofore.booter.service.BookmarkService;
import com.gofore.booter.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {

        return null; // TODO
    }

    @RequestMapping(value = "/{bookmarkId}", method = RequestMethod.GET)
    Bookmark readBookmark(@PathVariable("userId") String userId, @PathVariable Long bookmarkId) {
        return bookmarkService.getBookmark(userId, bookmarkId);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Bookmark> readBookmarks(@PathVariable("userId") String userId) {
        return bookmarkService.getBookmarks(userId);
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found")
    @ExceptionHandler(UserNotFoundException.class)
    public void usernameNotFound() {
        // Nothing to do
    }

    @Autowired
    BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

}



