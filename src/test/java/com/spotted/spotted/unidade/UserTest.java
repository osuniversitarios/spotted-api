package com.spotted.spotted.unidade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spotted.models.User;

/**
 * Testes de unidade para {@link User}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    private User user;

    @Before
    public void setUp() {
        this.user = new User("test@ccc", "test", "");
    }

    /**
     * Testa se dois usuários com mesmo email e username são iguais.
     */
    @Test
    public void testEquals() {
        User user = new User("test@ccc", "test", "abc");
        Assert.assertTrue("Os usuários deveriam ser iguais.", user.equals(this.user));
    }

    /**
     * Testa se dois usuários com usernames diferentes não são iguais.
     */
    @Test
    public void testEqualsDifferentUsernames() {
        User user = new User("test@ccc", "testando", "");
        Assert.assertFalse("Os usuários não deveriam ser iguais.", user.equals(this.user));
    }

    /**
     * Testa se dois usuários com emails diferentes não são iguais.
     */
    @Test
    public void testEqualsDifferentEmails() {
        User user = new User("testando@ccc", "teste", "");
        Assert.assertFalse("Os usuários não deveriam ser iguais.", user.equals(this.user));
    }
}
