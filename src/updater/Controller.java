package updater;

import java.io.File;

public class Controller {
	
	static GeneralWorker generalWorker = new GeneralWorker();
	static int ebene = 0;
	
	public static void master(String pathCOM, String pathSSD) {
				
		comparison(pathCOM, pathSSD);
		
		System.out.println("---finished---");
		
		System.out.println(generalWorker.getMissingFolders());
		
		
	}
	
	
	
	private static void comparison(String pathCOM, String pathSSD) {
		
		/***/	//System.err.println("[" + pathCOM + "] | [" + pathSSD + "]");
		
		generalWorker.resetter(ebene);
		generalWorker.SSDmissing(pathCOM, pathSSD);
		
		for(short z = 0; z < generalWorker.existSubSSD.size(); z++) {
			
			String tryPathCOM = pathCOM + "\\" + generalWorker.com.get(generalWorker.existSubSSD.get(z));
			String tryPathSSD = pathSSD + "\\" + generalWorker.com.get(generalWorker.existSubSSD.get(z));
			File superFile = new File(tryPathCOM);
			
			if(superFile.isDirectory()) {
				
				ebene++;
				comparison(tryPathCOM, tryPathSSD);
				
			}

			tryPathCOM = pathCOM;
			tryPathSSD = pathSSD;
			
		}
		
		generalWorker.reverseResetter(ebene);
		ebene--;
		
	}
	
	
	
//	public static void master() {
//		
//		comparison("\\workspace");
//		
//	}
//	
//	
//	
//	private static void comparison(String folder) {
//		
//		/***/	System.err.println("[" + folder + "]");
//		
//		generalWorker.resetter(ebene);
//		generalWorker.SSDmissing(folder);
//		
//		for(short z = 0; z < generalWorker.existSubSSD.size(); z++) {
//			
//			String tryFolder = folder + "\\" + generalWorker.com.get(generalWorker.existSubSSD.get(z));
//			File superFile = new File(generalWorker.workspaceCOM + tryFolder);
//			
//			if(superFile.isDirectory()) {
//				
//				ebene++;
//				comparison(tryFolder);
//				
//			}
//
//			tryFolder = folder;
//			
//		}
//		
//		generalWorker.reverseResetter(ebene);
//		ebene--;
//		
//	}
	
}
