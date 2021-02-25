package ro.itschool.curs.enums;

public enum TicketType {
FREE("free entrance") , BUY_ONLINE("buy online"), BUY_TICKET_ON_THE_SPOT("buy ticket on ther spot");
public final String code;

private TicketType(String code) {
    this.code = code;
}
public String getCode() {
	return code;
}
}
