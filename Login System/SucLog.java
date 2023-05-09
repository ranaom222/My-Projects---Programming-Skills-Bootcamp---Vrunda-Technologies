import java.util.Scanner;

class P1{
	String email1 = "om@gmail.com";
	String pass1 = "om123";
}
public class SucLog{
	public static void main(String args[]){
		
		System.out.print("\n");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter another email: ");
		String email2 = sc.nextLine();
		System.out.print("Enter pass: ");
		String pass2 = sc.nextLine();
		
		System.out.print("\n");
		
		System.out.println("email2: "+email2);
		System.out.println("pass2: "+pass2);
		
		System.out.print("\n");
		
		P1 p = new P1();
		
		System.out.println("email1: "+p.email1);
		System.out.println("pass1: "+p.pass1);
		
		if(p.email1.equals(email2)){
			if(p.pass1.equals(pass2)){
				System.out.println("\nLogin Successfull !");
			}
			else{
				System.out.println("\nWrong Credentials !");
			}
		}
		else{
			System.out.println("\nWrong Credentials !");		
			}
		
	}
}