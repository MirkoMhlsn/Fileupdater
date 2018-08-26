package updater;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Worker {
	
	static File workspaceCOM = new File("C:\\Users\\Admin\\workspace");
	static File workspaceSSD = new File("E:\\Mirko Dateien\\Benutzer\\workspace");
	
	static String[] list = new String[32];
	
	public static void listCOM() {
		
		list = workspaceCOM.list();
		
		for(short a = 0; a <= 32; a++) {
			
			System.out.println(list[a]);
			
		}
		
	}
	
	public static boolean listSSD() {
		
		list = workspaceSSD.list();
		boolean validation = true;
		
		try {
			
			for(short a = 0; a <= 28; a++) {
				System.out.println(list[a]);
				validation = true;
				
			}
		}
		catch(Exception e) {
			
			System.out.println("Externe Festplatte konnte nicht gefunden werden");
			validation = false;
			
		}
		
		return validation;
		
	}
	
	public static boolean checkForSSD() {
		
		if(workspaceSSD.exists()) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}
	
	public static void compareProjects() {
		
		String[] com = workspaceCOM.list();
		String[] ssd = workspaceSSD.list();
		
		int[] equal = new int[33];
		int[] unequal = new int[33];
		short a,b;
		short ifTrueCounter = 0;		//ist der Counter für das "equal" array
		short ifFalseCounter = 0;		//ist der Counter für das "unequal" array
		short counterNotEqual = 0;		//Wenn kein Ordner des SSD workspaces mit dem Computer workspace übereinstimmt, wird die Position dieses Ordners in das unequal Array geschrieben, dafür muss der Array-platz-counter um eins erhöht werden, damit die Position in ein leeres Arrayabscnitt geschrieben werden kann.
		
		for(a = 0; a <= 32; a++) {
			
			for(b = 0; b <= 28; b++) {
				
				if(com[a].equals(ssd[b])) {
					
					System.err.println((a + 1) + " " + com[a]  + " " + (b + 1) + " " + ssd[b]);
					equal[ifTrueCounter] = b + 1;
					ifTrueCounter++;
					ifFalseCounter = 0;
					break;
					
				}else {	
					
					ifFalseCounter++;
					
					if(ifFalseCounter == 28) {
						
						unequal[counterNotEqual] = a;
						counterNotEqual++;
						ifFalseCounter = 0;
						
					}
					
				}
				
			}
			
		}
		
		for(short c = 0; c <= 32; c++) {
			
			System.out.println(c + " " + "[" + equal[c] + "]");
			System.err.println(c + " " + "[" + unequal[c] + "]");
			
		}
		
		System.out.println("\n");
		
		for(short c = 0; c <= 32; c++) {
			
			System.out.println(com[unequal[c]]);
			
		}
		
	}
	
	public static void compareArraylist() {
		
		ArrayList<String> com = new ArrayList<String>();
		Collections.addAll(com, workspaceCOM.list());
		
		ArrayList<String> ssd = new ArrayList<String>();
		Collections.addAll(ssd, workspaceSSD.list());
		
		short a,b;
		
		for(a = 0; a < com.size(); a++) {
			
			for(b = 0; b < ssd.size(); b++) {
				
				if(com.get(a).equals(ssd.get(b))) {
					
					System.out.println(ssd.get(b));
					
				}
				
			}
			
		}
		
	}

}