/*
    This file is part of Cyclos (www.cyclos.org).
    A project of the Social Trade Organisation (www.socialtrade.org).

    Cyclos is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    Cyclos is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Cyclos; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 */
package nl.strohalm.cyclos.controls.channels;

import java.util.Map;

import nl.strohalm.cyclos.controls.BaseBindingForm;

/**
 * Form used to edit a channel
 * @author luis
 */
public class EditChannelForm extends BaseBindingForm {
    private static final long serialVersionUID = 7585711525604418742L;
    private long              channelId;
    private String[]          principalTypes;
    private String            defaultPrincipalType;

    public Map<String, Object> getChannel() {
        return values;
    }

    public Object getChannel(final String key) {
        return values.get(key);
    }

    public long getChannelId() {
        return channelId;
    }

    public String getDefaultPrincipalType() {
        return defaultPrincipalType;
    }

    public String[] getPrincipalTypes() {
        return principalTypes;
    }

    public void setChannel(final Map<String, Object> map) {
        values = map;
    }

    public void setChannel(final String key, final Object value) {
        values.put(key, value);
    }

    public void setChannelId(final long channelId) {
        this.channelId = channelId;
    }

    public void setDefaultPrincipalType(final String defaultPrincipalType) {
        this.defaultPrincipalType = defaultPrincipalType;
    }

    public void setPrincipalTypes(final String[] principalTypes) {
        this.principalTypes = principalTypes;
    }
}
