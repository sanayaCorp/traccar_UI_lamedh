/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.traccar.web.client.view;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowMouseDownEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.traccar.web.client.i18n.Messages;
import org.traccar.web.client.model.EventProperties;
import org.traccar.web.client.state.GridStateHandler;
import org.traccar.web.shared.model.Device;
import org.traccar.web.shared.model.DeviceEvent;

/**
 *
 * @author sanaya
 */
public class DeviceEventView implements RowMouseDownEvent.RowMouseDownHandler, CellDoubleClickEvent.CellDoubleClickHandler {
 
    private static DeviceEventViewUiBinder uiBinder = GWT.create(DeviceEventViewUiBinder.class);

    @Override
    public void onRowMouseDown(RowMouseDownEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCellClick(CellDoubleClickEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    interface DeviceEventViewUiBinder extends UiBinder<Widget, DeviceEventView> {
    }
    
    public interface DeviceEventHandler {
        public void onSelected(DeviceEvent deviceEvent);
        public void onMouseOver(int mouseX, int mouseY, DeviceEvent deviceEvent);
        public void onMouseOut(int mouseX, int mouseY, DeviceEvent deviceEvent);
        public void doubleClicked(DeviceEvent deviceEvent);
        public void onClearSelection();
    }
    
    private final DeviceEventHandler deviceEventHandler;
    
    private Widget panel;
    
    @UiField(provided = true)
    ColumnModel<DeviceEvent> cm;
   
    @UiField(provided = true)
    ListStore<DeviceEvent> store;
    
    @UiField(provided = true)
    PagingLoader<PagingLoadConfig, PagingLoadResult<DeviceEvent>> loader;
    
    @UiField
    Grid<DeviceEvent> grid;
    
    @UiField
    GridView<DeviceEvent> view;
    
    @UiField
    PagingToolBar toolBar;
    
    @UiField
    String type;
    
    @UiField(provided = true)
    Messages i18n = GWT.create(Messages.class);
    
    public DeviceEventView(final DeviceEventHandler deviceEventHandler,
            final ListStore<DeviceEvent> deviceEventStore) {
        this.deviceEventHandler = deviceEventHandler;
        this.store = deviceEventStore;
        
        EventProperties eventProperties = GWT.create(EventProperties.class);
        
        List<ColumnConfig<DeviceEvent, ?>> columnConfigList =
                new LinkedList<ColumnConfig<DeviceEvent, ?>>();
        
        ColumnConfig<DeviceEvent, Date> colTime = new ColumnConfig<DeviceEvent, Date>(eventProperties.time(), 0, i18n.time());
        colTime.setFixed(true);
        colTime.setResizable(false);
        columnConfigList.add(colTime);
        
        colTime.setCell(new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
        
        ColumnConfig<DeviceEvent, String> colName = new ColumnConfig<DeviceEvent, String>(eventProperties.name(), 0, i18n.name());
        colName.setFixed(true);
        colName.setResizable(false);
        columnConfigList.add(colName);               
                
        
//        ColumnConfig<DeviceEvent, String> colType = new ColumnConfig<DeviceEvent, String>(eventProperties.type(), 0, i18n.type());
//        colType.setCell(new AbstractCell<String>(BrowserEvents.MOUSEOVER, BrowserEvents.MOUSEOUT) {
//            @Override
//            public void render(Cell.Context context, String value, SafeHtmlBuilder sb) {
//                if (value == null) return;
//                sb.appendEscaped(value);
//            }
//
//            @Override
//            public void onBrowserEvent(Cell.Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
//                if (event.getType().equals(BrowserEvents.MOUSEOVER) || event.getType().equals(BrowserEvents.MOUSEOUT)) {
//                    Element target = Element.as(event.getEventTarget());
//                    int rowIndex = grid.getView().findRowIndex(target);
//                    if (rowIndex != -1) {
//                        if (event.getType().equals(BrowserEvents.MOUSEOVER)) {
//                            deviceEventHandler.onMouseOver(event.getClientX(), event.getClientY(), store.get(rowIndex));
//                        } else {
//                            deviceEventHandler.onMouseOut(event.getClientX(), event.getClientY(), store.get(rowIndex));
//                        }
//                    }
//                } else {
//                    super.onBrowserEvent(context, parent, value, event, valueUpdater);
//                }
//            }
//        });
//        columnConfigList.add(colType);
        
        cm = new ColumnModel<DeviceEvent>(columnConfigList);
        
        uiBinder.createAndBindUi(this);
                
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        grid.addRowMouseDownHandler(this);
        grid.addCellDoubleClickHandler(this);

        grid.getView().setAutoFill(true);
        grid.getView().setForceFit(true);

        new GridStateHandler<DeviceEvent>(grid).loadState();
    }
}
