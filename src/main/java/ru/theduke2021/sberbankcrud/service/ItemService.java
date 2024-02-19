package ru.theduke2021.sberbankcrud.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.theduke2021.sberbankcrud.dto.ItemDto;
import ru.theduke2021.sberbankcrud.model.Item;
import ru.theduke2021.sberbankcrud.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private Item findOrThrowItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item with id %d was not found!".formatted(id)));
    }

    public List<ItemDto> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable).stream()
                .map(item -> ItemDto.builder()
                        .id(item.getId())
                        .title(item.getTitle())
                        .quantity(item.getQuantity())
                        .build())
                .toList();
    }

    public ItemDto getItemById(Long id) {
        Item item = findOrThrowItemById(id);

        return ItemDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .quantity(item.getQuantity())
                .build();
    }

    public void deleteItemById(Long id) {
        Item item = findOrThrowItemById(id);

        itemRepository.delete(item);
    }

    public void replaceItemById(Long id, ItemDto newItem) {
        Item item = findOrThrowItemById(id);

        item.setTitle(newItem.getTitle());
        item.setQuantity(newItem.getQuantity());

        itemRepository.save(item);
    }

    public ItemDto createItem(ItemDto itemDto) {
        Item item = new Item();

        item.setTitle(itemDto.getTitle());
        item.setQuantity(itemDto.getQuantity());

        Item savedItem = itemRepository.save(item);
        return ItemDto.builder()
                .id(savedItem.getId())
                .title(savedItem.getTitle())
                .quantity(savedItem.getQuantity())
                .build();
    }
}
