package org.opaeum.metamodel.statemachines.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.opaeum.metamodel.statemachines.INakedRegion;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.IRegionOwner;

public class RegionOwnerUtil{
	public static boolean isAncestorOf(IRegionOwner one,IRegionOwner two){
		if(one.equals(two)){
			return true;
		}else if(one.getContainer() == null){
			return true;
		}else if(two.getContainer() == null){
			return false;
		}else{
			return one.isAncestorOf(two.getContainer().getRegionOwner());
		}
	}
	public static IRegionOwner getLeastCommonAncestor(IRegionOwner one,IRegionOwner two){
		if(one.isAncestorOf(two)){
			return one;
		}else if(two.isAncestorOf(one)){
			return two;
		}else{
			return one.getContainer().getRegionOwner().getLeastCommonAncestor(two);
		}
	}
	public static List<INakedRegion> getAllRegionsRecursively(IRegionOwner owner){
		List<INakedRegion> results = new ArrayList<INakedRegion>();
		addSubRegionsTo(results, owner.getRegions());
		return results;
	}
	/**
	 * Recursively adds all subRegions to results, from outer most to inner most
	 */
	private static void addSubRegionsTo(List<INakedRegion> results,List<INakedRegion> regions){
		Iterator<INakedRegion> iter = regions.iterator();
		while(iter.hasNext()){
			INakedRegion region = iter.next();
			results.add(region);
			addAllRegionsTo(results, region.getStates());
		}
	}
	private static void addAllRegionsTo(List<INakedRegion> results,List<INakedState> states){
		for(INakedState state:states){
			addSubRegionsTo(results, state.getRegions());
		}
	}
	public static Set<INakedState> getAllStatesRecursively(IRegionOwner owner){
		Set<INakedState> results = new HashSet<INakedState>();
		for(INakedRegion region:owner.getAllRegions()){
			results.addAll(region.getStates());
		}
		return results;
	}
	public static INakedRegion getTopmostRegionContaining(IRegionOwner owner,INakedState state){
		for(INakedRegion region:owner.getRegions()){
			if(region.contains(state)){
				return region;
			}
		}
		return null;
	}
}
