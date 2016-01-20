package com.gofore.booter.controller;

import com.gofore.booter.model.Bookmark;
import com.gofore.booter.service.BookmarkService;
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

        return null;
    }

    @RequestMapping(value = "/{bookmarkId}", method = RequestMethod.GET)
    Bookmark readBookmark(@PathVariable("userId") String userId, @PathVariable Long bookmarkId) {
        return bookmarkService.getBookmark(userId, bookmarkId);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Bookmark> readBookmarks(@PathVariable("userId") String userId) {
        return bookmarkService.getBookmarks(userId);
    }

    @Autowired
    BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }


}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'.");
    }
}

