package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    // 테스트 대상인 ItemRepository 인스턴스 생성
    ItemRepository itemRepository = new ItemRepository();

    /**
     * 각 테스트 메서드가 끝난 후 저장소를 초기화하는 메서드.
     * 저장소에 남아있는 데이터를 삭제하여 다음 테스트에 영향을 주지 않도록 함.
     */
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    /**
     * 상품 저장 테스트.    * save() 메서드 테스트.
     * 아이템을 저장한 후, 해당 아이템이 저장소에 정상적으로 저장되었는지 확인.
     */
    @Test
    void save() {
        // given - 테스트를 위한 초기 데이터 설정
        Item item = new Item("itemA", 10000, 10);
        
        // when - 테스트 실행 (아이템 저장)
        Item savedItem = itemRepository.save(item);
        
        // then - 테스트 결과 검증 (저장된 아이템이 조회되는지 확인)
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    /**
     * 전체 상품 조회 테스트.
     * findAll() 메서드 테스트.
     * 여러 개의 아이템을 저장한 후, 저장소에 모든 아이템이 정상적으로 조회되는지 확인.
     */
    @Test
    void findAll() {
        // given - 두 개의 아이템 생성 및 저장
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        
        // when - 저장소에서 모든 아이템 조회
        List<Item> result = itemRepository.findAll();
        
        // then - 조회된 아이템 리스트의 크기와 내용 검증
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    /**
     * 
     * 상품 수정 테스트.상품 수정 테스트.
     * updateItem() 메서드 테스트.
     * 기존의 아이템을 업데이트한 후, 아이템의 정보가 정상적으로 수정되었는지 확인.
     */
    @Test
    void updateItem() {
        // given - 아이템 생성 및 저장
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();
        
        // when - 아이템의 정보를 새로운 값으로 업데이트
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);
        Item findItem = itemRepository.findById(itemId);
        
        // then - 업데이트된 아이템의 각 속성값이 예상대로 변경되었는지 확인
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}