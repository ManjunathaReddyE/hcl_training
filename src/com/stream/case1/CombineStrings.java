package com.stream.case1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CombineStrings {

	public static void main(String[] args) {

		List<String> list = Arrays.asList("Mercury", "Venus", "Mars");

        String combinedString = list.stream().collect(Collectors.joining(","));

        System.out.println("Planets:-" +  combinedString);
		
		
	}

}
