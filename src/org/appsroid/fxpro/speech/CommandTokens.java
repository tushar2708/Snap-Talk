package org.appsroid.fxpro.speech;

public class CommandTokens {
    public String action;
    public String property;
    public String value;
    public String[] others;

// constructor
public CommandTokens(String _action, String _property, String _value, String _others[]) {
   this.action = _action;
   this.property = _property;
   this.value = _value;
   this.others = _others;
}

public CommandTokens() {
	   this.action = "";
	   this.property = "";
	   this.value = "";
	   this.others = null;
}


}
