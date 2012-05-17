package org.opaeum.rap.runtime.cubetree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.olap4j.OlapException;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Dimension;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.cube.AxisEntry;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.cube.DimensionBinding;
import org.opaeum.uim.cube.LevelProperty;
import org.opaeum.uim.cube.MeasureProperty;

public class CubeTreeComposite extends Composite{
	private static final long serialVersionUID = 1L;
	private JavaMetaInfoMap metaInfo;
	private IPersistentObject selectedObject;
	private Tree tree;
	private TreeViewer treeViewer;
	private TreeCube treeCube;
	public interface ICell{
		String getValue();
	}
	public CubeTreeComposite(Composite parent,int style,final CubeQuery cubeQuery,Cube cube,JavaMetaInfoMap map,
			IPersistentObject selectedObject) throws SQLException{
		super(parent, style);
		this.metaInfo = map;
		this.setLayout(new GridLayout(0, true));
		this.selectedObject = selectedObject;
		this.treeCube = this.buildTreeCube(cube, cubeQuery);
		this.tree = new Tree(this, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		setLayout(new GridLayout(1, false));
		tree.addTreeListener(new TreeListener(){
			// Optimization to ensure no rownodes are queried unnecessarily when a column is expanded
			public void treeExpanded(TreeEvent e){
				CubeRowNode node = (CubeRowNode) e.item.getData();
				node.expand();
				try{
					treeCube.populateExpandedRows(node.getExpandedChildren());
				}catch(SQLException e1){
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			public void treeCollapsed(TreeEvent e){
				CubeRowNode node = (CubeRowNode) e.item.getData();
				node.collapse();
			}
		});
		refreshTree();
	}
	public void refreshTree(){
		for(TreeColumn treeColumn:tree.getColumns()){
			treeColumn.dispose();
		}
		this.treeViewer = new TreeViewer(tree);
		this.treeViewer.setContentProvider(new CubeTreeContentProvider());
		this.treeViewer.setInput(treeCube.getRows());
		TreeViewerColumn firstColumn = new TreeViewerColumn(treeViewer, SWT.NONE, 0);
		firstColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element){
				if(element instanceof CubeRowNode){
					return ((CubeRowNode) element).member.getName();
				}else{
					return "";
				}
			}
			public Image getImage(final Object element){
				return Activator.getDefault().getImageRegistry().get(Activator.IMG_PROJECT);
			}
		});
		firstColumn.getColumn().setWidth(200);
		firstColumn.getColumn().setResizable(true);
		int i = 1;
		for(final CubeColumnNode cubeColumnNode:treeCube.getAllExpandedColumns()){
			for(Measure measure:cubeColumnNode.getMeasures()){
				TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.NONE, i++);
				column.getColumn().setWidth(200);
				column.setLabelProvider(new CubeCellLabelProvider(cubeColumnNode, measure));
				column.setEditingSupport(new ColumnHeaderEditingSupport(treeViewer){
					@Override
					protected void flipExpandedState(ColumnHeaderRow row){
						if(row.shouldDisplay(cubeColumnNode)){
							if(cubeColumnNode.expanded){
								cubeColumnNode.collapse();
							}else{
								cubeColumnNode.expand();
								try{
									treeCube.populateExpandedColumns(cubeColumnNode.getExpandedChildren());
								}catch(SQLException e1){
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							refreshTree();
						}
					}
				});
			}
		}
		tree.layout();
		layout();
	}
	private Member getMemberToFilterBy(Cube cube) throws OlapException{
		for(Dimension dimension:cube.getDimensions()){
			for(Hierarchy hierarchy:dimension.getHierarchies()){
				for(Level level:hierarchy.getLevels()){
					if(isOnLevel(selectedObject, level)){
						for(Member member:level.getMembers()){
							if(member.getName().equals(selectedObject.getName())){
								return member;
							}
						}
					}
				}
			}
		}
		return null;
	}
	private boolean isOnLevel(IPersistentObject selectedObject2,Level level){
		return level.getName().equals(IntrospectionUtil.getOriginalClass(selectedObject2).getSimpleName());
	}
	private String toLevelName(LevelProperty levelProperty){
		Class<?> baseType = metaInfo.getTypedElement(levelProperty.getUmlElementUid()).getBaseType();
		String simpleName = baseType.getSimpleName();
		return simpleName;
	}
	private String toDimensionName(DimensionBinding dimensionBinding){
		JavaTypedElement te = metaInfo.getTypedElement(dimensionBinding.getUmlElementUid());
		String name = te.getName();
		if(dimensionBinding.getNext() != null){
			return name + "." + toDimensionName(dimensionBinding.getNext());
		}else{
			return name + ":" + te.getBaseType().getSimpleName();
		}
	}
	private String toDimensionName(PropertyRef ref){
		JavaTypedElement typedElement = metaInfo.getTypedElement(ref.getUmlElementUid());
		String name = typedElement.getName();
		if(ref.getNext() != null){
			return name + "." + toDimensionName(ref.getNext());
		}else{
			return name + ":" + typedElement.getBaseType().getSimpleName();
		}
	}
	private TreeCube buildTreeCube(Cube cube,CubeQuery q) throws OlapException{
		CubeFilter filter = new CubeFilter(getMemberToFilterBy(cube));
		TreeCube result = new TreeCube(cube);
		LevelHolder rowLevelHolder = buildRootLevelHolder(cube, q.getRowAxis());
		List<Member> rowMembers = getMembersOnFirstLevel(cube, q.getRowAxis().get(0));
		for(Member member:rowMembers){
			result.addRowNode(rowLevelHolder, member, filter);
		}
		LevelHolder columnLevelHolder = buildRootLevelHolder(cube, q.getColumnAxis());
		List<Member> columnMembers = getMembersOnFirstLevel(cube, q.getColumnAxis().get(0));
		List<Measure> measures = findIncludedMeasures(cube, q);
		for(Member member:columnMembers){
			result.addColumnNode(columnLevelHolder, member, filter, measures);
		}
		return result;
	}
	private List<Measure> findIncludedMeasures(Cube cube,CubeQuery q){
		List<Measure> measures = new ArrayList<Measure>();
		for(Measure measure:cube.getMeasures()){
			for(MeasureProperty measureProperty:q.getMeasures()){
				if(measure.getName().equals(metaInfo.getTypedElement(measureProperty.getUmlElementUid()).getName())){
					measures.add(measure);
				}
			}
		}
		return measures;
	}
	private LevelHolder buildRootLevelHolder(Cube cube,EList<? extends AxisEntry> rowAxis){
		LevelHolder rowLevelHolder = null;
		for(AxisEntry rowAxisEntry:rowAxis){
			Dimension dimension = cube.getDimensions().get(toDimensionName(rowAxisEntry.getDimensionBinding()));
			for(LevelProperty levelProperty2:rowAxisEntry.getLevelProperty()){
				Level level = dimension.getHierarchies().get(0).getLevels().get(toLevelName(levelProperty2));
				if(level != null){
					if(rowLevelHolder == null){
						rowLevelHolder = new LevelHolder(level);
					}else{
						rowLevelHolder.setNext(new LevelHolder(level));
						rowLevelHolder = rowLevelHolder.getNext();
					}
				}
			}
		}
		return rowLevelHolder;
	}
	private List<Member> getMembersOnFirstLevel(Cube cube,AxisEntry rowAxisEntry) throws OlapException{
		Dimension dimension = cube.getDimensions().get(toDimensionName(rowAxisEntry.getDimensionBinding()));
		Level level = dimension.getHierarchies().get(0).getLevels().get(toLevelName(rowAxisEntry.getLevelProperty().get(0)));
		List<Member> members = level.getMembers();
		return members;
	}
}
