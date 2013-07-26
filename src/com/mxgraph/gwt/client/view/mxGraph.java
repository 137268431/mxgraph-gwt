package com.mxgraph.gwt.client.view;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.HasContextMenuHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Widget;
import com.mxgraph.gwt.client.handler.mxConnectionHandler;
import com.mxgraph.gwt.client.handler.mxGraphHandler;
import com.mxgraph.gwt.client.handler.mxTooltipHandler;
import com.mxgraph.gwt.client.model.mxCell;
import com.mxgraph.gwt.client.model.mxGeometry;
import com.mxgraph.gwt.client.model.mxGraphModel;
import com.mxgraph.gwt.client.model.mxICell;
import com.mxgraph.gwt.client.model.mxPoint;
import com.mxgraph.gwt.client.shape.mxSwimlane;
import com.mxgraph.gwt.client.util.WrapperUtils;
import com.mxgraph.gwt.client.util.mxConstants;
import com.mxgraph.gwt.client.util.mxEventSource;
import com.mxgraph.gwt.client.util.mxMouseEvent;
import com.mxgraph.gwt.client.util.mxPanningHandler;
import com.mxgraph.gwt.client.util.mxPanningManager;
import com.mxgraph.gwt.client.util.mxRectangle;

public class mxGraph extends mxEventSource implements HasContextMenuHandlers
{

	private static mxGraphUiBinder uiBinder = GWT.create(mxGraphUiBinder.class);

	interface mxGraphUiBinder extends UiBinder<Widget, mxGraph>
	{
	}

	/**
	 * Hook for creating the group cell to hold the given array of {@link mxCellState}
	 * 
	 */
	public static interface CreateGroupCellCallback
	{
		mxICell invoke(List<mxICell> cells, CreateGroupCellCallback old);
	}

	public static interface FireMouseEventCallback
	{
		void invoke(String eventName, mxMouseEvent event, Object sender, FireMouseEventCallback old);
	}

	public static interface ClickCallback
	{
		void invoke(mxMouseEvent me, ClickCallback old);
	}

	public static interface DblClickCallback
	{
		void invoke(NativeEvent me, mxICell cell, DblClickCallback old);
	}

	public static interface IsValidDropTargetCallback
	{
		boolean invoke(mxICell cell, List<mxICell> cells, NativeEvent event, IsValidDropTargetCallback old);
	}

	public static interface IsValidSourceCallback
	{
		boolean invoke(mxICell cell, IsValidSourceCallback old);
	}

	public static interface IsValidTargetCallback
	{
		boolean invoke(mxICell cell, IsValidTargetCallback old);
	}

	public static interface IsCellEditableCallback
	{
		boolean invoke(mxICell cell, IsCellEditableCallback old);
	}

	public static interface IsCellFoldableCallback
	{
		boolean invoke(mxICell cell, boolean collapse, IsCellFoldableCallback old);
	}

	public static interface GetTooltipForCellCallback
	{
		String invoke(mxICell cell, GetTooltipForCellCallback old);
	}

	public static interface MoveCellsCallback
	{
		List<mxICell> invoke(List<mxICell> cells, int dx, int dy, boolean clone, mxICell target, NativeEvent event, MoveCellsCallback old);
	}

	public static interface ConvertValueToStringCallback
	{
		String invoke(mxICell cell, ConvertValueToStringCallback old);
	}

	public static interface CellLabelChangedCallback
	{
		void invoke(mxICell cell, Object newValue, boolean autoSize, CellLabelChangedCallback old);
	}

	public static interface GetEditingValueCallback
	{
		Object invoke(mxICell cell, NativeEvent evt, GetEditingValueCallback old);
	}

	public static interface IsCellLockedCallback
	{
		boolean invoke(mxICell cell, IsCellLockedCallback old);
	}

	public static interface IsValidRootCallback
	{
		boolean invoke(mxICell cell, IsValidRootCallback old);
	}
	
	public static interface PanGraphCallback
	{
		void invoke(int dx, int dy, PanGraphCallback callback);
	}

