package ru.theduke2021.sberbankcrud.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.theduke2021.sberbankcrud.dto.ItemDto;
import ru.theduke2021.sberbankcrud.model.Item;
import ru.theduke2021.sberbankcrud.repository.ItemRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService underTest;

    @Mock
    private ItemRepository itemRepository;


    @Test
    void shouldReturnCorrectItemById() {
        Item mock = new Item();
        mock.setId(3L);
        mock.setTitle("Strawberry");
        mock.setQuantity(200);
        mock.setCreatedAt(LocalDateTime.parse("2024-02-19T15:00:00"));
        mock.setUpdatedAt(LocalDateTime.parse("2024-02-19T15:00:00"));

        when(itemRepository.findById(any())).thenReturn(Optional.of(mock));

        ItemDto expected = ItemDto.builder()
                .id(3L)
                .title("Strawberry")
                .quantity(200)
                .build();

        assertEquals(expected, underTest.getItemById(3L));
    }

    @Test
    void shouldDeleteItemById() {
        Item mock = new Item();

        when(itemRepository.findById(1L)).thenReturn(Optional.of(mock));

        underTest.deleteItemById(1L);

        ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).delete(argument.capture());

        assertEquals(mock, argument.getValue());
    }

    // Should only replace title and quantity, ignoring all other fields
    @Test
    void shouldReplaceItemById() {
        Item item = new Item();
        item.setId(2L);
        item.setTitle("Ice Cream");
        item.setQuantity(2);
        item.setCreatedAt(LocalDateTime.parse("2023-03-19T11:02:13"));
        item.setUpdatedAt(LocalDateTime.parse("2023-05-19T15:47:05"));

        when(itemRepository.findById(any())).thenReturn(Optional.of(item));

        // Intentionally including id field in ItemDto
        // Only title and quantity fields should be replaced, all other fields must be ignored
        ItemDto replacementItem = ItemDto.builder()
                .id(1000L)
                .title("Sweet Ice Cream")
                .quantity(99)
                .build();

        underTest.replaceItemById(2L, replacementItem);

        ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).save(argument.capture());

        Item expected = new Item();
        // Id is 2, not 1000
        expected.setId(2L);
        expected.setTitle("Sweet Ice Cream");
        expected.setQuantity(99);
        // createdAt and updatedAt remained the same
        expected.setCreatedAt(LocalDateTime.parse("2023-03-19T11:02:13"));
        expected.setUpdatedAt(LocalDateTime.parse("2023-05-19T15:47:05"));

        assertEquals(expected, argument.getValue());
    }

    // Should only use title and quantity in ItemDto, ignoring all other fields
    @Test
    void shouldCreateItem() {
        // Returning Item on save to avoid NPE
        when(itemRepository.save(any())).thenReturn(new Item());

        // id must be ignored
        ItemDto itemDto = ItemDto.builder()
                .id(2000L)
                .title("Peanuts")
                .quantity(0)
                .build();

        underTest.createItem(itemDto);

        ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).save(argument.capture());

        Item expected = new Item();
        expected.setTitle("Peanuts");
        expected.setQuantity(0);

        assertEquals(expected, argument.getValue());

    }

}
