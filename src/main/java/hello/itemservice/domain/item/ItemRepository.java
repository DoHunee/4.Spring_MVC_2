package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 아이템을 저장할 저장소로 사용되는 HashMap, static으로 선언하여 클래스 레벨에서 공유
    private static final Map<Long, Item> store = new HashMap<>(); //static 사용

    // 아이템의 고유 ID를 생성하기 위한 시퀀스, static으로 선언하여 클래스 레벨에서 공유
    private static long sequence = 0L; //static 사용

    /**
     * 새로운 아이템을 저장소에 저장합니다.
     *
     * @param item 저장할 아이템 객체
     * @return 저장된 아이템 객체 (ID가 할당된 상태)
     */
    public Item save(Item item) {
        item.setId(++sequence); // 아이템에 고유 ID를 할당
        store.put(item.getId(), item); // 저장소에 아이템 저장
        return item; // 저장된 아이템 반환
    }

    /**
     * 주어진 ID에 해당하는 아이템을 저장소에서 찾아 반환합니다.
     *
     * @param id 찾고자 하는 아이템의 ID
     * @return 해당 ID를 가진 아이템 객체, 없으면 null
     */
    public Item findById(Long id) {
        return store.get(id); // ID에 해당하는 아이템 반환
    }

    /**
     * 저장소에 있는 모든 아이템을 반환합니다.
     *
     * @return 모든 아이템 리스트
     */
    public List<Item> findAll() {
        return new ArrayList<>(store.values()); // 저장소에 있는 모든 아이템을 리스트로 반환
    }

    /**
     * 주어진 ID의 아이템을 찾아서 해당 아이템의 정보를 업데이트합니다.
     *
     * @param itemId 업데이트할 아이템의 ID
     * @param updateParam 업데이트할 정보가 담긴 객체
     */
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId); // ID로 아이템을 찾음
        findItem.setItemName(updateParam.getItemName()); // 아이템 이름 업데이트
        findItem.setPrice(updateParam.getPrice()); // 가격 업데이트
        findItem.setQuantity(updateParam.getQuantity()); // 수량 업데이트
    }

    /**
     * 저장소를 초기화합니다. 모든 아이템을 삭제합니다.
     */
    public void clearStore() {
        store.clear(); // 저장소를 비움
    }
}
