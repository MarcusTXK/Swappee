package com.swappee.repository.item;

import com.swappee.domain.item.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemRepositoryTest.class);

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void testGetOne() {
        Optional<Item> item = itemRepository.findById(1L);
        logger.info("testGetOne - item: {}", item);
        assertNotNull(item);
    }
}