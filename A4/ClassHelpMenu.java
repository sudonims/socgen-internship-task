import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;

class FileHandler {
	public static boolean writeToFile(String data, Scanner sc) {
		try {
			System.out.printf("Enter file name: ");
			String fileName = sc.next();
			File file = new File(fileName);
			FileWriter write = new FileWriter(file);
			write.write(data);
			write.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static void getFiles() {
		try {
			File dir = Path.of("").toAbsolutePath().toFile();
			String[] files = dir.list(new FilenameFilter() {
				@Override
				public boolean accept(File file, String name) {
					return name.endsWith(".a4file");
				}
			});
			Arrays.asList(files).forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

public class ClassHelpMenu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int option = -1;
		boolean exit = false, recheck;
		String className, out = "", input = "";
		while (!exit) {
			System.out.println("Enter the class Name");
			className = sc.next();

			try {
				Class classObject = Class.forName(className);
				recheck = false;
				while (!recheck) {
					System.out.printf(
							"Enter option\n1. Methods\n2. Class\n3. Subclass\n4. Parent class\n5. Constructors\n6. Data members\n");
					option = sc.nextInt();
					switch (option) {
						case 1:
							out = Arrays.asList(classObject.getMethods()).toString().replace("[", "").replace("]", "").replaceAll(
									", ",
									"\n");

							System.out.println(out);
							break;

						case 2:
							out = classObject.toGenericString();
							System.out.println(out);
							break;

						case 3:
							break;

						case 4:
							out = classObject.getSuperclass().getName();
							System.out.println(out);
							break;

						case 5:
							out = Arrays.asList(classObject.getConstructors()).toString().replace("[", "").replace("]", "")
									.replaceAll(
											", ",
											"\n");

							System.out.println(out);
							break;

						case 6:
							out = Arrays.asList(classObject.getDeclaredFields()).toString().replace("[", "").replace("]", "")
									.replaceAll(
											", ",
											"\n");

							System.out.println(out);
							break;
					}

					System.out.printf(
							"Do you want to see any other info? (yes/no)\n");

					input = sc.next();
					recheck = !input.equals("yes");
				}
				System.out.printf("1. Store info in file\n2. See all previous files\n3. Exit without saving");
				int innerOption = sc.nextInt();
				switch (innerOption) {
					case 1:
						FileHandler.writeToFile(out, sc);
						break;

					case 2:
						FileHandler.getFiles();
						break;

					case 3:
						exit = true;
						break;
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Class doesn't exist");
				continue;
			}
		}

		sc.close();
	}
}
