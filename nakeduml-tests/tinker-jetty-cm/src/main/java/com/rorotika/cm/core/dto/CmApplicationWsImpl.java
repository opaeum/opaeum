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
package com.rorotika.cm.core.dto;

import java.lang.annotation.Annotation;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.jws.WebService;

import org.apache.myfaces.extensions.cdi.core.api.provider.BeanManagerProvider;
import org.nakeduml.environment.ContextRootRequestScoped;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;

import com.rorotika.cm.core.CmApplication;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;

@WebService(endpointInterface = "com.rorotika.cm.core.dto.CmApplicationWs", serviceName = "CmApplicationDto")
public class CmApplicationWsImpl implements CmApplicationWs {

	public String sayHi(String text) {
		System.out.println("sayHi called");
		return "Hello " + text;
	}

	@Override
	public CmApplicationDto getCmApplication() {
		CmApplication cmApplication = getInstance(CmApplication.class, new AnnotationLiteral<ContextRootRequestScoped>() {
		});
		CmApplicationDto copy = new CmApplicationDto(cmApplication.getId(), cmApplication.getDeletedOn(), cmApplication.getCreatedOn(),
				cmApplication.getUpdatedOn(), cmApplication.getUid(), cmApplication.getName(), cmApplication.getDate());
		return copy;
	}

	@Override
	public void updateCmApplication(CmApplicationDto cmApplicationDto) {
		NakedGraph db = getInstance(NakedGraph.class, new AnnotationLiteral<ApplicationScopedDb>() {
		});
		CmApplication cmApplication = getInstance(CmApplication.class, new AnnotationLiteral<ContextRootRequestScoped>() {
		});
		GraphDb.setDb(db);
		db.setTransactionMode(Mode.MANUAL);
		db.startTransaction();
		cmApplication.setName(cmApplicationDto.getName());
//		cmApplication.setDate(cmApplicationDto.getDate());
		db.stopTransaction(Conclusion.SUCCESS);
		GraphDb.remove();		
	}

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