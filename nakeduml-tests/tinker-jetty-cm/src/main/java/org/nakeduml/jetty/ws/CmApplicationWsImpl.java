/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
// START SNIPPET: service
package org.nakeduml.jetty.ws;

import java.lang.annotation.Annotation;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.jws.WebService;

import org.apache.myfaces.extensions.cdi.core.api.provider.BeanManagerProvider;

import com.rorotika.cm.core.CmApplication;

@WebService(endpointInterface = "org.nakeduml.jetty.ws.CmApplicationWs", serviceName = "CmApplicationWs")
public class CmApplicationWsImpl implements CmApplicationWs {

	CmApplication cmApplication;

	public String sayHi(String text) {
		System.out.println("sayHi called");
		return "Hello " + text;
	}

//	public CmApplicationDto getCmApp() {
//		cmApplication = getInstance(CmApplication.class, new AnnotationLiteral<ContextRootRequestScoped>() {
//		});
//		CmApplicationDto copy = cmApplication.makeCopyDto();
//		return copy;
//	}

	private BeanManager getBeanManager() {
		return BeanManagerProvider.getInstance().getBeanManager();
	}

	@SuppressWarnings("unchecked")
	private <T> T getInstance(Class<T> type, Annotation... qualifiers) {
		BeanManager beanManager = getBeanManager();
		Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(type, qualifiers));
		CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
		return (T) beanManager.getReference(bean, type, ctx);
	}

}
// END SNIPPET: service
