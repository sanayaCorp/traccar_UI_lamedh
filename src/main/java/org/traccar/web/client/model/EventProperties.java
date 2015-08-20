/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.traccar.web.client.model;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.Date;
import org.traccar.web.shared.model.DeviceEvent;

/**
 *
 * @author sanaya
 */
public interface EventProperties extends PropertyAccess<DeviceEvent> {
    ModelKeyProvider<DeviceEvent> id();
    
    ValueProvider<DeviceEvent, Date> time();
    
    ValueProvider<DeviceEvent, String> name();
    
   // ValueProvider<DeviceEvent, String> type();
}
