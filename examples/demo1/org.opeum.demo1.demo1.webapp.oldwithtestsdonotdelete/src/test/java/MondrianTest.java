import org.opaeum.demo.demo1.Demo1JpaEnvironment;
import org.opaeum.demo.demo1.Demo1OpaeumApplication;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.runtime.organization.IPersonNode;

public class MondrianTest{
	public static void main(String[] args){
		Demo1JpaEnvironment.getInstance();
		Demo1OpaeumApplication a = new Demo1OpaeumApplication();
		IPersonNode ampie = a.findOrCreatePersonByEMailAddress("ampieb@gmail.com");
		new OpaeumRapSession(a, ampie).getMondrianSession().getConnection();
	}
}
