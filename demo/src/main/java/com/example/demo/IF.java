package com.example.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public interface IF<T> {

}
@Component
public class IntIF1 implements IF<Integer>{
}
@Component
public class IntIF2 implements IF<Integer>{
}
@Component
public class StringIF implements IF<Integer>{

/*@Autowired	/*IFインターフェイスを実装したBeanを全て取得する場合
List<IF<?>>ifList;
@Autowired
Map<String,IF<?>>ifMap;	*/

@Bean	/*List,MapのBean定義の実装*/
List<IF<?>>ifList(){
	return Arrays.asList(new IntIF1(),new IntIF2(),new StringIF());
}
@Bean
Map<String,IF<?>>ifMap(){
	Map<String,IF<?>> map=new HashMap<>();
	map.put("intIF1", new IntF1());
	map.put("intIF2", new IntF2());
	map.put("StringIF", new StringIF());
	return map;
	}	
	@Autowired @Qualifier("ifList")
	List<IF<?>>ifList;
	@Autowired @Qualifier("ifMap")
	Map<String,IF<?>>ifMap;
}
