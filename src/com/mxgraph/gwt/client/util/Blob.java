package com.mxgraph.gwt.client.util;

import com.google.gwt.core.client.JavaScriptObject;

public final class Blob extends JavaScriptObject {

	public static native Blob createInstance(String data) /*-{
		return new $wnd.Blob([data] , {type : 'text/xml'});
	}-*/;

	protected Blob()
	{
	}
	
	
	
}
