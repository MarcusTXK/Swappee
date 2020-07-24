package com.swappee.service.item;

import com.swappee.model.item.ItemViewDTO;
import com.swappee.utils.exception.BaseServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImplTest.class);

    @Autowired
    ItemService itemService;

    @Test
    public void findItemById() {
    }

    @Test
    public void findItemViewById() throws BaseServiceException {
        ItemViewDTO itemViewDTO = itemService.findItemViewById(1L);
        logger.info("findItemViewById - itemViewDTO: {}", itemViewDTO);
        assertNotNull(itemViewDTO);
    }

    @Test
    public void getAll() {
    }

    @Test
    public void testGetAll() {
    }

    @Test
    public void findByUserId() {
    }

    @Test
    public void testFindByUserId() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}