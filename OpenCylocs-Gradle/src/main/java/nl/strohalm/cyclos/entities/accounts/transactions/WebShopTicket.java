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
package nl.strohalm.cyclos.entities.accounts.transactions;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * A ticket processed for webshops
 * @author luis
 */
@Entity
@DiscriminatorValue(value="W")
public class WebShopTicket extends Ticket {
    private static final long serialVersionUID = 3429390634490637887L;
    private String            clientAddress;
    private String            memberAddress;
    private String            returnUrl;

    @Column(name="client_address",length = 40)
    public String getClientAddress() {
        return clientAddress;
    }

    @Column(name="member_address",length = 40)
    public String getMemberAddress() {
        return memberAddress;
    }

    @Column(name="return_url",length = 150)
    public String getReturnUrl() {
        return returnUrl;
    }

    public void setClientAddress(final String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public void setMemberAddress(final String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public void setReturnUrl(final String returnUrl) {
        this.returnUrl = returnUrl;
    }

}
