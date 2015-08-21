/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.traccar.web.client.model;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.Date;
import org.traccar.web.client.i18n.Messages;
import org.traccar.web.shared.model.DeviceEvent;
import org.traccar.web.shared.model.DeviceEventType;

/**
 *
 * @author sanaya
 */
public interface EventProperties extends PropertyAccess<DeviceEvent> {
    ModelKeyProvider<DeviceEvent> id();
    
    ValueProvider<DeviceEvent, Date> time();
    
    ValueProvider<DeviceEvent, String> name(); 
    
    ValueProvider<DeviceEvent, DeviceEventType> type();
    
    
//    ValueProvider<DeviceEvent, String> type();
    public static class DeviceEventTypeLabelProvider implements LabelProvider<DeviceEventType> {
        final Messages i18n = GWT.create(Messages.class);
        
        @Override
        public String getLabel(DeviceEventType type) {
            return i18n.deviceEventType(type);
        }
    }
}
