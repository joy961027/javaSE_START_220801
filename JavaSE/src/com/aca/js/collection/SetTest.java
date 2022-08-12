package com.aca.project0811.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*컬렉션 프레임웍중 set을 학습한다.
  set : 순서없는 객체 집합
*/
public class SetTest {

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();//Generic
		set.add("복숭아");
		set.add("오렌지");
		set.add("키위");
		set.add("사과");
		set.add("수박");
		System.out.println("담겨진 과일의 수는 " +set.size());
		// 순서 없는 집합이므로 별도의 도구를 이용해야 한다 이때 지원되는 도구들은 객체들을 
		//일렬로 늘어뜨리는 기능을 가진 enumeration, iterator가 있다.
		System.out.println();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next()); 
		}
		
	}

}
