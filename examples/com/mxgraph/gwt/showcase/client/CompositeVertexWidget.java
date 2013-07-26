package com.mxgraph.gwt.showcase.client;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.SimplePanel;
import com.mxgraph.gwt.client.handler.mxGraphHandler.GetInitialCellForEventCallback;
import com.mxgraph.gwt.client.handler.mxGraphHandler.ShouldRemoveCellsFromParentCallback;
import com.mxgraph.gwt.client.model.mxCell;
import com.mxgraph.gwt.client.model.mxGeometry;
import com.mxgraph.gwt.client.model.mxGraphModel;
import com.mxgraph.gwt.client.model.mxICell;
import com.mxgraph.gwt.client.model.mxPoint;
import com.mxgraph.gwt.client.util.mxMouseEvent;
import com.mxgraph.gwt.client.view.mxGraph;
import com.mxgraph.gwt.client.view.mxGraph.IsCellLockedCallback;
import com.mxgraph.gwt.client.view.mxGraph.PanGraphCallback;
import com.mxgraph.gwt.client.view.mxGraphView.SetTranslateCallback;

public class CompositeVertexWidget extends AbstractContentWidget
{

	public CompositeVertexWidget(String name, Showcase showcase)
	{
		super(name, showcase);
		description = "Shows a couple of ways of creating a composite vertex. Also demonstrates how to limit panning. Use left mouse button to pan around.";
	}

	@Override
	public void injectContent(SimplePanel panel)
	{
		final mxGraph graph = new mxGraph();
		panel.setWidget(graph);
		
		Examples.loadDefaultStyle(graph);
		graph.getElement().getStyle().setBackgroundImage("url('images/grid.gif')");
		graph.getElement().getStyle().setHeight(100, Unit.PCT);
		graph.setFoldingEnabled(false);
		graph.setConstrainChildren(false);
		graph.setExtendParents(false);
		graph.setExtendParentsOnAdd(false);
		
		graph.getPanningHandler().setUseLeftButtonForPanning(true);
		graph.setPanning(true);
		graph.getView().setScale(1);

		//limits the panning to 200px in each direction
		graph.setPanGraphCallback(new PanGraphCallback()
		{
			@Override
			public void invoke(int dx, int dy, PanGraphCallback callback)
			{
				mxPoint tr = graph.getView().getTranslate();
				double scale = graph.getView().getScale();
				double trX = tr.getX() * scale;
				double trY = tr.getY() * scale;
				dx = (int) (Math.max(0, Math.min(200 * scale, dx + (int)trX)) - (int)trX);
				dy = (int) (Math.max(0, Math.min(200 * scale, dy + (int)trY)) - (int)trY);
				
				callback.invoke(dx, dy, null);
			}
		});
		//updates the translation as a part of panning limit
		graph.getView().setSetTranslateCallback(new SetTranslateCallback()
		{
			@Override
			public void invoke(int dx, int dy, SetTranslateCallback callback)
			{
				dx = Math.max(0, Math.min(600, dx));
				dy = Math.max(0, Math.min(600, dy));
				
				callback.invoke(dx, dy, null);
			}
		});

		mxICell composite1 = new mxCell("Composite #1", new mxGeometry(0, 0, 150, 150), "");
		composite1.setVertex(true);

		mxICell cell = new mxCell("O", new mxGeometry(0, 0, 30, 30), "");
		cell.setVertex(true);
		mxICell imp = graph.importCells(Arrays.asList(composite1), 50, 50, graph.getDefaultParent()).get(0);

		int x = (int) imp.getGeometry().getX();
		int y = (int) imp.getGeometry().getY();

		graph.importCells(Arrays.asList(cell), x + 15, y + 15, imp);
		graph.importCells(Arrays.asList(cell), x + 90, y + 15, imp);
		graph.importCells(Arrays.asList(cell), x + 50, y + 80, imp);

		mxICell composite2 = new mxCell("Composite #2", new mxGeometry(0, 0, 150, 150), "");
		composite2.setVertex(true);

		cell = new mxCell("X", new mxGeometry(0, 0, 30, 30), "ellipse;locked=0");
		cell.setVertex(true);

		imp = graph.importCells(Arrays.asList(composite2), 300, 50, graph.getDefaultParent()).get(0);
		x = (int) imp.getGeometry().getX();
		y = (int) imp.getGeometry().getY();

		graph.importCells(Arrays.asList(cell), x + 15, y + 15, imp);
		graph.importCells(Arrays.asList(cell), x + 90, y + 15, imp);
		graph.importCells(Arrays.asList(cell), x + 50, y + 80, imp);

		mxICell composite3 = new mxCell("Composite #3", new mxGeometry(0, 0, 150, 150), "");
		composite3.setVertex(true);

		cell = new mxCell("Y", new mxGeometry(0, 0, 30, 30), "rounded;");
		cell.setVertex(true);

		imp = graph.importCells(Arrays.asList(composite3), 550, 50, graph.getDefaultParent()).get(0);
		x = (int) imp.getGeometry().getX();
		y = (int) imp.getGeometry().getY();
		int w = (int) imp.getGeometry().getWidth();
		int h = (int) imp.getGeometry().getHeight();

		graph.importCells(Arrays.asList(cell), x - 20, y + 15, imp);
		graph.importCells(Arrays.asList(cell), x - 20, y + h - 45, imp);
		graph.importCells(Arrays.asList(cell), x + w - 10, y + 15, imp);
		graph.importCells(Arrays.asList(cell), x + w - 10, y + h - 45, imp);

		graph.getGraphHandler().setShouldRemoveCellsFromParentCallback(new ShouldRemoveCellsFromParentCallback()
		{

			@Override
			public boolean invoke(mxICell parent, List<mxICell> cells, NativeEvent event, ShouldRemoveCellsFromParentCallback old)
			{
				return !"Composite #2".equals(parent.getValue());
			}
		});

		graph.setIsCellLockedCallback(new IsCellLockedCallback()
		{

			@Override
			public boolean invoke(mxICell cell, IsCellLockedCallback old)
			{
				Map<String, String> style = graph.getCellStyle(cell);
				boolean cellLocked = style.containsKey("locked") && style.get("locked").equals("0");

				return old.invoke(cell, null) || cellLocked;
			}
		});

		graph.getGraphHandler().setGetInitialCellForEventCallback(new GetInitialCellForEventCallback()
		{
			public mxICell invoke(mxMouseEvent me, GetInitialCellForEventCallback old)
			{

				mxGraphModel model = graph.getModel();
				mxICell ps = model.getParent(graph.getSelectionCell());
				mxICell cell = old.invoke(me, null);
				mxICell parent = model.getParent(cell);

				if (!"Composite #3".equals(parent.getValue())) //this makes sure this logic works only for Composite #3, remove this condition to make it work for all cells
				{
					return cell;
				}

				if (ps == null || !ps.equals(cell) && !ps.equals(parent))
				{
					while (!graph.isCellSelected(cell) && model.isVertex(parent))
					{
						cell = parent;
						parent = model.getParent(cell);
					}
				}

				return cell;
			}
		});

	}

}
