package com.belatrixsf.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import com.belatrixsf.datajpa.SpringDataJpaConfig;
import com.belatrixsf.datajpa.dao.CustomerManager;
import com.belatrixsf.datajpa.entity.Customer;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    SpringDataJpaConfig.class}, loader = AnnotationConfigContextLoader.class)
public class SpringDataJpaTest {

  @Autowired
  private CustomerManager customerManager;

  @Test
  public void whenCustomerIsAdded_shouldReturnGeneratedId() {
    Customer c1 = new Customer("James", "Gosling", "jgosling@belatrixsf.com");

    long genId = customerManager.addNewCustomer(c1);

    assertThat(genId).isEqualTo(1);
  }


  @Test
  public void whenFindByLastName_shouldReturnNonEmptyList() {
    customerManager.addNewCustomer(new Customer("James", "Gosling", "jgosling@belatrixsf.com"));
    customerManager.addNewCustomer(new Customer("Linus", "Torvalds", "ltorvalds@belatrixsf.com"));
    customerManager.addNewCustomer(new Customer("Dennis", "Ritchie", "dritchie@belatrixsf.com"));
    customerManager.addNewCustomer(new Customer("Ken", "Ritchie", "kritchie@belatrixsf.com"));

    List<Customer> customers = customerManager.findCustomersByLastName("Ritchie");

    assertThat(customers).hasSize(2);
  }

  @Test
  public void whenFindById_shouldReturnCustomer() {
    customerManager.addNewCustomer(new Customer("James", "Gosling", "jgosling@belatrixsf.com"));
    customerManager.addNewCustomer(new Customer("Linus", "Torvalds", "ltorvalds@belatrixsf.com"));
    customerManager.addNewCustomer(new Customer("Dennis", "Ritchie", "dritchie@belatrixsf.com"));
    customerManager.addNewCustomer(new Customer("Ken", "Ritchie", "kritchie@belatrixsf.com"));

    Customer customer = customerManager.findCustomerById(2);

    assertThat(customer).isNotNull();
    assertThat(customer.getFirstName()).isEqualTo("Linus");
    assertThat(customer.getLastName()).isEqualTo("Torvalds");
  }

}
