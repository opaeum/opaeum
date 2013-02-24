import org.opaeum.demo1.util.Demo1Application;
import org.opaeum.demo1.util.Demo1Environment;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.rwt.OpaeumRapSession;

public class MondrianTest{
	public static void main(String[] args){
		Demo1Environment instance = Demo1Environment.INSTANCE;
		Demo1Application a = new Demo1Application(null);
		IPersonNode ampie = a.findOrCreatePersonByEMailAddress("ampieb@gmail.com");
		new OpaeumRapSession(a, ampie).getMondrianSession().getConnection();
	}
}
