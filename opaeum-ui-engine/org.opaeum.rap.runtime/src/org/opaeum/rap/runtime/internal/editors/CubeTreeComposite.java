package org.opaeum.rap.runtime.internal.editors;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.olap4j.Axis;
import org.olap4j.CellSet;
import org.olap4j.CellSetAxis;
import org.olap4j.OlapException;
import org.olap4j.Position;
import org.olap4j.layout.RectangularCellSetFormatter;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Member;
import org.olap4j.query.Query;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;
import org.opaeum.name.NameConverter;
import org.opaeum.rap.runtime.cubetree.CubeRow;
import org.opaeum.rap.runtime.cubetree.CubeRowTree;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.cube.AxisEntry;
import org.opaeum.uim.cube.ColumnAxisEntry;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.cube.DimensionBinding;
import org.opaeum.uim.cube.LevelProperty;
import org.opaeum.uim.cube.MeasureProperty;
import org.opaeum.uim.cube.RowAxisEntry;

public class CubeTreeComposite extends Composite{
	private static final long serialVersionUID = 1L;
	private JavaMetaInfoMap metaInfo;
	private IPersistentObject selectedObject;
	private TreeViewer treeViewer;
	public interface ICell{
		String getValue();
	}
	public CubeTreeComposite(Composite parent,int style,final CubeQuery cubeQuery,Cube cube,JavaMetaInfoMap map,
			IPersistentObject selectedObject) throws SQLException{
		super(parent, style);
		this.metaInfo = map;
		this.setLayout(new GridLayout(0,true));
		this.selectedObject = selectedObject;
		Query query = new Query("John", cube);
		RowAxisEntry firstRowDimension = cubeQuery.getRowAxis().get(0);
		LevelProperty levelProperty = firstRowDimension.getLevelProperty().get(0);
		addEntry(cube, query, query.getAxis(Axis.ROWS), firstRowDimension, levelProperty);
		ColumnAxisEntry firstColumnDimension = cubeQuery.getColumnAxis().get(0);
		addEntry(cube, query, query.getAxis(Axis.COLUMNS), firstColumnDimension, firstColumnDimension.getLevelProperty().get(0));
		for(MeasureProperty mp:cubeQuery.getMeasures()){
			QueryAxis rowAxis = query.getAxis(Axis.COLUMNS);
			QueryDimension dimension = query.getDimension("Measures");
			if(!rowAxis.getDimensions().contains(dimension)){
				rowAxis.addDimension(dimension);
			}
			String name = metaInfo.getTypedElement(mp.getUmlElementUid()).getName();
			name = NameConverter.capitalize(mp.getAggregationFormula().getName().toLowerCase()) + "Of" + NameConverter.capitalize(name);
			Level level = dimension.getDimension().getHierarchies().get(0).getLevels().get(0);
			List<Member> members = level.getMembers();
			for(Member member:members){
				if(member.getName().equals(name)){
					dimension.include(member);
				}
			}
		}
		final List<CubeRow> rows = new ArrayList<CubeRow>();
		this.treeViewer = new TreeViewer(this, SWT.BORDER|SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		this.treeViewer.getTree().setLinesVisible(true);
		this.treeViewer.getTree().setHeaderVisible(true);
		this.treeViewer.setContentProvider(new ITreeContentProvider(){
			public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
			}
			public void dispose(){
			}
			public boolean hasChildren(Object element){
				return true;
			}
			public Object getParent(Object element){
				return ((CubeRow)element).parent;
			}
			public Object[] getElements(Object inputElement){
				return rows.toArray();
			}
			public Object[] getChildren(Object parentElement){
				try{
					return ((CubeRow)parentElement).expand().toArray();
				}catch(SQLException e){
					return null;
				}
			}
		});
		query.validate();
		CellSet execute = query.execute();
		RectangularCellSetFormatter formatter = new RectangularCellSetFormatter(false);
		CharArrayWriter chars = new CharArrayWriter();
		formatter.format(execute, new PrintWriter(chars));
		setLayout(new GridLayout(1, false));
		final CellSet cellSet = query.execute();
		CellSetAxis columnAxis = cellSet.getAxes().get(Axis.COLUMNS.axisOrdinal());
		CellSetAxis rowAxis = cellSet.getAxes().get(Axis.ROWS.axisOrdinal());
		TreeViewerColumn firstColumn = new TreeViewerColumn(treeViewer, SWT.NONE,0);
		firstColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element){
				return ((CubeRow)element).member.getName();
			}
			public Image getImage(final Object element){
				return Activator.getDefault().getImageRegistry().get(Activator.IMG_PROJECT);
			}

		});
		firstColumn.getColumn().setWidth(200);
		firstColumn.getColumn().setResizable(true);
		int i = 1;
		for(Position p:columnAxis.getPositions()){
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.NONE,i++);
			column.getColumn().setWidth(200);
			List<Member> members = p.getMembers();
			for(final Member member:members){
				if(member.getDimension().getName().equals(toDimensionName(firstColumnDimension.getDimensionBinding()))){
					column.setLabelProvider(new ColumnLabelProvider(){
						@Override
						public String getText(Object element){
							return ((CubeRow)element).findRowTreeNode(member).value+"";
						}
					});
					break;
				}
			}
		}
		for(Position rowPosition:rowAxis.getPositions()){
			CubeRow row = new CubeRow();
			rows.add(row);
			row.member=rowPosition.getMembers().get(0);
			row.cube=cube;
			for(Position p:columnAxis.getPositions()){
				CubeRowTree tree = new CubeRowTree();
				tree.row=row;
				for(Member member:p.getMembers()){
					if(member.getDimension().getName().equals("Measures")){
						tree.measure=member;
					}else{
						tree.member=member;
					}
					tree.value=cellSet.getCell(p,rowPosition).getFormattedValue();
					row.rootTrees.add(tree);
				}
			}
		}
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL,GridData.FILL,true,true));
		treeViewer.setInput(rows);
		treeViewer.getTree().layout();
		layout();
	}
	private void addEntry(Cube cube,Query query,QueryAxis rowAxis,AxisEntry oh,LevelProperty levelProperty) throws OlapException{
		DimensionBinding dimensionBinding = oh.getDimensionBinding();
		String dimensionName = toDimensionName(dimensionBinding);
		QueryDimension dimension = query.getDimension(dimensionName);
		if(!rowAxis.getDimensions().contains(dimension)){
			rowAxis.addDimension(dimension);
		}
		// EList<LevelProperty> levelProperties = oh.getLevelProperty();
		String simpleName = toLevelName(levelProperty);
		Level level = dimension.getDimension().getHierarchies().get(0).getLevels().get(simpleName);
		Class<?> baseType2 = metaInfo.getTypedElement(levelProperty.getUmlElementUid()).getBaseType();
		if(baseType2.isInstance(selectedObject)){
			for(Member member:level.getMembers()){
				if(member.getName().equals(selectedObject.getName())){
					dimension.include(member);
				}
			}
		}else{
			dimension.include(level);
		}
	}
	private String toLevelName(LevelProperty levelProperty){
		Class<?> baseType = metaInfo.getTypedElement(levelProperty.getUmlElementUid()).getBaseType();
		String simpleName = baseType.getSimpleName();
		return simpleName;
	}
	private String toDimensionName(DimensionBinding dimensionBinding){
		String name = metaInfo.getTypedElement(dimensionBinding.getUmlElementUid()).getName();
		if(dimensionBinding.getNext() != null){
			name = toDimensionName(dimensionBinding.getNext()) + "." + name;
		}
		return name = name + ":" + metaInfo.getTypedElement(dimensionBinding.getUmlElementUid()).getBaseType().getSimpleName();
	}
	private String toDimensionName(PropertyRef ref){
		String name = metaInfo.getTypedElement(ref.getUmlElementUid()).getName();
		if(ref.getNext() != null){
			return name + toDimensionName(ref.getNext());
		}
		return name;
	}
}
