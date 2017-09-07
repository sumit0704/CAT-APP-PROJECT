package com.catapp.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class shellCommands {	
		public static void main(String command) {
			shellCommands obj = new shellCommands();
			//in mac oxs
			// String command = "ping -c 3 goolge.com";
			//in windows
			command = "cmd.exe /c " + command;

			String output = obj.executeCommand(command);

			System.out.println(output);
			// System.out.println("ping2");

		}

		public String executeCommand(String command) {
			
			StringBuffer output = new StringBuffer();
			Process p;
			try {
				p = Runtime.getRuntime().exec(command);
				p.waitFor();
				BufferedReader reader =
	                            new BufferedReader(new InputStreamReader(p.getInputStream()));

	                        String line = "";
				while ((line = reader.readLine())!= null) {
					output.append(line + "\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return output.toString();
		}
}