package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional // 변경 감지 기능 사용 (더 안전한 방법)
    public Item updateItem(Long itemId, String name, int price, int stockQuantity) { // param: 파리미터로 넘어온 준영속 상태의 엔티티
        Item findItem = itemRepository.findOne(itemId); // 같은 엔티티를 조회한다.
        findItem.setName(name);//데이터를 수정한다.
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        // setter 를 사용하는 것보다 실제 사용되는 비즈니스 로직을 위한 메서드를 따로 만드는 것이 좋다.
        return findItem;
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
