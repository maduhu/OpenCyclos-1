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

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.accounts.Currency;
import nl.strohalm.cyclos.entities.members.Member;
import nl.strohalm.cyclos.entities.settings.LocalSettings;
import nl.strohalm.cyclos.utils.StringValuedEnum;

/**
 * A ticket is used to validate external payments from clients not on whitelist.
 * The web shop server (on the whitelist) must request a ticket to Cyclos, and
 * pass this ticket to the client. When the client confirms all data, that
 * ticket is passed back to Cyclos. If the ticket is not confirmed, it is marked
 * as cancelled by the TicketExpirationListener
 *
 * @author luis
 */
@javax.persistence.Entity
@Table(name = "tickets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tickets")
public abstract class Ticket extends Entity {

    public enum Nature implements StringValuedEnum {
        WEBSHOP("W"), PAYMENT_REQUEST("R");

        private final String value;

        private Nature(final String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public static enum Relationships implements Relationship {
        CURRENCY("currency"), FROM("from"), TO("to"), TRANSFER_TYPE("transferType"), TRANSFER("transfer");
        private final String name;

        private Relationships(final String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public static enum Status implements StringValuedEnum {
        CANCELLED("C"), EXPIRED("E"), OK("O"), PENDING("P"), FAILED("F");

        private final String value;

        private Status(final String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    private static final long serialVersionUID = -4036600783513490404L;

    private BigDecimal amount;
    private Currency currency;
    private Calendar creationDate;
    private String description;
    private Member from;
    private Status status;
    private String ticket;
    private Member to;
    private Transfer transfer;
    private TransferType transferType;

    @Id
    @GeneratedValue
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "amount", precision = 15, scale = 6)
    public BigDecimal getAmount() {
        return amount;
    }

    @Column(name = "creation_date")
    public Calendar getCreationDate() {
        return creationDate;
    }

    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "cureency_id")
    public Currency getCurrency() {
        return currency;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "from_member_id")
    public Member getFrom() {
        return from;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public Status getStatus() {
        return status;
    }

    @Column(name = "ticket", length = 32, nullable = false)
    public String getTicket() {
        return ticket;
    }

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "to_member_id")
    public Member getTo() {
        return to;
    }

    @ManyToOne(targetEntity = Transfer.class)
    @JoinColumn(name = "transfer_id")
    public Transfer getTransfer() {
        return transfer;
    }

    @ManyToOne(targetEntity = TransferType.class)
    @JoinColumn(name = "transfer_type_id")
    public TransferType getTransferType() {
        return transferType;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreationDate(final Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setFrom(final Member from) {
        this.from = from;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public void setTicket(final String ticket) {
        this.ticket = ticket;
    }

    public void setTo(final Member to) {
        this.to = to;
    }

    public void setTransfer(final Transfer transfer) {
        this.transfer = transfer;
    }

    public void setTransferType(final TransferType transferType) {
        this.transferType = transferType;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + getId() + ", ticket=" + ticket + "]";
    }

    @Override
    protected void appendVariableValues(final Map<String, Object> variables, final LocalSettings localSettings) {
        variables.put("amount", localSettings.getUnitsConverter(getCurrency().getPattern()).toString(getAmount()));
        variables.put("date", localSettings.getDateConverter().toString(creationDate));
        variables.put("from_member", from.getName());
        variables.put("from_login", from.getUsername());
        variables.put("to_member", to.getName());
        variables.put("to_login", to.getUsername());
    }

}
