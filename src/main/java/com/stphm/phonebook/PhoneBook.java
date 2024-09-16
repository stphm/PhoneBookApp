package com.stphm.phonebook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PhoneBook {

	public static final String PHONE_BOOK_FILE_NAME = "D:\\Documents\\phonebook.txt";
	public static Scanner scanner = null;
	
	public static void main(String[] args) throws IOException {
		
		scanner = new Scanner(System.in);

		String userLastName = getUserInput("Entrer un nom de famille : ");
		String userFirstName = getUserInput("Entrer un prénom: ");
		String userPhoneNumber = getUserInput("Entrer un numéro de téléphone: ");
		
		Contact newContact = new Contact(userLastName, userFirstName, userPhoneNumber);
		File phonebookFile = new File(PHONE_BOOK_FILE_NAME);

		System.out.println(newContact.toString());

		appendContactToPhoneBook(phonebookFile, newContact);

		scanner.close();
	}
	
	public static String getUserInput(String userRequest) {

		System.out.println(userRequest);
		return scanner.nextLine();
	}

	public static File getOrCreatePhoneBookFile(String phoneBookFilePath) throws IOException {
		File phoneBookFile = new File(phoneBookFilePath);

		if (phoneBookFile.exists()) {
			return phoneBookFile;
		}

		try {
			phoneBookFile.createNewFile();
			System.out.println("Le fichier a été créer (" + phoneBookFilePath + ").");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Contact appendContactToPhoneBook(File phonebookFile, Contact newContact) throws IOException {
		try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(phonebookFile, true))) {
			fileWriter.append(newContact.toString() + '\n');
			System.out.println("Contact ajouter");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return newContact;
	}
}
	