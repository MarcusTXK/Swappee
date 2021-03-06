package com.swappee.service.item;

import com.swappee.domain.item.Item;
import com.swappee.domain.item.PreferredItem;
import com.swappee.model.item.ItemDTO;
import com.swappee.model.item.ItemHistoryDTO;
import com.swappee.model.item.ItemViewDTO;
import com.swappee.model.item.PreferredItemDTO;
import com.swappee.model.picture.PictureDTO;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.security.SecurityUtil;
import org.aspectj.lang.annotation.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImplTest.class);

    @Autowired
    ItemService itemService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

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
        createItemDTO();
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
    public void create() throws BaseServiceException {
        mockLogin();
        logger.info("logged in user: {}", securityUtil.getAuthenticatedUsername());
        ItemDTO itemDTO = itemService.create(createItemDTO());
        logger.info("itemDTO: {}", itemDTO);
    }

    @Test
    public void update() throws BaseServiceException {
        mockLogin();
        logger.info("logged in user: {}", securityUtil.getAuthenticatedUsername());
        ItemDTO originalItemDTO = itemService.findItemById(4L);
        ItemDTO itemDTO = itemService.update(updateItemDTO(originalItemDTO));
        logger.info("itemDTO: {}", itemDTO);
    }

    @Test
    public void delete() {
    }

    private void mockLogin(){
        //Feign a logged in user
        UserDetails userDetails = this.jwtInMemoryUserDetailsService.loadUserByUsername("MarcusTXK");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private ItemDTO createItemDTO(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setUserId(1L);
        itemDTO.setStatus("OPEN");
        itemDTO.setName("Samsung Galaxy Note 10+");
        itemDTO.setDescription("Its big");
        itemDTO.setBrand("Samsung");
        itemDTO.setNew(false);
        itemDTO.setCategory("Phones");
        itemDTO.setStrict(true);
        itemDTO.setLikes(2L);

        List<String> catStrings = new ArrayList<>(Arrays.asList("Phone", "Game", "Car"));
        itemDTO.setPreferredCats(catStrings);

        PreferredItemDTO preferredItemDTO1 = new PreferredItemDTO();
        preferredItemDTO1.setName("1");
        preferredItemDTO1.setCategory("1");
        preferredItemDTO1.setBrand("1");
        preferredItemDTO1.setNew(true);
        PreferredItemDTO preferredItemDTO2 = new PreferredItemDTO();
        preferredItemDTO2.setName("2");
        preferredItemDTO2.setCategory("2");
        preferredItemDTO2.setBrand("2");
        preferredItemDTO2.setNew(true);
        List<PreferredItemDTO> preferredItemDTOList = new ArrayList<>(Arrays.asList(preferredItemDTO1, preferredItemDTO2));
        itemDTO.setPreferredItems(preferredItemDTOList);

        ItemHistoryDTO itemHistoryDTO1 = new ItemHistoryDTO();
        itemHistoryDTO1.setPrevOwnerUsername("MarcusTXK");
        itemHistoryDTO1.setTradedItemName("1");
        itemHistoryDTO1.setTradedItemId(1L);
        ItemHistoryDTO itemHistoryDTO2 = new ItemHistoryDTO();
        itemHistoryDTO2.setPrevOwnerUsername("MarcusTXK");
        itemHistoryDTO2.setTradedItemName("2");
        itemHistoryDTO2.setTradedItemId(2L);
        List<ItemHistoryDTO> itemHistoryDTOList = new ArrayList<>(Arrays.asList(itemHistoryDTO1, itemHistoryDTO2));
        itemDTO.setItemHistory(itemHistoryDTOList);
        logger.debug("itemDTO: {}", itemDTO);
        return itemDTO;
    }

    private ItemDTO updateItemDTO(ItemDTO itemDTO){
        itemDTO.setUserId(1L);
        itemDTO.setStatus("OPEN");
        itemDTO.setName("Apple Galaxy Note 10+");
        itemDTO.setDescription("Its big");
        itemDTO.setBrand("Apple");
        itemDTO.setNew(false);
        itemDTO.setCategory("Phones");
        itemDTO.setStrict(true);
        itemDTO.setLikes(2L);

        List<String> catStrings = new ArrayList<>(Arrays.asList("Mac", "Book"));
        itemDTO.setPreferredCats(catStrings);

        PreferredItemDTO preferredItemDTO1 = new PreferredItemDTO();
        preferredItemDTO1.setName("4");
        preferredItemDTO1.setCategory("4");
        preferredItemDTO1.setBrand("4");
        preferredItemDTO1.setNew(true);
        List<PreferredItemDTO> preferredItemDTOList = new ArrayList<>(Arrays.asList(preferredItemDTO1));
        itemDTO.setPreferredItems(preferredItemDTOList);

        ItemHistoryDTO itemHistoryDTO1 = new ItemHistoryDTO();
        itemHistoryDTO1.setPrevOwnerUsername("MarcusTXK");
        itemHistoryDTO1.setTradedItemName("1");
        itemHistoryDTO1.setTradedItemId(1L);
        ItemHistoryDTO itemHistoryDTO2 = new ItemHistoryDTO();
        itemHistoryDTO2.setPrevOwnerUsername("MarcusTXK");
        itemHistoryDTO2.setTradedItemName("2");
        itemHistoryDTO2.setTradedItemId(2L);
        List<ItemHistoryDTO> itemHistoryDTOList = new ArrayList<>(Arrays.asList(itemHistoryDTO1, itemHistoryDTO2));
        itemDTO.setItemHistory(itemHistoryDTOList);
        return itemDTO;
    }

    private List<PictureDTO> pictureDTOList() {
        PictureDTO pictureDTO1 = new PictureDTO();
        pictureDTO1.setOrder(0L);
        byte[] bytes1 = {1, 0, 1};
        pictureDTO1.setFileData(bytes1);
        pictureDTO1.setFileName("1");
        pictureDTO1.setContentType("1");
        pictureDTO1.setContentLength(3L);
        pictureDTO1.setDescription("1");

        PictureDTO pictureDTO2 = new PictureDTO();
        pictureDTO2.setOrder(1L);
        byte[] bytes2 = {0, 1, 0, 1};
        pictureDTO2.setFileData(bytes2);
        pictureDTO2.setFileName("2");
        pictureDTO2.setContentType("2");
        pictureDTO2.setContentLength(4L);
        pictureDTO2.setDescription("2");
        return new ArrayList<>(Arrays.asList(pictureDTO1, pictureDTO2));
    }

    private List<PictureDTO> pictureDTOList2() {
        PictureDTO pictureDTO1 = new PictureDTO();
        pictureDTO1.setOrder(0L);
        byte[] bytes1 = {1, 0, 1, 1, 1};
        pictureDTO1.setFileData(bytes1);
        pictureDTO1.setFileName("1new");
        pictureDTO1.setContentType("1");
        pictureDTO1.setContentLength(3L);
        pictureDTO1.setDescription("1");

        PictureDTO pictureDTO2 = new PictureDTO();
        pictureDTO2.setOrder(1L);
        byte[] bytes2 = {0, 1, 0, 1};
        pictureDTO2.setFileData(bytes2);
        pictureDTO2.setFileName("2new");
        pictureDTO2.setContentType("2");
        pictureDTO2.setContentLength(4L);
        pictureDTO2.setDescription("2");

        PictureDTO pictureDTO3 = new PictureDTO();
        pictureDTO3.setOrder(2L);
        byte[] bytes3 = {0, 1, 0, 1};
        pictureDTO3.setFileData(bytes3);
        pictureDTO3.setFileName("3new");
        pictureDTO3.setContentType("3");
        pictureDTO3.setContentLength(4L);
        pictureDTO3.setDescription("3");

        return new ArrayList<>(Arrays.asList(pictureDTO1, pictureDTO2, pictureDTO3));
    }

}