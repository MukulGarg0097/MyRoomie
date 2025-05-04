package com.myRoomie.Utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetAndListUtil {

	 public static <T> List<T> convertSetToList(Set<T> set) 
	    {
		 try {
		        List<T> list = new ArrayList<>(set); 
		        return list; 
		
		} catch (Exception e) {
			 return null; 
		}
	    } 
	 
	 public static <T> Set<T> convertListToSet(List<T> list) 
	    { 
		 try {
			    return new HashSet<>(list); 
		} 
		catch (Exception e) {
			 return null; 
		}
	    } 
}