package ru.theduke2021.sberbankcrud.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.theduke2021.sberbankcrud.model.Item;

@Repository
public interface ItemRepository extends ListCrudRepository<Item, Long>,
        PagingAndSortingRepository<Item, Long> {
}
