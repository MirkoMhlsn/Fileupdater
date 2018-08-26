package updater;

import java.io.File;

import java.util.ArrayList;
import java.util.Collections;

public class GeneralWorker {
	
//	static File workspaceCOM = new File("C:\\Users\\Admin");
//	static File workspaceSSD = new File("E:\\Mirko Dateien\\Benutzer");
	
	File workspaceCOM;
	File workspaceSSD;
	
	ArrayList<String> com = new ArrayList <String>();
	ArrayList<String> ssd = new ArrayList <String>();
	
	ArrayList<Integer> existSubSSD = new ArrayList<Integer>();
	ArrayList<Integer> existSuperSSD = new ArrayList<Integer>(); //entbehrlich?
	
	ArrayList<ArrayList<Integer>> ebenen = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<String>> ordner = new ArrayList<ArrayList<String>>();
	
	static ArrayList<String> missingFolders = new ArrayList<String>();
	String[] missingFoldersArray;
	
	public GeneralWorker(String pathCOM, String pathSSD) {
		
		this.workspaceCOM = new File(pathCOM);
		this.workspaceSSD = new File(pathSSD);
		
	}
	
	
	
	public GeneralWorker() {
		
		
	}
	
	
	
	public void SSDmissing() {
		
		boolean missing = true;
		short a,b;
		
		short counterEqual = 0;
		short counterUnequal = 0;
		
		ArrayList<Integer> missingSSD = new ArrayList<Integer>();
		
		for(a = 0; a < this.com.size(); a++) {
			
			for(b = 0; b < this.ssd.size(); b++) {
				
				if(this.com.get(a).equals(ssd.get(b))) {
					
					existSubSSD.add(counterEqual, new Integer(a));
					counterEqual++;
					missing = false;
					break;
					
				}	
				
			}
			
			if(missing) {
				
				missingSSD.add(counterUnequal, new Integer(a));
				counterUnequal++;
				
			}
			
			missing = true;
			
		}
		
		for(short c = 0; c < missingSSD.size(); c++) {
			
			System.out.println(missingSSD.get(c) + " " + this.com.get(missingSSD.get(c)));
			
		}
		
	}
	
	
	public void SSDmissing(String folder) {		//Methode mit folder (Methode mit Path's weiter unten)
		
		File methodWorkspaceCOM = new File(workspaceCOM + folder);
		File methodWorkspaceSSD = new File(workspaceSSD + folder);
		
		Collections.addAll(com, methodWorkspaceCOM.list());
		Collections.addAll(ssd, methodWorkspaceSSD.list());
		
		ArrayList<String> subFolderCOM = new ArrayList<String>();
		ArrayList<String> subFolderSSD = new ArrayList<String>();
		
		Collections.addAll(subFolderCOM, methodWorkspaceCOM.list());
		Collections.addAll(subFolderSSD, methodWorkspaceSSD.list());
		
		boolean missing = true;
		short a,b;
		
		short counterEqual = 0;
		short counterUnequal = 0;
		
		ArrayList<Integer> missingSSD = new ArrayList<Integer>();
		
		for(a = 0; a < subFolderCOM.size(); a++) {
			
			for(b = 0; b < subFolderSSD.size(); b++) {
				
				if(subFolderCOM.get(a).equals(subFolderSSD.get(b))) {
								
					existSubSSD.add(counterEqual, new Integer(a));
					
					counterEqual++;
					missing = false;
					break;
					
				}	
				
			}
			
			if(missing) {
				
				missingSSD.add(counterUnequal, new Integer(a));
				counterUnequal++;
				
			}
			
			missing = true;
			
		}
		
		for(short c = 0; c < missingSSD.size(); c++) {
			
			System.out.println(missingSSD.get(c) + " " + subFolderCOM.get(missingSSD.get(c)));
			
		}
		
	}
	
	
	
	public void resetter(int ebene) {
		
		ebenen.add(ebene, new ArrayList<Integer>());
		
		for(short z = 0; z < existSubSSD.size(); z++) {
			
			ebenen.get(ebene).add(z, existSubSSD.get(z));
			
		}
		
		existSubSSD.clear();
		
		
		ordner.add(ebene, new ArrayList<String>());
		
		for(short z = 0; z < com.size(); z++) {
			
			ordner.get(ebene).add(z, com.get(z));
			
		}
		
		com.clear();
		
	}
	
	
	
	public void reverseResetter(int ebene) {
		
		existSubSSD.clear();
		
		for(short z = 0; z < ebenen.get(ebene).size(); z++) {
			
			existSubSSD.add(z, ebenen.get(ebene).get(z));
			
		}
		
		
		com.clear();
		
		for(short z = 0; z < ordner.get(ebene).size(); z++) {
			
			com.add(z, ordner.get(ebene).get(z));
			
		}
		
		
	}
	
	
	
	
	public void SSDmissing(String pathCOM, String pathSSD) {		//Methode mit path´s
		
		File methodWorkspaceCOM = new File(pathCOM);
		File methodWorkspaceSSD = new File(pathSSD);
		
		Collections.addAll(com, methodWorkspaceCOM.list());
		Collections.addAll(ssd, methodWorkspaceSSD.list());
		
		ArrayList<String> subFolderCOM = new ArrayList<String>();
		ArrayList<String> subFolderSSD = new ArrayList<String>();
		
		Collections.addAll(subFolderCOM, methodWorkspaceCOM.list());
		Collections.addAll(subFolderSSD, methodWorkspaceSSD.list());
		
		boolean missing = true;
		short a,b;
		
		short counterEqual = 0;
		short counterUnequal = 0;
		
		ArrayList<Integer> missingSSD = new ArrayList<Integer>();
		
		for(a = 0; a < subFolderCOM.size(); a++) {
			
			for(b = 0; b < subFolderSSD.size(); b++) {
				
				if(subFolderCOM.get(a).equals(subFolderSSD.get(b))) {
								
					existSubSSD.add(counterEqual, new Integer(a));
					
					counterEqual++;
					missing = false;
					break;
					
				}	
				
			}
			
			if(missing) {
				
				missingSSD.add(counterUnequal, new Integer(a));
				counterUnequal++;
				
			}
			
			missing = true;
			
		}
		
		if(missingSSD.size() != 0) {
			
			System.err.println("[" + pathCOM + "] | [" + pathSSD + "]");
			
		}
		
		for(short c = 0; c < missingSSD.size(); c++) {
			
			//System.out.println(missingSSD.get(c) + " " + subFolderCOM.get(missingSSD.get(c)));
			missingFolders.add(subFolderCOM.get(missingSSD.get(c)));
			
		}
		
	}
	
	public ArrayList<String> getMissingFolders() {
		
		return missingFolders;
		
	}
	
	
	public void printFolder(String folderCOM, String folderSSD) {
		
		workspaceCOM = new File(workspaceCOM.getPath() + folderCOM);
		workspaceSSD = new File(workspaceSSD.getPath() + folderSSD);
		
		ArrayList<String> subFolderCOM = new ArrayList<String>();
		ArrayList<String> subFolderSSD = new ArrayList<String>();
		
		Collections.addAll(subFolderCOM, workspaceCOM.list());
		Collections.addAll(subFolderSSD, workspaceSSD.list());
		
		short a;
		
		for (a = 0; a < subFolderCOM.size(); a++) {
			
			System.out.println(subFolderCOM.get(a));
			
		}
		
		System.out.println();
		
		for (a = 0; a < subFolderSSD.size(); a++) {
			
			System.out.println(subFolderSSD.get(a));
			
		}
		
		System.out.println("\n");
		
	}

}
