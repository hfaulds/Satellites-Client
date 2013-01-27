package net.msgs;

public class LoginErrorMsg {
  
  private final String error;

  public LoginErrorMsg() {
    this("");
  }

  public LoginErrorMsg(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof LoginErrorMsg))
      return false;
    LoginErrorMsg other = (LoginErrorMsg) obj;
    if (error == null) {
      if (other.error != null)
        return false;
    } else if (!error.equals(other.error))
      return false;
    return true;
  }

}
