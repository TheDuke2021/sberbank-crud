package ru.theduke2021.sberbankcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.theduke2021.sberbankcrud.dto.ItemDto;
import ru.theduke2021.sberbankcrud.service.ItemService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItems(Pageable pageable) {
        return itemService.getAllItems(pageable);
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void replaceItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        itemService.replaceItemById(id, itemDto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ItemDto createItem(@RequestBody ItemDto itemDto) {
        return itemService.createItem(itemDto);
    }

}
