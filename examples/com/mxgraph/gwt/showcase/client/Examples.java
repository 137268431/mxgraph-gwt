package com.mxgraph.gwt.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.mxgraph.gwt.client.mxClient;
import com.mxgraph.gwt.client.io.mxCodec;
import com.mxgraph.gwt.client.util.mxUtils;
import com.mxgraph.gwt.client.view.mxGraph;

public class Examples implements EntryPoint {

	@Override
	public void onModuleLoad() {

		if (!mxClient.isBrowserSupported()) {
			Window.alert("Your browser is not supported.");
		}
		Showcase sc = new Showcase();
		sc.add(new HelloWorldWidget("Hello world", sc));
		sc.add(new HierarchicalLayoutWidget("Hierarchical Layout", sc));
		sc.add(new LabelPositionWidget("Label Position", sc));
		sc.add(new LayersWidget("Layers", sc));
		sc.add(new DynamicLoadingWidget("Dynamic loading", sc));
		sc.add(new DragAndDropWidget("Drag 'n' Drop", sc));
		sc.add(new CustomValidDropTargetWidget("Custom Valid Drop Target", sc));
		sc.add(new CustomValidSourceWidget("Custom Valid Source/Target", sc));
		sc.add(new CompositeVertexWidget("Composite Vertex", sc));
		sc.add(new UserObjectWidget("User object as cell value", sc));
		sc.add(new CustomEditingBox("Custom editing box", sc));
		sc.add(new CustomCellHoverStyle("Custom cell hover style", sc));
		sc.add(new CustomDragPreview("Custom drag preview", sc));
		sc.add(new CollapseExpand("Collapse/Expand", sc));
		
		
		RootLayoutPanel.get().add(sc);
	}
	
	public static void loadDefaultStyle(mxGraph graph) {
		Element node = mxUtils.load("resources/default-style2.xml").getDocumentElement();
		mxCodec decode = new mxCodec(node.getOwnerDocument());
		decode.decode(node, graph.getStylesheet());
	}
}
