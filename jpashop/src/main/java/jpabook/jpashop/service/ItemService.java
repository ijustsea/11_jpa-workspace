package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional(readOnly = false)
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long itemId, String name, int price, int stockQuantity){ //bookParam = 준영속 엔티티
        Item findItem = itemRepository.findOne(itemId);//findItem = 영속 엔티티

        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

        return findItem;
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }
}
