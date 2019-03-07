import kareltherobot.*;

public class V5 implements Directions{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		World.setVisible(true);
		
		Robot rabbit = new Robot (1 ,1 , East ,0) ;
		Robot turtle = new Robot (1 ,1 , East ,0) ;
		for ( int i = 0; i < 10; i ++) {
		
		rabbit . move () ;
		turtle . move () ;
		if ( i % 2 == 0) {
			rabbit . move () ;
			}
		}

	}

}
