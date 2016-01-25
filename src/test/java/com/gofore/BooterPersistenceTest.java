package com.gofore;

import com.gofore.booter.BooterApplication;
import com.gofore.booter.repository.AccountRepository;
import com.gofore.booter.repository.BookmarkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BooterApplication.class)
public class BooterPersistenceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Test
    public void loadsAccount() {
        assertTrue(accountRepository.findByUsername("janne").isPresent());
    }

    @Test
    public void loadsBookmarks() {
        assertEquals(2, bookmarkRepository.findByAccountUsername("janne").size());
    }

}
