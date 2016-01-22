package com.gofore;

import com.gofore.booter.BooterApplication;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BooterApplication.class)
@WebAppConfiguration
public class BookmarkApplicationRestITest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void user_can_retrieve_her_own_bookmarks() {
        given().mockMvc(mvc).auth().with(user("janne").password("none"))
                .when().get("/janne/bookmarks")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("", hasSize(2));
    }

    @Test
    public void user_cannot_retrieve_other_bookmarks() {
        given().mockMvc(mvc).auth().with(httpBasic("janne", "none")).when().get("/manne/bookmarks").then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void unauthorized_user_cannot_retrieve_bookmarks() {
        given().mockMvc(mvc).auth().with(httpBasic("janne", "gone")).when().get("/janne/bookmarks").then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void can_add_new_bookmark() {
        given().mockMvc(mvc).auth().with(httpBasic("janne", "none")).body("{\"uri\":\"https://gofore.com\",\"description\":\"go!\"}").contentType(ContentType.JSON).when().post("/janne/bookmarks").then().statusCode(HttpStatus.SC_CREATED);
    }

}
