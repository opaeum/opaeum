package test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.jpa.StandaloneJpaEnvironment;

public class TestPersistence extends TestCase{
	public void test(){
		StandaloneJpaEnvironment.getInstance().getPersistence();
	}
}
