import java.sql.*;
import java.util.Scanner;

public class User {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/test", "", "");
			System.out.println("Driver Connected");

			Statement s = connection.createStatement();
			ResultSet result;

			int option = -1;
			String query;
			while (option != 0) {
				System.out
						.printf(
								"!! WELCOME TO user CRUD Services !!\n1. Registration\n2. Update\n3. Display\n4. Delete\n0. Exit\n");
				option = sc.nextInt();

				switch (option) {
					case 1:
						System.out.printf("Enter firstname, lastname and email respectively\n");
						String first = sc.next(), last = sc.next(), email = sc.next();
						try {
							query = String.format("insert into user (firstName, lastName, email) values('%s', '%s', '%s')", first,
									last,
									email);
							s.executeUpdate(query);
							System.out.println("User Added");

						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("Email already exists");
						}
						break;

					case 2:
						try {
							System.out.printf("Enter email of user to be updated \n");
							email = sc.next();
							System.out.printf("Enter new firstname, lastname and email respectively\n");
							first = sc.next();
							last = sc.next();
							String newEmail = sc.next();

							query = String.format("update user set firstName='%s', lastName='%s', email='%s' where email='%s'", first,
									last, newEmail, email);
							s.executeUpdate(query);
							System.out.printf("User %s updated", email);

						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("Email already exists");
						}
						break;

					case 3:
						System.out.printf("Enter email of user to be displayed \n");
						email = sc.next();
						query = String.format("select * from user where email='%s'", email);

						result = s.executeQuery(query);

						if (!result.next()) {
							System.out.println("Email doesn't exist");
						}
						System.out.printf("UserId: %d\nfirstName: %s\nlastName: %s\nemail: %s\n", result.getInt("userId"),
								result.getString("firstName"), result.getString("lastName"), result.getString("email"));

						result.close();
						result = null;
						break;

					case 4:
						System.out.printf("Enter email of user to be deleted \n");
						email = sc.next();
						query = String.format("delete from user where email='%s'", email);

						s.executeUpdate(query);
						System.out.printf("User %s Deleted\n", email);
						break;

					case 0:
						System.out.println("Exited Successfully");
						break;
				}
			}

			connection.close();
			sc.close();
		} catch (

		SQLException s) {
			System.out.println(s);
		} catch (ClassNotFoundException c) {
			System.out.println(c);
		}
	}
}
