package com.nixsolutions.emelianov.dao;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.inject.Inject;
import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.nixsolutions.emelianov.dao.RoleDao;
import com.nixsolutions.emelianov.dao.UserDao;
import com.nixsolutions.emelianov.entity.Role;
import com.nixsolutions.emelianov.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/dataset.xml")
public class HibernateUserDaoTest extends DBTestCase {

    @Inject
    @Qualifier("roleDao")
    private RoleDao roledao;

    @Inject
    @Qualifier("userDao")
    private UserDao userDao;

    @Inject
    @Qualifier("role")
    private Role role;

    @Inject
    @Qualifier("user")
    private User user;

    @Override
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new H2DataTypeFactory());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return null;
    }

    @Test
    @ExpectedDatabase(table = "User", value = "/expectedDataSetCreate.xml")
    public void testCreate() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = sdf.parse("01/01/1989");

        role.setName("admin");
        role.setId(1);

        user.setId(3);
        user.setLogin("new_login");
        user.setPassword("new_password");
        user.setFirstName("new_fname");
        user.setLastName("new_lname");
        user.setEmail("new@email");
        user.setBirthday(new java.sql.Date(date.getTime()));
        user.setRole(role);

        userDao.create(user);

    }

    @Test
    @ExpectedDatabase(table = "User", value = "/expectedDataSetUpdate.xml")
    public void testUpdate() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = sdf.parse("01/01/1989");

        role.setName("admin");
        role.setId(1);

        user.setId(2);
        user.setLogin("new_login");
        user.setPassword("new_password");
        user.setFirstName("new_fname");
        user.setLastName("new_lname");
        user.setEmail("new@email");
        user.setBirthday(new java.sql.Date(date.getTime()));
        user.setRole(role);

        userDao.update(user);

    }

    @Test
    @ExpectedDatabase(table = "User", value = "/expectedDataSetRemove.xml")
    public void testRemove() throws Exception {

        role.setName("user");
        role.setId(2);

        user.setId(2);
        user.setRole(role);

        userDao.remove(user);

    }

    @Test
    public void testFindAll() throws Exception {

        List<User> list = userDao.findAll();

        assertNotNull(list);

    }

    @Test
    public void testFindByLogin() throws Exception {

        assertNull(userDao.findByLogin("INVALID LOGIN"));

        String login = "ivanov_login";

        assertEquals(login, userDao.findByLogin(login).getLogin());

    }

    @Test
    public void testFindByEmail() throws Exception {

        assertNull(userDao.findByEmail("INVALID EMAIL"));

        String email = "ivanov@email";

        assertEquals(email, userDao.findByEmail(email).getEmail());

    }

}
