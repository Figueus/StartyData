package start;

import java.io.IOException;
import java.util.Date;

import model.Connection;
import model.Generator;
import model.User;

public class TestAddingUsers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = new Connection();
		
		long startTime = System.currentTimeMillis();
		String rl = Generator.getRandomString(1);
		for(int i = 0;i<1000;i++){
			User u = new User("test", "test","test"+rl+ i+"@test.io");
			if(i%100==0){
			}
			try {
				con.ExecuteHttpRequestBase(con.CreateAddUserPost(u.getEmail(), u.getPassword(), u.getName())).close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		long pastTime = endTime - startTime;
		
		System.out.println("Start time: " + new Date(startTime));
		System.out.println("End time: " + new Date(endTime));
		System.out.println("Used time: " + new Date(pastTime));
	}

}
