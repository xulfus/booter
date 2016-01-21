package com.gofore.booter.controller;



import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.gofore.booter.model.Bookmark;
import com.gofore.booter.service.BookmarkService;
import com.gofore.booter.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
        Bookmark created = bookmarkService.addBookmark(userId, input);
        HttpHeaders httpHeaders = new HttpHeaders();

        Link forOneBookmark = new BookmarkResource(created).getLink("self");
        httpHeaders.setLocation(URI.create(forOneBookmark.getHref()));

        return new ResponseEntity<>(created, httpHeaders, HttpStatus.CREATED);
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

    class BookmarkResource extends ResourceSupport {

        private final Bookmark bookmark;

        public BookmarkResource(Bookmark bookmark) {
            String username = bookmark.getAccount().getUsername();
            this.bookmark = bookmark;
            this.add(new Link(bookmark.getUri(), "bookmark-uri"));
            this.add(linkTo(BookmarkController.class, username).withRel("bookmarks"));
            this.add(linkTo(methodOn(BookmarkController.class, username).readBookmark(username, bookmark.getId())).withSelfRel());
        }

        public Bookmark getBookmark() {
            return bookmark;
        }
    }

}



