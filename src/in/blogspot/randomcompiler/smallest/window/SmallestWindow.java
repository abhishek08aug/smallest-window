package in.blogspot.randomcompiler.smallest.window;

import java.util.HashMap;
import java.util.Map;

public class SmallestWindow {
    public static void smallestWindow(String input, String chars) {
        if(input == null || chars == null) {
            System.out.println("Invalid input");
            return;
        }
        System.out.println("Finding shortest window for: '" + chars + "' in '" + input + "'");
        Map<Character, Integer> charsCountMap = new HashMap<Character, Integer>();
        int charsCount = 0;
        for(int i=0; i<chars.length(); i++) {
            if(charsCountMap.get(chars.charAt(i)) == null) {
                charsCountMap.put(chars.charAt(i), 1);
                charsCount += 1;
            } else {
                charsCountMap.put(chars.charAt(i), charsCountMap.get(chars.charAt(i))+1);
            }
        }
        int startIndex = -1;
        int endIndex = -1;
        int shortestWinStartIndex = -1;
        int shortestWinEndIndex = -1;
        if(input.length() < charsCount) {
            System.out.println("All chars are not present in input string");
        }
        
        
        Map<Character, Integer> inputCountMap = new HashMap<Character, Integer>();
        int inputCount = 0; 
        boolean allCharsFound = false;
        int shortestWindowSize = Integer.MAX_VALUE;
        for(int i=0; i<=input.length()-1; i++) {
            if(charsCountMap.get(input.charAt(i)) != null) {
                if(startIndex == -1) {
                    startIndex = i;
                }
                if(inputCountMap.get(input.charAt(i)) == null) {
                    inputCountMap.put(input.charAt(i), 1);                    
                } else {
                    inputCountMap.put(input.charAt(i), inputCountMap.get(input.charAt(i))+1);
                }
                if(inputCountMap.get(input.charAt(i)) == charsCountMap.get(input.charAt(i))) {
                    inputCount += 1;
                    if(inputCount == charsCount) {
                        endIndex = i;
                        allCharsFound = true;
                        shortestWindowSize = endIndex - startIndex + 1;
                        shortestWinStartIndex = startIndex;
                        shortestWinEndIndex = endIndex;
                        break;
                    }
                }
                if(inputCountMap.get(input.charAt(i)) == charsCountMap.get(input.charAt(i))+1) {
                    if(input.charAt(i) == input.charAt(startIndex)) {
                        startIndex += 1;
                        while(charsCountMap.get(input.charAt(startIndex)) == null) {
                            startIndex += 1;
                        }
                    }
                }
            }
        }
        if(allCharsFound == false) {
            System.out.println("All chars in " + chars + " do not exist in " + input);
        }
        
        for(int i=endIndex+1; i<input.length(); i++) {
            if(input.charAt(i) == input.charAt(startIndex)) {
                endIndex = i;
                startIndex += 1;
                while(charsCountMap.get(input.charAt(startIndex)) == null || inputCountMap.get(input.charAt(startIndex)) > charsCountMap.get(input.charAt(startIndex))) {
                    if(charsCountMap.get(input.charAt(startIndex)) != null) {
                        inputCountMap.put(input.charAt(startIndex), inputCountMap.get(input.charAt(startIndex)) - 1);
                    }
                    startIndex += 1;
                }
                if(endIndex - startIndex + 1 < shortestWindowSize) {
                    shortestWindowSize = endIndex - startIndex + 1;
                    shortestWinStartIndex = startIndex;
                    shortestWinEndIndex = endIndex;
                }
            } else {
                if(charsCountMap.get(input.charAt(i)) != null) {
                    inputCountMap.put(input.charAt(i), inputCountMap.get(input.charAt(i)) + 1);
                }                
            }
        }
        System.out.println("Shortest window is: " + input.substring(shortestWinStartIndex, shortestWinEndIndex+1));
        System.out.println("Shortest window size is: " + shortestWindowSize);
    }
}
