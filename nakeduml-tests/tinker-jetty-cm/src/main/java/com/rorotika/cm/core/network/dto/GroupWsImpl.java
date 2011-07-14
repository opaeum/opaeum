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
package com.rorotika.cm.core.network.dto;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.jws.WebService;

import org.apache.myfaces.extensions.cdi.core.api.provider.BeanManagerProvider;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;

import com.rorotika.cm.core.CmApplication;
import com.rorotika.cm.core.Hierarchy;
import com.rorotika.cm.core.HierarchyDto;
import com.rorotika.cm.core.network.Group;
import com.rorotika.cm.core.network.NetworkDto;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;

@WebService(endpointInterface = "com.rorotika.cm.core.network.dto.GroupWs", serviceName = "GroupDto")
public class GroupWsImpl implements GroupWs {

	public String sayHi(String text) {
		System.out.println("sayHi called");
		return "Hello " + text;
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

	@Override
	public List<GroupDto> getGroups(Long cmApplicationId) {
		NakedGraph db = getInstance(NakedGraph.class, new AnnotationLiteral<ApplicationScopedDb>() {
		});
		GraphDb.setDb(db);
		db.setTransactionMode(Mode.MANUAL);
		db.startTransaction();
		List<GroupDto> result = new ArrayList<GroupDto>();
		CmApplication cmApplication = new CmApplication(db.getVertex(cmApplicationId));
		Set<Group> groups = cmApplication.getGroup();
		for (Group group : groups) {
			GroupDto groupDto = new GroupDto();
			groupDto.setName(group.getName());
			groupDto.setId(group.getId());
			result.add(groupDto);
		}
		db.stopTransaction(Conclusion.SUCCESS);
		GraphDb.remove();		
		return result;
	}
	
	@Override
	public List<HierarchyDto> getChildren(Long groupId) {
		NakedGraph db = getInstance(NakedGraph.class, new AnnotationLiteral<ApplicationScopedDb>() {
		});
		GraphDb.setDb(db);
		db.setTransactionMode(Mode.MANUAL);
		db.startTransaction();
		List<HierarchyDto> result = new ArrayList<HierarchyDto>();
		Group group = new Group(db.getVertex(groupId));
		Set<Hierarchy> children = group.getChildren();
		for (Hierarchy child : children) {
			NetworkDto networkDto = new NetworkDto();
			networkDto.setName(child.getName());
			result.add(networkDto);
		}
		db.stopTransaction(Conclusion.SUCCESS);
		GraphDb.remove();		
		return result;
	}	

	@Override
	public void updateGroups(List<GroupDto> groups) {
		// TODO Auto-generated method stub
		
	}

}