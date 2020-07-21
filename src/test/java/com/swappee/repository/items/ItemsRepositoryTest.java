package com.swappee.repository.items;

import com.swappee.domain.items.Items;
import jdk.swing.interop.SwingInterOpUtils;
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
public class ItemsRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemsRepositoryTest.class);

    @Autowired
    ItemsRepository itemsRepository;

    @Test
    public void testGetOne() {
        Optional<Items> item = itemsRepository.findById(1L);
        logger.info("testGetOne - item: {}", item);
        assertNotNull(item);
    }
}