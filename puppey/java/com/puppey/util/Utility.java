package com.puppey.util;

public class Utility {
    
    public Utility(){
        
    }

    public static String slugify(String name) {
        return name.toLowerCase().replaceAll("\\s", "-").replaceAll("\\.", "").replaceAll("'", "");
    }
    
    public static int getCurrentTime(){
    	return (int) (System.currentTimeMillis() / 1000);
    }
}
