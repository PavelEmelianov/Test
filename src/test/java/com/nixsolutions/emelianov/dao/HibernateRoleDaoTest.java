package com.nixsolutions.emelianov.dao;

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
import com.nixsolutions.emelianov.entity.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/dataset.xml")
public class HibernateRoleDaoTest extends DBTestCase {

    @Inject
    @Qualifier("roleDao")
    private RoleDao roledao;

    @Inject
    @Qualifier("role")
    private Role role;

    @Override
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new H2DataTypeFactory());
    }

    @Test
    @ExpectedDatabase(table = "Role", value = "/expectedDataSetCreate.xml")
    public void testCreateRole() throws Exception {

        role.setName("new_user");
        role.setId(3);
        roledao.create(role);
         
    }

    @Test
    @ExpectedDatabase(table = "Role", value = "/expectedDataSetUpdate.xml")
    public void testUpdateRole() throws Exception {

        role.setName("new_user");
        role.setId(1);
        roledao.update(role);
    
    }

    @Test
    @ExpectedDatabase(table = "Role", value = "/expectedDataSetRemove.xml")
    public void testRemoveRole() throws Exception {

        role.setId(2);
        role.setName("admin");
        roledao.remove(role);
        
    }

    @Test
    public void testFindByName() throws Exception {

        String name = "admin";
        assertEquals(name, roledao.findByName(name).getName());

    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return null;
    }

}