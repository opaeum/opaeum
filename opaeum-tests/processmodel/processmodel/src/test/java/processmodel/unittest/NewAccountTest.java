package processmodel.unittest;

import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.Test;

import processmodel.humancentric.NewAccount;
import processmodel.humancentric.NewAccountState;
import processmodel.humancentric.SalesDepartment;
import processmodel.humancentric.UserInRole;
import processmodel.humancentric.newaccount.VerifyLimits;

public class NewAccountTest {
	@Test
	public void testFlow() {
		NewAccount newAccount = new NewAccount();
		newAccount.setAccountNumber("asdf");
		SalesDepartment salesDepartment = new SalesDepartment();
		UserInRole user1 = new UserInRole();
		user1.setLoad(1);
		user1.setUserName("asdf");
		salesDepartment.addToUser(user1);
		UserInRole user2 = new UserInRole();
		user2.setLoad(2);
		user2.setUserName("sdfg");
		salesDepartment.addToUser(user2);
		UserInRole user3 = new UserInRole();
		user3.setLoad(3);
		user3.setUserName("dfgh");
		salesDepartment.addToUser(user3);
		newAccount.setSalesDepartment(salesDepartment);
		newAccount.execute();
		assert newAccount.isStepActive(NewAccountState.DOWORK);
		newAccount.getDoWork().setLowerLimit(1);
		newAccount.getDoWork().setUpperLimit(100);
		newAccount.getDoWork().complete();
		assert newAccount.isStepActive(NewAccountState.VERIFYLIMITS);
		Set<VerifyLimits> verifyLimits = newAccount.getVerifyLimits();
		for (VerifyLimits vl : verifyLimits) {
			vl.setUpperLimit(100);
			vl.setLowerLimit(0);
			vl.setValid(true);
		}
		assert verifyLimits.size()==2;
		Iterator<VerifyLimits> iterator = verifyLimits.iterator();
		iterator.next().complete();
		assert newAccount.isStepActive(NewAccountState.VERIFYLIMITS);
		iterator.next().complete();
		assert !newAccount.isStepActive(NewAccountState.VERIFYLIMITS);
		
	}
}
