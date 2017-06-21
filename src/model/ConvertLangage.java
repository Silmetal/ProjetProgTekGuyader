/*package model;

public class ConvertLangage{

	private static String OS;
	private static String PROCES;

	public ConvertLangage(){
		OS = OSValidator.getOS();
		PROCES = OSValidator.getPROCES();
	}


	public static String convert(String requete ,Langage depart, Langage arrivee ){
		
		premiereModif();


		if(OS.equals("win")){
			if(PROCES.equals("32")){
				executeWindows32();
			}
			else if(PROCES.equals("64")){
				executeWindows64();
			}
		}
		else if(OS.equals("osx")){
			if(PROCES.equals("32")){

			}
			else if(PROCES.equals("64")){

			}
		}
		else if(OS.equals("uni")){
			if(PROCES.equals("32")){
				executeLinux32();
			}
			else if(PROCES.equals("64")){
				executeLinux64();
			}
		}
	}


	private static String premiereModif(String requete,Langage depart, Langage arrivee){

		if (depart.toString().equals("oracle") && depart.toString().equals("mysql")) {
			
		}




	}










	private static void executeWindows64(){
		OSValidator.getOS();
		Process myProcess = null;
		try {
			myProcess = Runtime.getRuntime().exec("cmd /c cd ../fichier & start convertW64.bat");
			InputStream myOut = myProcess.getInputStream();
			int myStatus = -1;
			boolean ready = false;

			while (!ready) {
			
				myStatus = myProcess.exitValue();
				ready = true;
			} 
				
			myOut.read();
					
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		catch (IllegalThreadStateException e) {
			
		}
	}


	private static void executeWindows32(){
		OSValidator.getOS();
		Process myProcess = null;
		try {
			myProcess = Runtime.getRuntime().exec("cmd /c cd ../fichier & start convertW32.bat");
			InputStream myOut = myProcess.getInputStream();
			int myStatus = -1;
			boolean ready = false;

			while (!ready) {
			
				myStatus = myProcess.exitValue();
				ready = true;
			} 
				
			myOut.read();
					
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		catch (IllegalThreadStateException e) {
			
		}
	}


	private static void executeLinux32(Langage depart,Langage arrivee){
		OSValidator.getOS();
		Process myProcess = null;
		try {
			myProcess = Runtime.getRuntime().exec("../fichier/sqlines -s="+depart.toString()+" -t="+arrivee.toString()+" -in=../fichier/fichier.sql");
			InputStream myOut = myProcess.getInputStream();
			int myStatus = -1;
			boolean ready = false;

			while (!ready) {
			
				myStatus = myProcess.exitValue();
				ready = true;
			} 
				
			myOut.read();
					
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		catch (IllegalThreadStateException e) {
			
		}
	}

	private static void executeLinux64(Langage depart,Langage arrivee){
		OSValidator.getOS();
		Process myProcess = null;
		try {
			myProcess = Runtime.getRuntime().exec("../fichier/sqlines64 -s="+depart.toString()+" -t="+arrivee.toString()+" -in=../fichier/fichier.sql");
			InputStream myOut = myProcess.getInputStream();
			int myStatus = -1;
			boolean ready = false;

			while (!ready) {
			
				myStatus = myProcess.exitValue();
				ready = true;
			} 
				
			myOut.read();
					
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		catch (IllegalThreadStateException e) {
			
		}
	}
		
}*/