package br.edu.univas.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartApp {
	
	
	public static void main(String[] args) {
		
		
		
		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nome do arquivo a ser lido:");
		String fileName = scanner.nextLine();
		
		String path = System.getenv("CSV_FILE");
		File completePath = new File(path);
		List<String> fileContent = new ArrayList();
		String names = "";
		int opcao;
		for(File f: completePath.listFiles()) {
		if(f.getName().equalsIgnoreCase(fileName+".csv")) {
		 if(f.canRead()) {
		fileContent = readFile(f);
					break;
				}
			}	
		}
		if(fileContent.size() == 0) {
			System.out.println("Arquivo não existente");
		}else {
			opcao= menu(fileContent);
		if(opcao == 0) {
				System.out.println("FIM");
		}else {
			System.out.println(" Matérias: "+ fileContent.get(opcao - 1)+"\n	Digite 9 para sair");
		names = getNames(fileContent.get(opcao - 1));
			}
		createFile(names, path, fileContent.get(opcao - 1));
		}
		scanner.close();
	}
	public static List<String> readFile(File readable) {
		Scanner readLines;
		List<String> fileContent = new ArrayList();
		try {
			readLines = new Scanner(readable);
		while(readLines.hasNextLine()) {
				String host = readLines.nextLine();
				String[] hostA = host.split("\n|;");
		for(String e: hostA) {
					fileContent.add(e);
				}	
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		return fileContent;	
	}
	public static int menu(List<String> subjects) {
		System.out.println(":::CHAMADA:::");
		for(int i = 0; i < subjects.size(); i++) {
			System.out.println("	["+(i + 1)+"] "+subjects.get(i));
		}
		System.out.println("	[0] Sair");
		int opcao;
		do {
			System.out.print(">>");
			opcao= readInt();
		if(opcao == 0) {
				break;
			}
		}while(opcao < 0 || opcao > subjects.size());
		 return opcao;	
	}
	public static int readInt() {
		Scanner scanner = new Scanner(System.in);
		int opcao = scanner.nextInt();
		scanner.nextLine();
		return opcao;
	}
	public static String getNames(String subject) {
		Scanner scanner = new Scanner(System.in);
		String input;
		String names = "";
		do {
			System.out.print(">>");
			input = scanner.nextLine();
		if(input.equals("9")) {
			break;
		}else {
			names += input +"\n";
			}
		}while(true);	
		
		return names;
	}
	public static void createFile(String names, String inpupath, String newName) {
		String path = inpupath + "\\"+fileName(newName);
		File newFile = new File(path);
		try {
			FileWriter writtenFile = new FileWriter(path);
			writtenFile.write(names);
			writtenFile.close();
			saveFile(newFile);
	 } catch (IOException e) {
			
			System.out.println("1");
			System.out.println(e);
		}
	}
	public static void saveFile(File newFile) {
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			System.out.println("2");
			System.out.println(e);
		}
	}
	public static String fileName(String newName) {
		newName = newName.toLowerCase().replace(' ', '_')+"_";
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedCurrentDate = currentDate.format(formatter).replace('-', '_');
		return newName+formattedCurrentDate+".txt";
				
	}
}




	
	
		
		
		
		
		