	/**
	 * Class that implements every callback interface. For each callback, instance of this class is initialized with the JavaScriptObject that represents the
	 * default implementation of underlying function. End users are presented with default callback each time they redefine the default implementation of a
	 * function. This is a substitute for super.methodName() syntax.
	 * 
	 */
	@SuppressWarnings("unused")
	private static class DefaultCallback implements GetTooltipForCellCallback, IsCellFoldableCallback, CreateGroupCellCallback, FireMouseEventCallback, ClickCallback, DblClickCallback,
			IsValidDropTargetCallback, IsValidSourceCallback, IsValidTargetCallback, IsCellEditableCallback, MoveCellsCallback, ConvertValueToStringCallback, CellLabelChangedCallback,
			GetEditingValueCallback, IsCellLockedCallback, IsValidRootCallback, PanGraphCallback
	{

		JavaScriptObject graph;

		JavaScriptObject callback;

		public DefaultCallback(mxGraph graph, JavaScriptObject callback)
		{
			this.graph = graph.jso;
			this.callback = callback;
		}

		public native mxICell invoke(List<mxICell> cells, CreateGroupCellCallback old) /*-{
			var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
			var cellJS = this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(
					this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph, [ cellJS ]);
			return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cellJS);
		}-*/;

		public native String invoke(mxICell cell, GetTooltipForCellCallback previous) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS ]);
		}-*/;

		public native boolean invoke(mxICell cell, boolean collapse, IsCellFoldableCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS, collapse ]);
		}-*/;

		public native boolean invoke(mxICell cell, IsCellEditableCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS ]);
		}-*/;

		public native boolean invoke(mxICell cell, IsValidSourceCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS ]);
		}-*/;

		public native boolean invoke(mxICell cell, IsValidTargetCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS ]);
		}-*/;

		public native boolean invoke(mxICell cell, List<mxICell> cells, NativeEvent event, IsValidDropTargetCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS, cellJS, event ]);
		}-*/;

		public native void invoke(NativeEvent me, mxICell cell, DblClickCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph, [ me,
					cellJS ]);

		}-*/;

		public native void invoke(mxMouseEvent me, ClickCallback old) /*-{
			var meJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(me);
			this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ meJS ]);

		}-*/;

		public native void invoke(String eventName, mxMouseEvent event, Object sender, FireMouseEventCallback old) /*-{
			var eventJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(event);
			this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph, [
					eventName, eventJS, sender ]);
		}-*/;

		public native List<mxICell> invoke(List<mxICell> cells, int dx, int dy, boolean clone, mxICell target, NativeEvent event, MoveCellsCallback old) /*-{
			var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
			var targetJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(target);
			var retvalJS = this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(
					this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph, [ cellsJS, dx, dy, clone, targetJS, event ]);
			return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(retvalJS);
		}-*/;

		public native String invoke(mxICell cell, ConvertValueToStringCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS ]);
		}-*/;

		public native void invoke(mxICell cell, Object newValue, boolean autoSize, CellLabelChangedCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph, [
					cellJS, newValue, autoSize ]);
		}-*/;

		public native Object invoke(mxICell cell, NativeEvent evt, GetEditingValueCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS, evt ]);
		}-*/;

		public native boolean invoke(mxICell cell, IsCellLockedCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS ]);
		}-*/;

		public native boolean invoke(mxICell cell, IsValidRootCallback old) /*-{
			var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
			return this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ cellJS ]);

		}-*/;

		@Override
		public native void invoke(int dx, int dy, PanGraphCallback callback) /*-{
			this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::callback.apply(this.@com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::graph,
					[ dx, dy ]);
		}-*/;
		
		

	}

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#getTooltipForCell(mxICell)}
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setGetTooltipForCellCallback(GetTooltipForCellCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getTooltipForCell;//get the default implementation of the function
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell, collapse) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.GetTooltipForCellCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Lcom/mxgraph/gwt/client/view/mxGraph$GetTooltipForCellCallback;)(cellJ, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getTooltipForCell = funct;
	}-*/;

	/**
	 * TODO
	 * 
	 * @param handler
	 */
	public native void setClickCallback(ClickCallback handler) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).click;//get the default implementation of the function
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(me) {
			var meJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(me);
			handler.@com.mxgraph.gwt.client.view.mxGraph.ClickCallback::invoke(Lcom/mxgraph/gwt/client/util/mxMouseEvent;Lcom/mxgraph/gwt/client/view/mxGraph$ClickCallback;)(meJ, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).click = funct;
	}-*/;

	/**
	 * TODO
	 * 
	 * @param handler
	 */
	public native void setDblClickCallback(DblClickCallback handler) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).dblClick;
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(evt, cell) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			handler.@com.mxgraph.gwt.client.view.mxGraph.DblClickCallback::invoke(Lcom/google/gwt/dom/client/NativeEvent;Lcom/mxgraph/gwt/client/model/mxICell;Lcom/mxgraph/gwt/client/view/mxGraph$DblClickCallback;)(evt, cellJ, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).dblClick = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#isValidDropTarget(mxCell, List, NativeEvent)}
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setIsValidDropTargetCallback(IsValidDropTargetCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidDropTarget;
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell, cells, evt) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			var cellsJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(cells);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.IsValidDropTargetCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Ljava/util/List;Lcom/google/gwt/dom/client/NativeEvent;Lcom/mxgraph/gwt/client/view/mxGraph$IsValidDropTargetCallback;)(cellJ, cellsJ, evt, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidDropTarget = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#isValidSource(mxICell) }
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setIsValidSourceCallback(IsValidSourceCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidSource;
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.IsValidSourceCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Lcom/mxgraph/gwt/client/view/mxGraph$IsValidSourceCallback;)(cellJ, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidSource = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#isValidSource(mxICell) }
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setIsValidTargetCallback(IsValidTargetCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidTarget;
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.IsValidTargetCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Lcom/mxgraph/gwt/client/view/mxGraph$IsValidTargetCallback;)(cellJ, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidTarget = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#isCellEditable(mxICell)}
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setIsCellEditableCallback(IsCellEditableCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellEditable;
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.IsCellEditableCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Lcom/mxgraph/gwt/client/view/mxGraph$IsCellEditableCallback;)(cellJ, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellEditable = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#isCellFoldable(mxICell, boolean)}
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setIsCellFoldableCallback(IsCellFoldableCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellFoldable;
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell, collapse) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.IsCellFoldableCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;ZLcom/mxgraph/gwt/client/view/mxGraph$IsCellFoldableCallback;)(cellJ, collapse, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellFoldable = funct;
	}-*/;

	/**
	 * TODO
	 * 
	 * @param callback
	 */
	public native void setFireMouseEventCallback(FireMouseEventCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).fireMouseEvent;
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(evtName, me, sender) {
			var meJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(me);
			var senderJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(sender);
			callback.@com.mxgraph.gwt.client.view.mxGraph.FireMouseEventCallback::invoke(Ljava/lang/String;Lcom/mxgraph/gwt/client/util/mxMouseEvent;Ljava/lang/Object;Lcom/mxgraph/gwt/client/view/mxGraph$FireMouseEventCallback;)(evtName, meJ, senderJ, oldJ);
		};
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).fireMouseEvent = funct;
	}-*/;

	/**
	 * Sets the CreateGroupCellCallback
	 * 
	 * @param callback callback that will perform group creation
	 */
	public native void setCreateGroupCellCallback(CreateGroupCellCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).createGroupCell;//get the default implementation of the function
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cells) {
			var cellsJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(cells);
			var groupCellJ = callback.@com.mxgraph.gwt.client.view.mxGraph$CreateGroupCellCallback::invoke(Ljava/util/List;Lcom/mxgraph/gwt/client/view/mxGraph$CreateGroupCellCallback;)(cellsJ, oldJ);
			return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(groupCellJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).createGroupCell = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#moveCells(List, int, int, boolean, mxICell)}
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setMoveCellsCallback(MoveCellsCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).moveCells;//get the default implementation of the function
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cells, dx, dy, clone, target, evt) {
			var cellsJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(cells);
			var targetJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(target);
			var retvalJ = callback.@com.mxgraph.gwt.client.view.mxGraph.MoveCellsCallback::invoke(Ljava/util/List;IIZLcom/mxgraph/gwt/client/model/mxICell;Lcom/google/gwt/dom/client/NativeEvent;Lcom/mxgraph/gwt/client/view/mxGraph$MoveCellsCallback;)(cellsJ, dx, dy, clone, targetJ, evt, oldJ);
			return @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(retvalJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).moveCells = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#convertValueToString(mxCell)}
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setConvertValueToStringCallback(ConvertValueToStringCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).convertValueToString;//get the default implementation of the function
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.ConvertValueToStringCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Lcom/mxgraph/gwt/client/view/mxGraph$ConvertValueToStringCallback;)(cellJ, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).convertValueToString = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#cellLabelChanged(mxICell, String, boolean)}
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setCellLabelChangedCallback(CellLabelChangedCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).cellLabelChanged;//get the default implementation of the function
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell, value, autoSize) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			callback.@com.mxgraph.gwt.client.view.mxGraph.CellLabelChangedCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Ljava/lang/Object;ZLcom/mxgraph/gwt/client/view/mxGraph$CellLabelChangedCallback;)(cellJ, value, autoSize, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).cellLabelChanged = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#getEditingValue(mxICell, NativeEvent)}
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setGetEditingValueCallback(GetEditingValueCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getEditingValue;//get the default implementation of the function
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell, evt) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.GetEditingValueCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Lcom/google/gwt/dom/client/NativeEvent;Lcom/mxgraph/gwt/client/view/mxGraph$GetEditingValueCallback;)(cellJ, evt, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getEditingValue = funct;
	}-*/;

	/**
	 * Allows user to redefine the behavior of {@link mxGraph#isCellLocked(mxICell)}
	 * 
	 * @param callback a callback containing a new definition
	 */
	public native void setIsCellLockedCallback(IsCellLockedCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellLocked;//get the default implementation of the function
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.IsCellLockedCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Lcom/mxgraph/gwt/client/view/mxGraph$IsCellLockedCallback;)(cellJ, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellLocked = funct;
	}-*/;

	public native void setIsValidRootCallback(IsValidRootCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidRoot;
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);

		var funct = function(cell) {
			var cellJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cell);
			return callback.@com.mxgraph.gwt.client.view.mxGraph.IsValidRootCallback::invoke(Lcom/mxgraph/gwt/client/model/mxICell;Lcom/mxgraph/gwt/client/view/mxGraph$IsValidRootCallback;)(cellJ, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidRoot = funct;
	}-*/;
	
	public native void setPanGraphCallback(PanGraphCallback callback) /*-{
		var old = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).panGraph;
		var oldJ = @com.mxgraph.gwt.client.view.mxGraph.DefaultCallback::new(Lcom/mxgraph/gwt/client/view/mxGraph;Lcom/google/gwt/core/client/JavaScriptObject;)(this, old);
		
		var funct = function(dx , dy) {
			return callback.@com.mxgraph.gwt.client.view.mxGraph.PanGraphCallback::invoke(IILcom/mxgraph/gwt/client/view/mxGraph$PanGraphCallback;)(dx, dy, oldJ);
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).panGraph = funct;
		
	}-*/;

	private native JavaScriptObject createJso(Element container, JavaScriptObject model, String renderHint, JavaScriptObject stylesheet) /*-{
		return new $wnd.mxGraph(container, model, renderHint, stylesheet);
	}-*/;

	protected mxGraph(JavaScriptObject jso)
	{
		super(jso);
	}

	/**
	 * Constructs a graph inside the container defined via UiBinder.
	 * 
	 */
	public @UiConstructor
	mxGraph()
	{
		initWidget(uiBinder.createAndBindUi(this));
		jso = createJso(getWidget().getElement());
	}

	public mxGraph(String container)
	{
		Element cont = Document.get().getElementById(container);
		jso = createJso(cont);
	}

	/**
	 * @param container Container widget that will hold the contents of the graph
	 * @param model {@link mxGraphModel} that constitutes the graph data.
	 * @param renderHint Optional string that specifies the display accuracy and performance. Default is mxConstants.DIALECT_MIXEDHTML (for IE).
	 * @param stylesheet Optional mxStylesheet to be used in the graph.
	 */
	public mxGraph(Widget container, mxGraphModel model, String renderHint, mxStylesheet stylesheet)
	{
		jso = createJso(container != null ? container.getElement() : null, WrapperUtils.unwrap(model), renderHint, WrapperUtils.unwrap(stylesheet));
		if (container != null)
		{
			initWidget(container);
		}
	}

	private native JavaScriptObject createJso(Element container) /*-{
		return new $wnd.mxGraph(container);
	}-*/;

	public native mxCell getDefaultParent()/*-{
		var parent = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getDefaultParent();
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(parent);
	}-*/;

	public native mxGraphModel getModel() /*-{
		var mxGraphModel = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getModel();
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(mxGraphModel);
	}-*/;

	public mxCell insertVertex(mxCell parent, String id, Object value, double x, double y, double width, double height)
	{
		return insertVertex(parent, id, value, x, y, width, height, "");
	}

	public native mxCell insertVertex(mxCell parent, String id, Object value, double x, double y, double width, double height, String style) /*-{
		var mxCell = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).insertVertex(
				@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(parent), id, value, x, y, width, height, style);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(mxCell);
	}-*/;

	public mxICell insertEdge(mxICell parent, String id, Object value, mxICell source, mxICell target)
	{
		return insertEdge(parent, id, value, source, target, "");
	}

	public native mxICell insertEdge(mxICell parent, String id, Object value, mxICell source, mxICell target, String style) /*-{
		var mxCell = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).insertEdge(
				@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(parent), id, value,
				@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(source),
				@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(target), style);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(mxCell);
	}-*/;

	/**
	 * Returns the {@link mxStylesheet} that defines the style.
	 * 
	 * @return
	 */
	public native mxStylesheet getStylesheet() /*-{
		var styleSheet = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getStylesheet();
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(styleSheet);
	}-*/;

	/**
	 * Sets the {@link mxStylesheet} that defines the style.
	 * 
	 * @param stylesheet
	 */
	public native void setStylesheet(mxStylesheet stylesheet) /*-{
		var styleSheetJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(stylesheet);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setStylesheet(styleSheetJS);
	}-*/;

	/**
	 * Returns the cells which may be imported in the given list of cells.
	 * 
	 * @param cells list of cell to get filtered for importable cells
	 * @return list of importable cells
	 */
	public native List<mxICell> getImportableCells(List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var retCellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getImportableCells(cellsJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(retCellsJS);
	}-*/;

	/**
	 * Clones and inserts the given cells into the graph using the move method and returns the inserted cells. This shortcut is used if cells are inserted via
	 * datatransfer.
	 * 
	 * @param cells List of {@link mxCell}S to be moved, cloned or added to the target.
	 * @param dx Integer that specifies the x-coordinate of the vector.
	 * @param dy Integer that specifies the y-coordinate of the vector.
	 * @param target {@link mxCell} that represents the new parent of the cells.
	 * @return
	 */
	public native List<mxICell> importCells(List<mxICell> cells, int dx, int dy, mxICell target) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var targetJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(target);
		var importedCellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).importCells(cellsJS, dx, dy,
				targetJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(importedCellsJS);
	}-*/;

	/**
	 * Specifies if tooltips should be enabled. This implementation updates mxTooltipHandler in tooltipHandler.
	 * 
	 * @param enable Boolean indicating if tooltips should be enabled.
	 */
	public native void setTooltips(boolean enable) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setTooltips(enable);
	}-*/;

	/**
	 * Returns true if graph grid is enabled
	 * 
	 * @return
	 */
	public native boolean isGridEnabled() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isGridEnabled();
	}-*/;

	/**
	 * Specifies if the grid should be enabled.
	 * 
	 * @param enableGrid Boolean indicating if the grid should be enabled.
	 */
	public native void setGridEnabled(boolean enableGrid) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setGridEnabled(enableGrid);
	}-*/;

	/**
	 * Returns true if the <connectionHandler> is enabled.
	 * 
	 * @return
	 */
	public native boolean isConnectable() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isConnectable();
	}-*/;

	/**
	 * Specifies if the graph should allow new connections. This implementation updates <mxConnectionHandler.enabled> in <connectionHandler>.
	 * 
	 * @param connectable Boolean indicating if new connections should be allowed.
	 */
	public native void setConnectable(boolean connectable) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setConnectable(connectable);
	}-*/;

	/**
	 * Returns true if folding (collapse and expand via an image icon in the graph should be enabled). Default is true.
	 * 
	 * @return
	 */
	public native boolean isFoldingEnabled() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).foldingEnabled;
	}-*/;

	/**
	 * Sets new value for folding enabled flag
	 * 
	 * @param foldingEnabled
	 */
	public native void setFoldingEnabled(boolean foldingEnabled) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).foldingEnabled = foldingEnabled;
	}-*/;

	/**
	 * Returns true if a dashed line should be drawn between multiple pages. Default is false.
	 * 
	 * @return
	 */
	public native boolean isPageBreaksVisible() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).pageBreaksVisible;
	}-*/;

	/**
	 * Specifies if a dashed line should be drawn between multiple pages. Default is false. If you change this value while a graph is being displayed then you
	 * should call {@link #sizeDidChange()} to force an update of the display.
	 * 
	 * @param pageBreaksVisible
	 */
	public native void setPageBreaksVisible(boolean pageBreaksVisible) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).pageBreaksVisible = pageBreaksVisible;
	}-*/;

	/**
	 * Returns true if the graph size should be rounded to the next page number in {@link #sizeDidChange()}. This is only used if the graph container has
	 * scrollbars. Default is false.
	 * 
	 * @return
	 */
	public native boolean isPreferPageSize() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).preferPageSize;
	}-*/;

	/**
	 * Sets new value for prefer page size flag
	 * 
	 * @param preferPageSize
	 * @return
	 */
	public native boolean setPreferPageSize(boolean preferPageSize) /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).preferPageSize = preferPageSize;
	}-*/;

	/**
	 * Returns true if the background page should be visible. Default is false.
	 * 
	 * @return
	 */
	public native boolean isPageVisible() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).pageVisible;
	}-*/;

	/**
	 * Sets new value for page visible flag.
	 * 
	 * @param pageVisible
	 */
	public native void setPageVisible(boolean pageVisible) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).pageVisible = pageVisible;
	}-*/;

	/**
	 * Called when the size of the graph has changed. This implementation fires a <size> event after updating the clipping region of the SVG element in
	 * SVG-bases browsers.
	 */
	public native void sizeDidChange() /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).sizeDidChange();
	}-*/;

	/**
	 * Gets the {@link mxGraphHandler} for this graph
	 * 
	 * @return graph handler
	 */
	public native mxGraphHandler getGraphHandler() /*-{
		var graphHandlerJs = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).graphHandler;
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(graphHandlerJs);
	}-*/;

	/**
	 * Returns the Holds the {@link mxGraphView} that caches the <mxCellStates> for the cells.
	 * 
	 * @return
	 */
	public native mxGraphView getView() /*-{
		var graphViewJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).view;
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(graphViewJS);
	}-*/;

	public native mxConnectionHandler getConnectionHandler() /*-{
		var handlerJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).connectionHandler;
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(handlerJS);
	}-*/;

	public native void setConnectionHandler(mxConnectionHandler connectionHandler) /*-{
		var handlerJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(connectionHandler);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).connectionHandler = handlerJS;
	}-*/;

	/**
	 * Returns cell renderer for this graph instance
	 * 
	 * @return instance of {@link mxCellRenderer}
	 */
	public native mxCellRenderer getCellRenderer() /*-{
		var rendererJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).cellRenderer;
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(rendererJS);
	}-*/;

	/**
	 * Returns the dialect to be used for drawing the graph. Possible values are all constants in {@link mxConstants} with a DIALECT-prefix.
	 * 
	 * @return
	 */
	public native String getDialect() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).dialect;
	}-*/;

	/**
	 * 
	 * Sets new value for dialect
	 * 
	 * @param dialect
	 */
	public native void setDialect(String dialect) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).dialect = dialect;
	}-*/;

	/**
	 * Zooms into the graph by <zoomFactor>.
	 */
	public native void zoomIn() /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).zoomIn();
	}-*/;

	/**
	 * Zooms out of the graph by <zoomFactor>.
	 */
	public native void zoomOut() /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).zoomOut();
	}-*/;

	/**
	 * Resets the zoom and panning in the view.
	 */
	public native void zoomActual() /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).zoomActual();
	}-*/;

	/**
	 * Zooms the graph using the given factor.
	 */
	public native void zoom(double zoomFactor) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).zoom(zoomFactor);
	}-*/;

	/**
	 * Scales the graph such that the complete diagram fits into <container> and returns the current scale in the view.
	 */
	public native void fit() /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).fit();
	}-*/;

	/**
	 * 
	 */
	public native void fit(int border, boolean keepOrigin) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).fit(border, keepOrigin);
	}-*/;

	/**
	 * Returns the {@link mxGraphSelectionModel} that contains the selection.
	 * 
	 * @return
	 */
	public native mxGraphSelectionModel getSelectionModel() /*-{
		var selectionModelJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getSelectionModel();
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(selectionModelJS);
	}-*/;

	/**
	 * Returns the list of selected {@link mxCell}S.
	 */
	public native List<mxICell> getSelectionCells() /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getSelectionCells();
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(cellsJS);
	}-*/;

	/**
	 * Returns the number of selected cells.
	 * 
	 */
	public native int getSelectionCount() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getSelectionCount();
	}-*/;

	/**
	 * 
	 * Toggles the boolean value for the given key in the style of the given cells. If no cells are specified, then the selection cells are used. For example,
	 * this can be used to toggle {@link mxConstants#STYLE_ROUNDED} or any other style with a boolean value.
	 * 
	 * @param key String representing the key for the boolean value to be toggled.
	 * @param defaultValue Default boolean value if no value is defined
	 * @param cells Cells whose styles should be modified
	 */
	public native void toggleCellStyles(String key, boolean defaultValue, List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).toggleCellStyles(key, defaultValue, cellsJS);
	}-*/;

	/**
	 * Toggles the boolean value for the given key in the style of the selection cells
	 * 
	 * @param key String representing the key for the boolean value to be toggled.
	 */
	public native void toggleCellStyles(String key) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).toggleCellStyles(key);
	}-*/;

	/**
	 * 
	 * Sets the key to value in the styles of the given cells. This will modify the existing cell styles in-place and override any existing assignment for the
	 * given key. If no cells are specified, then the selection cells are changed. If no value is specified, then the respective key is removed from the styles.
	 * 
	 * 
	 * @param key String representing the key to be assigned.
	 * @param value String representing the new value for the key.
	 * @param cells optional list of {@link mxCell}S to change the style for. Default is the selection cells.
	 */
	public native void setCellStyles(String key, String value, List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setCellStyles(key, value, cellsJS);
	}-*/;

	/**
	 * Toggles the given bit for the given key in the styles of the specified cells.
	 * 
	 * @param key String representing the key to toggle the flag in.
	 * @param flag Integer that represents the bit to be toggled.
	 * @param cells Optional list of {@link mxCell}S to change the style for. Default is the selection cells.
	 */
	public native void toggleCellStyleFlags(String key, int flag, List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).toggleCellStyleFlags(key, flag, cellsJS);
	}-*/;

	/**
	 * Returns the first cell from the list of selected {@link mxCell}S.
	 * 
	 * @return
	 */
	public native mxCell getSelectionCell() /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getSelectionCell()
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(cellJS);
	}-*/;

	/**
	 * Aligns the given cells vertically or horizontally according to the given alignment using the optional parameter as the coordinate.
	 * 
	 * @param align Specifies the alignment. Possible values are all constants in mxConstants with an ALIGN prefix.
	 * @param cells Optional list of {@link mxCell}S to be aligned. Defaults to selected cells if empty.
	 * @param param Optional coordinate for the alignment.
	 * @return aligned cells
	 */
	public native List<mxICell> alignCells(String align, List<mxICell> cells, Double param) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var paramJS = param != null ? param.@java.lang.Double::doubleValue()() : null;
		var alignedCellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).alignCells(align, cellsJS,
				paramJS);
		var alignedCells = @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(alignedCellsJS);
		return cells != null ? cells : alignedCells;
	}-*/;

	/**
	 * Adds the cells into the given group. The change is carried out using <cellsAdded>, <cellsMoved> and <cellsResized>. This method fires
	 * <mxEvent.GROUP_CELLS> while the transaction is in progress. Returns the new group. A group is only created if there is at least one entry in the given
	 * list of cells.
	 * 
	 * @param group {@link mxCell} that represents the target group. If null is specified then a new group is created using {@link #createGroupCell}.
	 * @param border Optional integer that specifies the border between the child area and the group bounds. Default is 0.
	 * @param cells Optional list of {@link mxCell} to be grouped. If null is specified then the selection cells are used.
	 * @return list of grouped cells
	 */
	public native mxCell groupCells(mxCell group, Integer border, List<mxICell> cells) /*-{
		var groupJS = group != null ? @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(group) : null;
		var borderJS = border != null ? border.@java.lang.Integer::intValue()() : null;
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		groupJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).groupCells(groupJS, borderJS, cellsJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(groupJS);
	}-*/;

	/**
	 * Ungroups the given cells by moving the children the children to their parents parent and removing the empty groups. Returns the children that have been
	 * removed from the groups.
	 * 
	 * @param cells List of cells to be ungrouped. If null is specified then the selection cells are used.
	 * @return list of ungrouped cells
	 */
	public native List<mxICell> ungroupCells(List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var ungroupedCellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).ungroupCells(cellsJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(ungroupedCellsJS);
	}-*/;

	/**
	 * Sets the selection cell.
	 * 
	 * @param cell {@link mxCell} to be selected.
	 */
	public native void setSelectionCell(mxCell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setSelectionCell(cellJS);
	}-*/;

	/**
	 * Sets the selection cells.
	 * 
	 * @param cells List of {@link mxCell}S to be selected.
	 */
	public native void setSelectionCells(List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setSelectionCells(cellsJS);
	}-*/;

	/**
	 * Hook for creating the group cell to hold the given array of <mxCells> if no group cell was given to the <group> function.
	 * 
	 * The following code can be used to set the style of new group cells.
	 * 
	 * (code) var graphCreateGroupCell = graph.createGroupCell; graph.createGroupCell = function(cells) { var group = graphCreateGroupCell.apply(this,
	 * arguments); group.setStyle('group');
	 * 
	 * return group; };
	 * 
	 * @param cells
	 * @return
	 */
	public native mxCell createGroupCell(List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var groupCellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).createGroupCell(cellsJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(groupCellJS);
	}-*/;

	/**
	 * Moves the given cells to the front or back. The change is carried out using <cellsOrdered>. This method fires <mxEvent.ORDER_CELLS> while the transaction
	 * is in progress.
	 * 
	 * @param back Boolean that specifies if the cells should be moved to back.
	 * @param cells List of {@link mxCell}S to move to the background. If null is specified then the selection cells are used.
	 * @return
	 */
	public native List<mxICell> orderCells(boolean back, List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var orderedCellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).orderCells(back, cellsJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(orderedCellsJS);
	}-*/;

	/**
	 * Removes the given cells from the graph including all connected edges if includeEdges is true. The change is carried out using <cellsRemoved>. This method
	 * fires <mxEvent.REMOVE_CELLS> while the transaction is in progress. The removed cells are returned as a list.
	 * 
	 * @param cells List of {@link mxCell}s to remove. If null is specified then the selection cells which are deletable are used.
	 * @param includeEdges Optional boolean which specifies if all connected edges should be removed as well. Default is true.
	 * @return list of removed cells
	 */
	public native List<mxICell> removeCells(List<mxICell> cells, Boolean includeEdges) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var includeEdgesJS = includeEdges != null ? includeEdges.@java.lang.Boolean::booleanValue()() : null;
		var removedJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).removeCells(cellsJS,
				includeEdgesJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(removedJS);
	}-*/;

	public native Element getContainer() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).container;
	}-*/;

	/**
	 * Returns the cells to be selected for the given list of changes.
	 * 
	 * @param changes list of changes
	 * @return list of cells
	 */
	public native List<mxICell> getSelectionCellsForChanges(List<mxGraphModel.mxChange> changes) /*-{
		var changesJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(changes);
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this)
				.getSelectionCellsForChanges(changesJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(cellsJS);
	}-*/;

	/**
	 * Returns the scale of the background page. Default is 1.5.
	 * 
	 * @return
	 */
	public native double getPageScale() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).pageScale;
	}-*/;

	/**
	 * Returns true if the given cell is a valid drop target for the specified cells. If the given cell is an edge, then <isSplitDropTarget> is used, else
	 * <isParentDropTarget> is used to compute the return value.
	 * 
	 * @param cell {@link mxCell} that represents the possible drop target.
	 * @param cells {@link mxCell} that represents the new terminal.
	 * @param event Mouseevent that triggered the invocation.
	 * @return
	 */
	public native boolean isValidDropTarget(mxCell cell, List<mxICell> cells, NativeEvent event) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidDropTarget(cellJS, cellsJS, event);
	}-*/;

	/**
	 * Returns true if the given cell is a valid source for new connections. This implementation returns true for all non-null values and is called by is called
	 * by <isValidConnection>.
	 * 
	 * @param cell {@link mxCell} that represents a possible source or null.
	 * @return
	 */
	public native boolean isValidSource(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidSource(cellJS);
	}-*/;

	/**
	 * Returns true if dropping onto edges should be enabled. Default is true.
	 * 
	 * @return
	 */
	public native boolean isSplitEnabled() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isSplitEnabled();
	}-*/;

	/**
	 * 
	 * Returns true if the given edge can be split into two edges with the given cell as a new terminal between the two.
	 * 
	 * @param edge {@link mxCell} that represents the edge to be split.
	 * @param cells
	 * @param event Mouseevent that triggered the invocation.
	 * @return
	 */
	public native boolean isSplitTarget(mxCell edge, List<mxICell> cells, NativeEvent event) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var edgeJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(edge);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isSplitTarget(edgeJS, cellsJS, event);
	}-*/;

	/**
	 * Pans the graph so that it shows the given cell. Optionally the cell may be centered in the container.
	 * 
	 * To center a given graph if the <container> has no scrollbars, use the following code.
	 * 
	 * [code] var bounds = graph.getGraphBounds(); graph.view.setTranslate(-bounds.x - (bounds.width - container.clientWidth) / 2, -bounds.y - (bounds.height -
	 * container.clientHeight) / 2); [/code]
	 * 
	 * @param cell {@link mxCell} to be made visible.
	 * @param center Optional boolean flag. Default is false.
	 */
	public native void scrollCellToVisible(mxICell cell, Boolean center) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		var centerJS = center != null ? center.@java.lang.Boolean::booleanValue()() : null;
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).scrollCellToVisible(cellJS, centerJS);
	}-*/;

	/**
	 * Splits the given edge by adding the newEdge between the previous source and the given cell and reconnecting the source of the given edge to the given
	 * cell. This method fires <mxEvent.SPLIT_EDGE> while the transaction is in progress. Returns the new edge that was inserted.
	 * 
	 * @param edge <mxCell> that represents the edge to be splitted.
	 * @param cells <mxCells> that represents the cells to insert into the edge.
	 * @param newEdge <mxCell> that represents the edge to be inserted.
	 * @param dx Optional integer that specifies the vector to move the cells.
	 * @param dy Optional integer that specifies the vector to move the cells.
	 * @return
	 */
	public native mxICell splitEdge(mxICell edge, List<mxICell> cells, mxICell newEdge, Integer dx, Integer dy) /*-{
		var edgeJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(edge);
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var newEdgeJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(newEdge);
		var dxJS = dx != null ? dx.@java.lang.Integer::intValue()() : null;
		var dyJS = dy != null ? dy.@java.lang.Integer::intValue()() : null;

		var retvalJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).splitEdge(edgeJS, cellsJS,
				newEdgeJS, dxJS, dyJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(retvalJS);
	}-*/;

	/**
	 * Returns true if the graph should automatically scroll if the mouse goes near the container edge while dragging. This is only taken into account if the
	 * container has scrollbars. Default is true.
	 * 
	 * @return
	 */
	public native boolean isAutoScroll() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).autoScroll;
	}-*/;

	/**
	 * Returns true if the given cell is a valid root for the cell display hierarchy. This implementation returns true for all non-null values.
	 * 
	 * @param cell {@link mxCell} which should be checked as a possible root.
	 * @return
	 */
	public native boolean isValidRoot(mxCell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidRoot(cellJS);
	}-*/;

	public native void setDropEnabled(boolean enabled) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setDropEnabled(enabled);
	}-*/;

	/**
	 * Tolerance for a move to be handled as a single click. Default is 4 pixels.
	 * 
	 * @return
	 */
	public native int getTolerance() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getTolerance();
	}-*/;

	/**
	 * Specifies the grid size. Default is 10.
	 * 
	 * @return
	 */
	public native int getGridSize() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getGridSize();
	}-*/;

	/**
	 * Specifies if panning should be enabled. This implementation updates <mxPanningHandler.panningEnabled> in <panningHandler>.
	 * 
	 * @param panning
	 */
	public native void setPanning(boolean panning) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setPanning(panning);
	}-*/;

	public HandlerRegistration addContextMenuHandler(ContextMenuHandler handler)
	{
		return addDomHandler(handler, ContextMenuEvent.getType());
	}

	/**
	 * Selects all children of the given parent cell or the children of the default parent if no parent is specified. To select leaf vertices and/or edges use
	 * <selectCells>.
	 * 
	 * @param parent Optional {@link mxCell} whose children should be selected. Default is <defaultParent>.
	 */
	public native void selectAll(mxCell parent) /*-{
		var parentJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(parent);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).selectAll(parentJS);
	}-*/;

	/**
	 * Select all edges inside the given parent or the default parent.
	 */
	public native void selectEdges() /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).selectEdges();
	}-*/;

	/**
	 * Select all vertices inside the given parent or the default parent.
	 */
	public native void selectVertices() /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).selectVertices();
	}-*/;

	/**
	 * Returns true if the graph is enabled.
	 * 
	 * @return
	 */
	public native boolean isEnabled() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isEnabled();
	}-*/;

	/**
	 * Moves or clones the specified cells and moves the cells or clones by the given amount, adding them to the optional target cell. The evt is the mouse
	 * event as the mouse was released. The change is carried out using <cellsMoved>. This method fires <mxEvent.MOVE_CELLS> while the transaction is in
	 * progress. Returns the cells that were moved.
	 * 
	 * @param cells list of {@link mxCellState} to be moved, cloned or added to the target.
	 * @param dx Integer that specifies the x-coordinate of the vector.
	 * @param dy Integer that specifies the y-coordinate of the vector.
	 * @param clone Boolean indicating if the cells should be cloned.
	 * @param target {@link mxCell} that represents the new parent of the cells.
	 * @return list of cells that were moved.
	 */
	public native List<mxICell> moveCells(List<mxICell> cells, int dx, int dy, boolean clone, mxICell target) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var targetJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(target);
		var retvalJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).moveCells(cellsJS, dx, dy, clone,
				targetJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(retvalJS);
	}-*/;

	/**
	 * Specifies if dangling edges are allowed, that is, if edges are allowed that do not have a source and/or target terminal defined.
	 * 
	 * @param allowDanglingEdges
	 */
	public native void setAllowDanglingEdges(boolean allowDanglingEdges) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setAllowDanglingEdges(allowDanglingEdges);
	}-*/;

	/**
	 * Returns true if dangling edges are allowed, false otherwise
	 * 
	 * @return
	 */
	public native boolean isAllowDanglingEdges() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isAllowDanglingEdges();
	}-*/;

	/**
	 * Sets the style of the specified cells. If no cells are given, then the selection cells are changed.
	 * 
	 * @param style String representing the new style of the cells.
	 * @param cells Optional list of {@link mxCell} to set the style for. Default is the selection cells.
	 */
	public native void setCellStyle(String style, List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setCellStyle(style, cellsJS);
	}-*/;

	/**
	 * Returns true if the selection is empty.
	 * 
	 * @return
	 */
	public native boolean isSelectionEmpty() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isSelectionEmpty();
	}-*/;

	/**
	 * Updates the bounds of the given array of groups so that it includes all child vertices.
	 * 
	 * @param cells The groups whose bounds should be updated.
	 * @param border Optional border to be added in the group. Default is 0.
	 * @param moveGroup Optional boolean that allows the group to be moved. Default is false.
	 * @return
	 */
	public native List<mxICell> updateGroupBounds(List<mxICell> cells, Integer border, Boolean moveGroup) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var borderJS = border != null ? border.@java.lang.Integer::intValue()() : null;
		var moveGroupJS = moveGroup != null ? moveGroup.@java.lang.Boolean::booleanValue()() : null;
		var retvalJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).updateGroupBounds(cellsJS,
				borderJS, moveGroupJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(retvalJS);

	}-*/;

	/**
	 * Updates the size of the given cell in the model using <cellSizeUpdated>. This method fires <mxEvent.UPDATE_CELL_SIZE> while the transaction is in
	 * progress. Returns the cell whose size was updated.
	 * 
	 * @param cell
	 * @param ignoreChildren
	 * @return
	 */
	public native mxICell updateCellSize(mxICell cell, Boolean ignoreChildren) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		var ignoreChildrenJS = ignoreChildren != null ? ignoreChildren.@java.lang.Boolean::booleanValue()() : null;
		var retvalJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).updateCellSize(cellJS,
				ignoreChildrenJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(retvalJS);
	}-*/;

	/**
	 * Returns the {@link mxGeometry} for the given cell. This implementation uses <mxGraphModel.getGeometry>. Subclasses can override this to implement
	 * specific geometries for cells in only one graph, that is, it can return geometries that depend on the current state of the view.
	 * 
	 * @param cell {@link mxCell} whose geometry should be returned.
	 * @return
	 */
	public native mxGeometry getCellGeometry(mxCell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		var geoJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getCellGeometry(cellJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(geoJS);

	}-*/;

	/**
	 * Removes the specified cells from their parents and adds them to the default parent. Returns the cells that were removed from their parents.
	 * 
	 * @param cells List of {@link mxCell}S to be removed from their parents.
	 * @return list of removed cells
	 */
	public native List<mxICell> removeCellsFromParent(List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var retvalJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).removeCellsFromParent(cellsJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(retvalJS);

	}-*/;

	/**
	 * Specifies if the graph should allow any interactions. This implementation updates <enabled>.
	 * 
	 * @param enabled
	 */
	public native void setEnabled(boolean enabled) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setEnabled(enabled);
	}-*/;

	/**
	 * Returns an {@link mxPoint} representing the given event in the unscaled, non-translated coordinate space of <container> and applies the grid.
	 * 
	 * @param event Mous event that contains the mouse pointer location.
	 * @param addOffset Optional boolean that specifies if the position should be offset by half of the <gridSize>. Default is true.
	 * @return
	 */
	public native mxPoint getPointForEvent(NativeEvent event, Boolean addOffset) /*-{
		var addOffsetJS = addOffset != null ? addOffset.@java.lang.Boolean::booleanValue()() : null;
		var pointJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getPointForEvent(event, addOffsetJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(pointJS);
	}-*/;

	/**
	 * Snaps the given numeric value to the grid if <gridEnabled> is true.
	 * 
	 * @param value Numeric value to be snapped to the grid.
	 * @return
	 */
	public native double snap(double value) /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).snap(value);
	}-*/;

	/**
	 * Returns the bounds of the visible graph
	 * 
	 * @return
	 */
	public native mxRectangle getGraphBounds() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getGraphBounds());
	}-*/;

	/**
	 * Returns the bottom-most cell that intersects the given point (x, y) in the cell hierarchy starting at the given parent. This will also return swimlanes
	 * if the given location intersects the content area of the swimlane. If this is not desired, then the <hitsSwimlaneContent> may be used if the returned
	 * cell is a swimlane to determine if the location is inside the content area or on the actual title of the swimlane.
	 * 
	 * @param x X-coordinate of the location to be checked.
	 * @param y Y-coordinate of the location to be checked.
	 * @param parent <mxCell> that should be used as the root of the recursion. Default is <defaultParent>.
	 * @param vertices Optional boolean indicating if vertices should be returned. Default is true.
	 * @param edges Optional boolean indicating if edges should be returned. Default is true.
	 * @return
	 */
	public native mxICell getCellAt(int x, int y, mxICell parent, Boolean vertices, Boolean edges) /*-{
		var parentJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(parent);
		var verticesJS = vertices != null ? vertices.@java.lang.Boolean::booleanValue()() : null;
		var edgesJS = edges != null ? edges.@java.lang.Boolean::booleanValue()() : null;

		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getCellAt(x, y, parentJS, verticesJS, edgesJS));
	}-*/;

	/**
	 * Clears all cell states or the states for the hierarchy starting at the given cell and validates the graph. This fires a refresh event as the last step.
	 * 
	 * @param cell Optional {@link mxCell} for which the cell states should be cleared.
	 */
	public native void refresh(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).refresh(cellJS);
	}-*/;

	public native void init(Element container) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).init(container);
	}-*/;

	/**
	 * Sets the new value for enterStopsCellEditing. If true, pressing the enter key without pressing control or shift will stop editing and accept the new
	 * value. This is used in <mxCellEditor> to stop cell editing. Note: You can always use F2 and escape to stop editing.
	 * 
	 * @param value
	 */
	public native void setEnterStopsCellEditing(boolean value) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setEnterStopsCellEditing(value);
	}-*/;

	/**
	 * Gets the value for enterStopsCellEditing. If true, pressing the enter key without pressing control or shift will stop editing and accept the new value.
	 * This is used in <mxCellEditor> to stop cell editing. Note: You can always use F2 and escape to stop editing. Default is false.
	 * 
	 * @return
	 */
	public native boolean isEnterStopsCellEditing() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isEnterStopsCellEditing();
	}-*/;

	/**
	 * Clears the selection using <mxGraphSelectionModel.clear>.
	 */
	public native void clearSelection() /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).clearSelection();
	}-*/;

	/**
	 * Returns true if the given cell is editable. This returns <cellsEditable> for all given cells if <isCellLocked> does not return true for the given cell
	 * and its style does not specify <mxConstants.STYLE_EDITABLE> to be 0.
	 * 
	 * @param cell {@link mxCell} whose editable state should be returned.
	 * @return
	 */
	public native boolean isCellEditable(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellEditable(cellJS);
	}-*/;

	/**
	 * Returns true if the label must be rendered as HTML markup.
	 * 
	 * @return the htmlLabels
	 */
	public native boolean isHtmlLabels() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isHtmlLabels();
	}-*/;

	/**
	 * Sets the value for htmlLabels flag.
	 * 
	 * @param htmlLabels the htmlLabels to set
	 */
	public native void setHtmlLabels(boolean htmlLabels) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setHtmlLabels(htmlLabels);
	}-*/;

	/**
	 * Pans the graph so that it shows the given rectangle.
	 * 
	 * @param rectangle {@link mxRectangle} to be made visible.
	 */
	public native boolean scrollRectToVisible(mxRectangle rectangle) /*-{
		var rectangleJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(rectangle);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).scrollRectToVisible(rectangleJS);
	}-*/;

	/**
	 * Returns true if the given cell is foldable. This implementation returns true if the cell has at least one child and its style does not specify
	 * <mxConstants.STYLE_FOLDABLE> to be 0.
	 * 
	 * @param cell {@link mxCell} whose foldable state should be returned.
	 * @param collapse
	 * @return
	 */
	public native boolean isCellFoldable(mxICell cell, boolean collapse) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellFoldable(cellJS, collapse);
	}-*/;

	/**
	 * Returns the panning handler
	 * 
	 * @return instance of {@link mxPanningHandler}
	 */
	public native mxPanningHandler getPanningHandler() /*-{
		var phJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).panningHandler;
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(phJS);
	}-*/;

	/**
	 * Sets the panning handler
	 * 
	 * @param panningHandler instance of {@link mxPanningHandler}
	 */
	public native void setPanningHandler(mxPanningHandler panningHandler) /*-{
		var phJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(panningHandler);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).panningHandler = phJS;
	}-*/;

	/**
	 * Returns the tooltip handler
	 * 
	 * @return
	 */
	public native mxTooltipHandler getTooltipHandler() /*-{
		var phJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).tooltipHandler;
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(phJS);
	}-*/;

	/**
	 * Sets the tooltip handler
	 * 
	 * @param handler
	 */
	public native void setTooltipHandler(mxTooltipHandler handler) /*-{
		var handlerJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(handler);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).tooltipHandler = handlerJS;
	}-*/;

	/**
	 * Returns the string or DOM node to be used as the tooltip for the given cell. This implementation uses the cells getTooltip function if it exists, or else
	 * it returns <convertValueToString> for the cell.
	 * 
	 * Example:
	 * 
	 * (code) graph.getTooltipForCell = function(cell) { return 'Hello, World!'; } (end)
	 * 
	 * Replaces all tooltips with the string Hello, World!
	 * 
	 * @param cell {@link mxCell} whose tooltip should be returned.
	 * @return
	 */
	public native String getTooltipForCell(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getTooltipForCell(cellJS);
	}-*/;

	/**
	 * Returns true if the given cell is a swimlane in the graph. A swimlane is a container cell with some specific behaviour. This implementation checks if the
	 * shape associated with the given cell is a {@link mxSwimlane}.
	 * 
	 * @param cell cell to test
	 * @return true if cell is a swimlane, false otherwise
	 */
	public native boolean isSwimlane(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isSwimlane(cellJS);
	}-*/;

	/**
	 * Specifies if the graph should allow resizing of cells. This implementation updates cellsResizable.
	 * 
	 * @param value Boolean indicating if the graph should allow resizing of cells.
	 */
	public native void setCellsResizable(boolean value) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setCellsResizable(value);
	}-*/;

	/**
	 * Specifies if the container should be resized to the graph size when the graph size has changed.
	 * 
	 * @param value
	 */
	public native void setResizeContainer(boolean value) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setResizeContainer(value);
	}-*/;

	/**
	 * Return true if the container should be resized to the graph size when the graph size has changed. Default is true.
	 * 
	 * @return
	 */
	public native boolean isResizeContainer() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isResizeContainer();
	}-*/;

	/**
	 * Sets {@link mxRectangle} that specifies the minimum size of the container if resizeContainer is true.
	 * 
	 * @param value
	 */
	public native void setMinimumContainerSize(mxRectangle value) /*-{
		var valueJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(value);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).minimumContainerSize = valueJS;
	}-*/;

	/**
	 * Gets {@link mxRectangle} that specifies the minimum size of the container if resizeContainer is true.
	 * 
	 * @return
	 */
	public native mxRectangle getMinimumContainerSize() /*-{
		var valueJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).minimumContainerSize;
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(valueJS);
	}-*/;

	/**
	 * Sets the value of border.
	 * 
	 * @param border Positive integer that represents the border to be used.
	 */
	public native void setBorder(int border) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setBorder(border);
	}-*/;

	/**
	 * Returns the value of border.
	 * 
	 * @return
	 */
	public native int getBorder() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getBorder();
	}-*/;

	/**
	 * Returns a string or DOM node that represents the label for the given cell
	 * 
	 * @param terminal {@link mxCell} whose label should be returned.
	 * @return
	 */
	public native String getLabel(mxICell terminal) /*-{
		var terminalJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(terminal);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getLabel(terminalJS);
	}-*/;

	/**
	 * Returns the initial value for in-place editing. If this function is overridden, then mxGraphModel.valueForCellChanged should take care of correctly
	 * storing the actual new value inside the user object.
	 * 
	 * @param cell {@link mxCell} for which the initial editing value should be returned.
	 * @param evt Optional mouse event that triggered the editor.
	 * @return
	 */
	public native String getEditingValue(mxICell cell, NativeEvent evt) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getEditingValue(cellJS, evt);
	}-*/;

	/**
	 * Adds the given cells from the selection.
	 * 
	 * @param cells Array of {@link mxCellState} to be removed from the selection.
	 */
	public native void addSelectionCells(List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).addSelectionCells(cellsJS);
	}-*/;

	/**
	 * Adds the given cell to the selection.
	 * 
	 * @param cell {@link mxICell} to be added to the selection.
	 */
	public native void addSelectionCell(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).addSelectionCell(cellJS);
	}-*/;

	/**
	 * Removes the given cells to the selection.
	 * 
	 * @param cells Array of {@link mxCellState} to be added to the selection.
	 */
	public native void removeSelectionCells(List<mxICell> cells) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).removeSelectionCells(cellsJS);
	}-*/;

	/**
	 * Removes the given cell from the selection.
	 * 
	 * @param cell {@link mxICell} to be removed from the selection.
	 */
	public native void removeSelectionCell(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).removeSelectionCell(cellJS);
	}-*/;

	/**
	 * Returns true if the given cell is selected.
	 * 
	 * @return {@link mxICell} for which the selection state should be returned.
	 */
	public native boolean isCellSelected(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellSelected(cellJS);
	}-*/;

	/**
	 * Returns the edges between the given source and target. This takes into account collapsed and invisible cells and returns the connected edges as displayed
	 * on the screen.
	 * 
	 * @param source
	 * @param target
	 * @param directed
	 * @return
	 */
	public native List<mxICell> getEdgesBetween(mxICell source, mxICell target, Boolean directed) /*-{
		var sourceJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(source);
		var targetJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(target);
		var directedJS = directed != null ? directed.@java.lang.Boolean::booleanValue()() : null;
		var edgesJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getEdgesBetween(sourceJS, targetJS,
				directedJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(edgesJS);
	}-*/;

	/**
	 * Returns the textual representation for the given cell.
	 * 
	 * @param cell {@link mxICell} whose textual representation should be returned.
	 * @return
	 */
	public native String convertValueToString(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).convertValueToString(cellJS);
	}-*/;

	/**
	 * Specifies if the graph should allow cloning of cells by holding down the control key while cells are being moved. This implementation updates
	 * cellsCloneable.
	 * 
	 * @param value Boolean indicating if the graph should be cloneable.
	 */
	public native void setCellsCloneable(boolean value) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setCellsCloneable(value);
	}-*/;

	/**
	 * Returns true if the graph allows cloning of cells by using control-drag, false otherwise
	 * 
	 * @return
	 */
	public native boolean isCellsCloneable() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellsCloneable();
	}-*/;

	/**
	 * Returns true if the given cell may not be moved, sized, bended, disconnected, edited or selected. This implementation returns true for all vertices with
	 * a relative geometry if locked is false.
	 * 
	 * @return
	 */
	public native boolean isCellsLocked() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellsLocked();
	}-*/;

	/**
	 * Specifies if any cell may be moved, sized, bended, disconnected, edited or selected.
	 * 
	 * @param value Boolean that defines the new value for cellsLocked.
	 */
	public native void setCellsLocked(boolean value) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setCellsLocked(value);
	}-*/;

	/**
	 * Returns true if the given cell may not be moved, sized, bended, disconnected, edited or selected. This implementation returns true for all vertices with
	 * a relative geometry if <locked> is false.
	 * 
	 * @param cell
	 * @return
	 */
	public native boolean isCellLocked(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isCellLocked(cellJS);
	}-*/;

	/**
	 * Returns a map representing the cell style for the given cell. If no string is defined in the model that specifies the style, then the default style for
	 * the cell is returned or empty map, if not style can be found. Note: You should try and get the cell state for the given cell and use the cached style in
	 * the state before using this method.
	 * 
	 * @param cell
	 * @return
	 */
	public native Map<String, String> getCellStyle(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		var mapJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).getCellStyle(cellJS);
		return @com.mxgraph.gwt.client.util.CollectionUtils::convertMap(Lcom/google/gwt/core/client/JavaScriptObject;)(mapJS);
	}-*/;

	/**
	 * Specifies if panning via panGraph should be allowed to implement autoscroll if no scrollbars are available in scrollPointToVisible. Default is false.
	 * 
	 * @param value
	 */
	public native void setAllowAutoPanning(boolean value) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).allowAutoPanning = value;
	}-*/;

	/**
	 * Returns true if panning via panGraph should be allowed to implement autoscroll if no scrollbars are available in scrollPointToVisible.
	 * 
	 * @return
	 */
	public native boolean isAllowAutoPanning() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).allowAutoPanning;
	}-*/;

	/**
	 * Returns the {@link mxPanningManager}
	 * 
	 * @return
	 */
	public native mxPanningManager getPanningManager() /*-{
		var panManJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).panningManager;
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(panManJS);
	}-*/;

	/**
	 * Returns the {@link mxCellEditor}
	 * 
	 * @return
	 */
	public native mxCellEditor getCellEditor() /*-{
		var ceJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).cellEditor;
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(ceJS);
	}-*/;

	/**
	 * Adds a listener to the graph event dispatch loop. The listener
	 * must implement the mouseDown, mouseMove and mouseUp methods
	 * as shown in the {@link mxMouseEvent} class.
	 * @param listener listener to be added to the graph event listeners.
	 */
	public native void addMouseListener(mxMouseEvent.mxIMouseListener<?> listener) /*-{
		var listenerJS = {
			mouseDown : function(sender, me) {
				var meJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(me);
				var senderJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(sender);
				listener.@com.mxgraph.gwt.client.util.mxMouseEvent.mxIMouseListener::onMouseDown(Ljava/lang/Object;Lcom/mxgraph/gwt/client/util/mxMouseEvent;)(senderJ, meJ);
			},
			mouseUp : function(sender, me) {
				var meJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(me);
				var senderJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(sender);
				listener.@com.mxgraph.gwt.client.util.mxMouseEvent.mxIMouseListener::onMouseUp(Ljava/lang/Object;Lcom/mxgraph/gwt/client/util/mxMouseEvent;)(senderJ, meJ);
			},
			mouseMove : function(sender, me) {
				var meJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(me);
				var senderJ = @com.mxgraph.gwt.client.util.WrapperUtils::wrap(Lcom/google/gwt/core/client/JavaScriptObject;)(sender);
				listener.@com.mxgraph.gwt.client.util.mxMouseEvent.mxIMouseListener::onMouseMove(Ljava/lang/Object;Lcom/mxgraph/gwt/client/util/mxMouseEvent;)(senderJ, meJ);
			}
		};

		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).addMouseListener(listenerJS);

	}-*/;

	/**
	 * Gets the state of the mouse button.
	 * 
	 * @return
	 */
	public native boolean getIsMouseDown() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isMouseDown;
	}-*/;

	/**
	 * Returns true if the given cell is a valid root for the cell display
	* hierarchy. 
	 * 
	 * @param cell  {@link mxICell} which should be checked as a possible root.
	 * @return
	 */
	public native boolean isValidRoot(mxICell cell) /*-{
		var cellJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(cell);
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isValidRoot(cellJS);
	}-*/;

	/**
	 * Returns true if children of the container cell should be constrained
	 * @return
	 */
	public native boolean isConstrainChildren() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isConstrainChildren();
	}-*/;

	/**
	 * Sets the value of constrainChildren flag
	 * @param value
	 */
	public native void setConstrainChildren(boolean value) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setConstrainChildren(value);
	}-*/;

	/**
	 * Specifies if a parent should contain the child bounds after a resize of
	* the child.
	 * 
	 * @param value
	 */
	public native void setExtendParents(boolean value) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setExtendParents(value);
	}-*/;

	/**
	 * Returns true if a parent should contain the child bounds after a resize of
	* the child. Default is true.
	 * 
	 * @return
	 */
	public native boolean isExtendParents() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isExtendParents();
	}-*/;

	/**
	 * Specifies if parents should be extended according to the extendParents
	* switch if cells are added. 
	 * 
	 * @param value
	 */
	public native void setExtendParentsOnAdd(boolean value) /*-{
		@com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).setExtendParentsOnAdd(value);
	}-*/;

	/**
	 * Returns true if parents should be extended according to the extendParents
	* switch if cells are added. Default is true.
	 * 
	 * @return
	 */
	public native boolean isExtendParentsOnAdd() /*-{
		return @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).isExtendParentsOnAdd();
	}-*/;
	
	public native List<mxICell> foldCells(boolean collapse, Boolean recurse, List<mxICell> cells, Boolean checkFoldable) /*-{
		var cellsJS = @com.mxgraph.gwt.client.util.WrapperUtils::unwrapList(Ljava/util/List;)(cells);
		var recurseJS = recurse != null ? recurse.@java.lang.Boolean::booleanValue()() : null;
		var checkFoldableJS = checkFoldable != null ? checkFoldable.@java.lang.Boolean::booleanValue()() : null;
		var list = @com.mxgraph.gwt.client.util.WrapperUtils::unwrap(Lcom/mxgraph/gwt/client/IJavaScriptWrapper;)(this).foldCells(collapse, recurseJS, cellsJS, checkFoldableJS);
		return @com.mxgraph.gwt.client.util.WrapperUtils::wrapList(Lcom/google/gwt/core/client/JavaScriptObject;)(list); 
	}-*/;

}
