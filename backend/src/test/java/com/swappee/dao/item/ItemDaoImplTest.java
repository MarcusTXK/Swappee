package com.swappee.dao.item;

import com.swappee.domain.item.Item;
import com.swappee.repository.item.ItemRepository;
import com.swappee.utils.exception.BaseDaoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ItemDaoImplTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemDaoImplTest.class);

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemDaoImpl itemDao;

    @Test
    public void findById() throws BaseDaoException {
        Item mockItem = new Item();
        mockItem.setId(1L);
        mockItem.setName("Hello");
        Mockito.when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(mockItem));
        Item item = itemDao.findById(1L);
        logger.debug("item: {}", item);
        Assert.assertNotNull(item);
    }

//    @Test
//    public void getAll() {
//    }
//
//    @Test
//    public void testGetAll() {
//    }
//
//    @Test
//    public void create() {
//    }
//
//    @Test
//    public void update() {
//    }
//
//    @Test
//    public void delete() {
//    }
}