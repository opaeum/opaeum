import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opeum.demo1.util.Demo1Application;
import org.opeum.demo1.util.Demo1Environment;

public class MondrianTest{
	public static void main(String[] args){
		Demo1Environment instance = Demo1Environment.INSTANCE;
		Demo1Application a = new Demo1Application(null);
		IPersonNode ampie = a.findOrCreatePersonByEMailAddress("ampieb@gmail.com");
		new OpaeumRapSession(a, ampie).getMondrianSession().getConnection();
	}
}
