package com.gofore;

import com.gofore.booter.BooterApplication;
import com.gofore.booter.service.BookmarkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BooterApplication.class)
public class MethodSecurityTest {

    @Autowired
    BookmarkService bookmarkService;

    @Test
    @WithMockUser("janne")
    public void user_can_retrieve_her_own_bookmarks() {
        assertNotNull(bookmarkService.getBookmarks("janne"));
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser("hanne")
    public void user_cannot_retrieve_other_bookmarks() {
        bookmarkService.getBookmarks("janne");
    }


}
