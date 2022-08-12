package com.aca.js.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * 컬렉션 프레임웍 중 MAP을 학습한다
 * 모여 있는 데이터의 구분을 KEY값으로 제어할수 있으며 특히 KEY값을 알면 데이터를 접근할수 있다
 * 데이터 교환에 사용되는 JSON도 사실 MAP유형이다
 * */ 
public class MapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("a", "apple");
		map.put("b", "banana");
		map.put("c", "coconut");
		map.put("m", "mango");
		map.put("p", "pineapple");
		
		System.out.println("총 과일의 수는 "+map.size());
		
		Set<String> set =map.keySet();
		Iterator<String> it =set.iterator();
		while(it.hasNext()) {
			System.out.println(map.get(it.next()));
		}
	}
}
