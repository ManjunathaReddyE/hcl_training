package com.stream.case2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserLog {

	public static void main(String[] args) {

		
		String fileName = "G://CourseCube//HCL-Training//userlog.txt";
		//Stream<String> stream=null;

		//read file into stream, try-with-resources
		try {
			
			Stream<String> stream= Files.lines(Paths.get(fileName));
			
			//int rowcount=(int)rows1.map(x->x.split(",")).filter(x->x.length==14).count();
			//System.out.println("rowcount  :"+rowcount);
			
			List<String> sl= Files.lines(Paths.get(fileName)).sorted((o1, o2) ->o1.compareTo(o2)).collect(Collectors.toList());
			System.out.println("=="+sl.lastIndexOf(sl.size()));
			sl.forEach(System.out::println);
			//stream.filter(x->x.)
			
		
			//stream.forEach(System.out::println);
			//System.out.println("======================================");
			String max_login_time_user =stream.max(Comparator.comparing(String::valueOf)).get();
			System.out.println("user has max login time:"+max_login_time_user);
		

		}catch(Exception e)
		{
			e.getStackTrace();
		}
			//stream.forEach(System.out::println);
			//System.out.println("======================================");
			
		
		
	}

}
