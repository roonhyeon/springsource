package com.spring.memo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Memo;

@SpringBootTest // 이건 테스트 클래스라는 의미의 어노테이션
public class MemoRepositoryTest {
	
	@Autowired
	private MemoRepository memoRepository;
	
//	@Test // 테스트 메서드는 void로 생성하고, 메서드명은 마음대로
//	public void createMemoTest() { 
//		
////		Memo memo=new Memo();
////		memo.setMemoText("메모 첫번째");
////		
////		// save(): insert, update
////		memoRepository.save(memo);
//		
//		for (int i = 0; i < 100; i++) {
////			Memo memo=new Memo();
////			memo.setMemoText("Memo...."+i);
////			memoRepository.save(memo);
//			
//			// Memo.java의 @Builder의 쓰임새
//			Memo memo=Memo.builder().memoText("Memo..."+i).build();
//			memoRepository.save(memo);
//		}
//		
//		// 바로 위 코드의 콘솔 출력 내용(이 출력문 반복해서 출력됨)
////		Hibernate: 
////		    select
////		        mem_seq.nextval 
////		    from
////		        dual
////		Hibernate: 
////		    insert 
////		    into
////		        memo
////		        (memo_text, mno) 
////		    values
////		        (?, ?)
//		
//	}
	
	// 하나 조회
	@Test
	public void findByMemo() {
		// 조회: findById()
		Optional<Memo> result=memoRepository.findById(90L);
		
		if(result.isPresent()) {
			System.out.println(result.get());
		} // nullpointer 방지
	}
	
	// 콘솔창 출력 내용
//	Hibernate: 
//	    select
//	        memo0_.mno as mno1_0_0_,
//	        memo0_.memo_text as memo_text2_0_0_ 
//	    from
//	        memo memo0_ 
//	    where
//	        memo0_.mno=?
	
	// 전체 조회
//	@Test
//	public void findByMemoList() {
//		// 조회: findById()
//		List<Memo> result=memoRepository.findAll();
//		
//		result.forEach(memo -> System.out.println(memo));
//	}
	
	// 콘솔창: Memo(mno=2, memoText=Memo....0) 100개까지 전체 조회
	
	// 수정
//	@Test
//	public void updateMemo() {
//		// save(): insert, update
//		Memo memo=Memo.builder().mno(80L).memoText("메모 수정").build(); // 80번 수정하기
//		
//		Memo update=memoRepository.save(memo);
//		System.out.println(update);
//	}
	
	// 출력 내용
//	Hibernate: 
//	    update
//	        memo 
//	    set
//	        memo_text=? 
//	    where
//	        mno=?
//	Memo(mno=80, memoText=메모 수정)
	
	@Test
	public void deleteMemo() {
		memoRepository.deleteById(90L); // mno=90번 삭제하기
	}
	
	// 출력 내용(위에 다른 코드들도 항상 select 먼저 해서 mno에 실제 존재하는 번호인지부터 체크하게 된다. 그러고나서 실제 있는 번호일 시 삭제에 들어간다.)
//	Hibernate: 
//	    select
//	        memo0_.mno as mno1_0_0_,
	
//	        memo0_.memo_text as memo_text2_0_0_ 
//	    from
//	        memo memo0_ 
//	    where
//	        memo0_.mno=?
//	Hibernate: 
//	    delete 
//	    from
//	        memo 
//	    where
//	        mno=?

}
