package com.lucas.javase.io.serializable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		String dataFile="saved.data";
		/*try(ObjectOutputStream output=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))){
			//依次写入int，String，Person
			output.writeInt(999);
			output.writeUTF("Hello,world!");
			output.writeObject(new Person("lucas"));
		}*/
		System.out.println("Read ...");
		try(ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))){
			//依次读取int，String,Person
			System.out.println(input.readInt());
			System.out.println(input.readUTF());
			Person p=(Person) input.readObject();
			System.out.println(p);
		}
	}

}
